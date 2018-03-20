databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521527250025-1") {
        addColumn(tableName: "lp_user") {
            column(name: "update_required", type: "boolean") {
                constraints(nullable: "false")
            }
        }
    }
}
