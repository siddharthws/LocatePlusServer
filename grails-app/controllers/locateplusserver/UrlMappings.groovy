package locateplusserver

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*-------------------- Auth APIs ----------------------*/
        "/api/auth/register"        (controller: "AuthApi") { action = [GET: "register"] }

        /*-------------------- Error Handling ----------------------*/
        "500"(controller: "Utils", action: "handleError")
        "404"(controller: "Utils", action: "handle404")

        /*---------------------User APIs-----------------------*/
        "/api/user/addPlace"        (controller: "UserApi") { action = [POST : "addPlace"]}
        "/api/user/getPlaces"        (controller: "UserApi") { action = [GET  : "getPlaces"]}

        /*---------------------Admin APIs-----------------------*/
        "/api/admin/addCategory"        (controller: "AdminApi") { action = [POST: "addCategory"] }
        "/api/admin/removePlace"        (controller: "AdminApi") { action = [POST: "removePlace"] }
    }
}
