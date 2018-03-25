package locateplusserver.api
import grails.converters.JSON
import org.springframework.web.multipart.MultipartFile
import locateplusserver.ApiException
import locateplusserver.domains.Photo


class PhotoApiController {
    // ----------------------- Dependencies ---------------------------//
    def authService
    def userService
    def photoService

    // ----------------------- Public APIs ---------------------------//
    // API to register a user and return user details
    def upload() {

        def imei = request.getHeader("imei")

        // check if imei is present
        authService.checkImei(imei)

        // get user object by imei number
        def user = userService.getByImei(imei)

        if(!user)
        {
            throw new ApiException("Not Registered", Constants.HttpCodes.BAD_REQUEST)
        }

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

        // Get file
        def fileStream = new File("F:/temp/"+uuid+".png").newDataInputStream()

        fileStream

    }



}

    // ----------------------- Private APIs ---------------------------//

