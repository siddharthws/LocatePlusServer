databaseChangeLog = {

    changeSet(author: "Rohan (generated)", id: "1522426213082-1") {
        createTable(tableName: "udid") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "udidPK")
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

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "udid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Rohan (generated)", id: "1522426213082-2") {
        addColumn(tableName: "lp_user") {
            column(name: "udid", type: "varchar(255)") {
            }
        }
    }
}
