databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1522448149385-1") {
        addColumn(tableName: "rating") {
            column(name: "facilities_rating", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522448149385-2") {
        dropColumn(columnName: "facilties_rating", tableName: "rating")
    }
}
