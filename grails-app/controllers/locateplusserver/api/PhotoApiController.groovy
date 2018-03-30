package locateplusserver.api
import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile
import locateplusserver.ApiException
import org.grails.web.json.JSONArray
import grails.io.IOUtils
import locateplusserver.Constants


class PhotoApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def photoService
    def ratingService

    // ----------------------- Public APIs ---------------------------//
    // API to register a user and return user details
    def upload() {

        log.error("upload photo request")
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
            def os = new File("R:/temp/", fileName + ".png").newDataOutputStream()

            // Get input Stream on file
            def is = uploadedFile.getInputStream()

            // Write photo to newly created file
            os << is

            //Store photo
            photoService.putPhoto(uuid)

        }

        // Send  response
        def resp=  [photo: true]
        render resp as JSON

        log.error("upload photo response")
    }

    def getPhoto(){

        log.error("photo req")

        def placeId = request.JSON.placeId

        // get place by ID
        def place = userService.getPlaceById(placeId)

        def photoList = photoService.getPhotoByPlace(place)

        def uuid = 0

        def photos = []

        photoList.each{member->

             def removed = member.isRemoved

            if(!removed){

                uuid = member.uuid.toString()

                def inAppropriateCount = member.inAppropriateCount

                def file= new File("R:/temp/"+uuid+".png")

                byte[] ba = IOUtils.copyToByteArray(file)

                photos.add(uuid:uuid, photo:ba, inAppropriateCount:inAppropriateCount)

            }

        }

        log.error("length:"+photos.size())
        def resp = [photos:photos]
        // Send bytes in response
        render resp as JSON


        log.error("get photo response")


       // MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

        // Adding Multi-part file parameter "imageFile"

       // multiPartContent.addPart("imageFile", new InputStreamBody(multipartImageFile.inputStream, multipartImageFile.contentType, multipartImageFile.originalFilename))


        //render(file: new File("F:/temp/4e5f988a-8cac-49fe-bbd1-694fea87f234.png"), fileName: "4e5f988a-8cac-49fe-bbd1-694fea87f234.png")
        // Return photo object
       // render(file: new File("F:/temp/", name + ".png"), fileName: name+".png")



    }


    def getPhotoApp(){

        log.error("photo req")

        def placeId = request.JSON.placeId

        // get place by ID
        def place = userService.getPlaceById(placeId)

        def photoList = photoService.getPhotoByPlace(place)

        def uuid = 0

        def photos = []

        photoList.each{member->

            def removed = member.isRemoved

            if(!removed){

                uuid = member.uuid.toString()

                def inAppropriateCount = member.inAppropriateCount

                def file= new File("R:/temp/"+uuid+".png")

                byte[] ba = IOUtils.copyToByteArray(file)

                String base64 = ba.encodeBase64().toString()

                photos.add(uuid:uuid, photo:base64, inAppropriateCount:inAppropriateCount)

                log.error("photo Uuid"+uuid)

            }

        }

        log.error("length:"+photos.size())
        def resp = [photos:photos]
        // Send bytes in response
        render resp as JSON


        log.error("get photo response")


    }



}

    // ----------------------- Private APIs ---------------------------//

