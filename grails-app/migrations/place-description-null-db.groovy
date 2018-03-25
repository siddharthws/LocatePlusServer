databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521711341636-1") {
        dropNotNullConstraint(columnDataType: "varchar(255)", columnName: "description", tableName: "place")
    }
}
