databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521701304676-1") {
        addColumn(tableName: "place") {
            column(name: "description", type: "varchar(255)") {
                constraints(nullable: "false")
            }
        }
    }
}
