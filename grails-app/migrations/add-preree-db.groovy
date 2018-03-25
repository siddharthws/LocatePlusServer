databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521969217789-1") {
        dropColumn(columnName: "role", tableName: "lp_user")
    }
}
