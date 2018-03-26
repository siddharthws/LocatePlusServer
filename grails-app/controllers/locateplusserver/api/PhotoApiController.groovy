package locateplusserver.api
import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile
import locateplusserver.ApiException


class PhotoApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def photoService

    // ----------------------- Public APIs ---------------------------//
    // API to register a user and return user details
    def upload() {

        // Get Multipart file from parameter name
        List<MultipartFile> files = request.multiFileMap.uploadImage

        // If files are not present
        if(!files){

            throw new ApiException("Photos Not present", Constants.HttpCodes.BAD_REQUEST)
        }

        // Iterate over photos
        files.each { uploadedFile ->

            //Get file name or uuid
            def fileName = uploadedFile.originalFilename

            // Convert file name to String
            def uuid = fileName.toString()

            // Create new file with name as uuid
            def os = new File("F:/temp/", fileName + ".png").newDataOutputStream()

            // Get input Stream on file
            def is = uploadedFile.getInputStream()

            //Store photo
            photoService.putPhoto(uuid)


            // Write photo to newly created file
            os << is

        }

        // Send  response
        def resp=  [photo: true]
        render resp as JSON
    }

    def getPhoto(){

        def placeId = request.JSON.placeId

        // get place by ID
        def place = userService.getPlaceById(placeId)

        def photoList = photoService.getPhotoByPlace(place)

        def uuid = 0
        photoList.each{member->

            uuid = member.uuid.toString()

            def file= new File("F:/temp/"+uuid+".png")

            def is = file.newInputStream()

            render file:is, contentType: 'image/png'

        }


        //render(file: new File("F:/temp/4e5f988a-8cac-49fe-bbd1-694fea87f234.png"), fileName: "4e5f988a-8cac-49fe-bbd1-694fea87f234.png")
        // Return photo object
       // render(file: new File("F:/temp/", name + ".png"), fileName: name+".png")



    }



}

    // ----------------------- Private APIs ---------------------------//

