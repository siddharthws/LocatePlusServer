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
    }
}
