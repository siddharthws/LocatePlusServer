package locateplusserver.api
import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile
import locateplusserver.ApiException
import org.grails.web.json.JSONArray
import grails.io.IOUtils


class PhotoApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def photoService
    def ratingService

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

        def resp = new JSONArray()

        List<MultipartFile> files = new ArrayList<MultipartFile>()

        photoList.each{member->

            uuid = member.uuid.toString()

            def file= new File("F:/temp/"+uuid+".png")

            byte[] ba = IOUtils.copyToByteArray(file)

            resp.add(ba)

        }

        // Send bytes in response
        render resp as JSON









       // MultipartEntity multiPartContent = new MultipartEntity(HttpMultipartMode.BROWSER_COMPATIBLE)

        // Adding Multi-part file parameter "imageFile"

       // multiPartContent.addPart("imageFile", new InputStreamBody(multipartImageFile.inputStream, multipartImageFile.contentType, multipartImageFile.originalFilename))


        //render(file: new File("F:/temp/4e5f988a-8cac-49fe-bbd1-694fea87f234.png"), fileName: "4e5f988a-8cac-49fe-bbd1-694fea87f234.png")
        // Return photo object
       // render(file: new File("F:/temp/", name + ".png"), fileName: name+".png")



    }



}

    // ----------------------- Private APIs ---------------------------//

