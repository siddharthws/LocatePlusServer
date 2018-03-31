databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1522481214909-1") {
        addColumn(tableName: "place") {
            column(name: "contact_number", type: "varchar(255)")
        }
    }
}
