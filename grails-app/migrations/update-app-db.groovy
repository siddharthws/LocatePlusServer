databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521697280298-1") {
        createTable(tableName: "update") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "updatePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "fc_status", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "place_status", type: "FLOAT8") {
                constraints(nullable: "false")
            }
        }
    }
}
