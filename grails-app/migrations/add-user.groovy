databaseChangeLog = {

    changeSet(author: "Siddharth (generated)", id: "1521104146235-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "Siddharth (generated)", id: "1521104146235-2") {
        createTable(tableName: "lp_user") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "lp_userPK")
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

            column(name: "imei", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }
}
