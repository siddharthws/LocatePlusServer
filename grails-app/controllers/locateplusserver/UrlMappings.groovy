package locateplusserver

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        /*-------------------- Error Handling ----------------------*/
        "500"(controller: "Utils", action: "handleError")
        "404"(controller: "Utils", action: "handle404")
    }
}
