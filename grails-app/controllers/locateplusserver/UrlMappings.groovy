package locateplusserver

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*-------------------- Auth APIs ----------------------*/
        "/api/auth/registerUser"        (controller: "AuthApi") { action = [GET: "registerUser"] }
        "/api/auth/registerAdmin"        (controller: "AuthApi") { action = [GET: "registerAdmin"] }


        /*-------------------- Error Handling ----------------------*/
        "500"(controller: "Utils", action: "handleError")
        "404"(controller: "Utils", action: "handle404")

        /*---------------------User APIs-----------------------*/
        "/api/user/addReviews"      (controller: "UserApi") { action = [POST : "addReviews"]}
        "/api/user/getReviews"      (controller: "UserApi") { action = [POST : "getReviews"]}
        "/api/user/addPlace"        (controller: "UserApi") { action = [POST : "addPlace"]}
        "/api/user/getPlaces"       (controller: "UserApi") { action = [GET  : "getPlaces"]}
        "/api/user/getFC"           (controller:"UserApi")  {action = [GET  : "getFC"]}
        "/api/user/addRatings"      (controller: "UserApi") { action = [POST : "addRatings"]}
        "/api/user/getrpStatus"     (controller: "UserApi") { action = [POST : "getrpStatus"]}

        /*---------------------photo APIs-----------------------*/
        "/api/user/upload"              (controller: "PhotoApi") { action = [POST : "upload"]}
        "/api/user/getPhoto"            (controller: "PhotoApi") { action = [POST : "getPhoto"]}
        "/api/user/getPhotoApp"         (controller: "PhotoApi") { action = [POST : "getPhotoApp"]}

        "/api/user/overallRating"      (controller: "RatingApi") { action = [POST : "overallRating"]}
        "/api/user/infoRating"      (controller: "RatingApi") { action = [POST : "infoRating"]}
        "/api/user/photoRating"      (controller: "RatingApi") { action = [POST : "photoRating"]}
        "/api/user/facilitiesRating"      (controller: "RatingApi") { action = [POST : "facilitiesRating"]}

        /*---------------------Admin APIs-----------------------*/
        "/api/admin/addCategory"        (controller: "AdminApi") { action = [POST: "addCategory"] }
        "/api/admin/removePlace"        (controller: "AdminApi") { action = [POST: "removePlace"] }
        "/api/admin/addFacility"        (controller: "AdminApi") { action = [POST: "addFacility"] }
        "/api/admin/removeCategory"     (controller: "AdminApi") { action = [POST: "removeCategory"] }
        "/api/admin/removePhotos"       (controller: "AdminApi") { action = [POST: "removePhotos"] }
        "/api/admin/importUdid"         (controller: "AdminApi") { action = [POST: "importUdid"] }


    }
}
