package locateplusserver.api

import grails.converters.JSON
import locateplusserver.ApiException
import locateplusserver.Constants
import locateplusserver.domains.Facility
import locateplusserver.domains.Category
import locateplusserver.domains.Place
import locateplusserver.domains.Udid
import locateplusserver.domains.User

class AdminApiController {


    // ----------------------- Dependencies ---------------------------//
    def userService
    def authService
    def updateService
    def adminService
    def photoService
    def importService
    // ----------------------- Public APIs ---------------------------//

    // API to add a category
    def addCategory() {

        log.error("Add Category")
        def newCategory = request.JSON.category

        def categoryPresent = userService.getCategoryByName(newCategory)

        def isPresent = categoryPresent.equalsIgnoreCase(newCategory)

        if(isPresent)
        {
            throw new ApiException("Category Already Present", Constants.HttpCodes.BAD_REQUEST)
        }

        def category = new Category(
                    name : newCategory
        )

        // save category in database .
        category.save(flush: true, failOnError: true)

        // update the FC status for all users
        updateService.updateFcStatus()

        def resp = [success: true]
        render resp as JSON
    }

    // API to remove a place
    def removePlace() {

        log.error("Remove Place")
        def id = request.JSON.placeId

        // get Place by id
        Place place = Place.findById(id)

        if(!place){
            throw new ApiException("Place Note Present", Constants.HttpCodes.BAD_REQUEST)
        }

        place.isRemoved = true

        photoService.removePhotoByPlace(place)

        place.save(flush: true,failOnError: true)

        // update the Place status for all users
        updateService.updatePlaceStatus()

        def resp = [success: true]

        render resp as JSON

    }

    def addFacility(){

        log.error("Add Facility")
        def newFacility = request.JSON.facility


        def isPresent = userService.getFacilityByName(newFacility)

        if(isPresent)
        {
            throw new ApiException("Facility Already Present", Constants.HttpCodes.BAD_REQUEST)
        }

        log.error("hello"+newFacility)
        // Create new facility object based on facility provided
        def facility = new Facility(
                name : newFacility
        )

        // save facility
        facility.save(flush: true,failOnError: true)

        // update the Place status for all users
        updateService.updateFcStatus()

        //return response
        def resp = [success: true]
        render resp as JSON


    }

    def removeCategory()
    {

        log.error("remove Category")
        def categoryId = request.JSON.categoryId

        def category = userService.getCategoryById(categoryId)

        category.delete(flush: true,failOnError: true)


    }


    def removeFacility()
    {
        def facilityId = request.JSON.facilityId

        def facility = userService.getFacilityById(facilityId)



    }


    def removePhotos(){

        log.error("Remove Photo")

        def uuid = request.JSON.photoUuid

        def placeId = request.JSON.placeId

        Place place = userService.getPlaceById(placeId)

        def UuidString = uuid.toString()

        // get Place by id
         def response  = photoService.removePhotos(UuidString)

        if(!response){
            throw new ApiException("Photo Not Removed", Constants.HttpCodes.BAD_REQUEST)
        }

        // update the Place status for all users
        updateService.updatePhotoStatus(place)

        def resp = [success: true]

        render resp as JSON

    }

    def importUdid() {

        log.error("importing")

        // Get file
        def file = request.getFile('importFile')

        // Get table from file
        def table = importService.parseExcel(file)

        // Validate all columns
        importService.checkColumns(table.columns)

        // Validate all rows
        importService.checkRows(table.rows, table.columns)

        def udidJson = []
        table.rows.eachWithIndex {row, i -> udidJson.push(importService.parseRow(table.columns, row))}

        log.error(udidJson.toString())

        udidJson.each {udid ->
            new Udid(name: udid.Name, udid: udid.Udid).save(flush: true, failOnError: true)
        }

        def resp = [success: true]

        render resp as JSON
    }

    def validateUdid() {

        log.error("udid validate request")

        def imei = request.getHeader("imei")
        User user = userService.getByImei(imei)

        def udid = request.JSON.udid
        log.error(udid)
        def response = adminService.getByUdid(udid)

        if(!response) {
            throw locateplusserver.ApiException('Udid not Present', Constants.HttpCodes.BAD_REQUEST)
        }
        else {
            user.udid = udid
            user.save(flush:true, failOnError: true)
        }

        def resp = [success : true]
        render resp as JSON
    }
    // ----------------------- Private APIs ---------------------------//
}
