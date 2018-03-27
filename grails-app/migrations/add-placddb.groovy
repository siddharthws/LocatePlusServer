databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1522130568767-1") {
        addColumn(tableName: "place") {
            column(name: "rating_status", type: "float8") {
                constraints(nullable: "false")
            }
        }
    }
}
