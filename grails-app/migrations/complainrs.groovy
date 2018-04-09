databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1522486229560-1") {
        createTable(tableName: "complaints") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "complaintsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "title", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "resolved_count", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522486229560-2") {
        createTable(tableName: "complaints_photo") {
            column(name: "complaints_photos_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "photo_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1522486229560-3") {
        addForeignKeyConstraint(baseColumnNames: "complaints_photos_id", baseTableName: "complaints_photo", constraintName: "FK4ym2yr0em20mx65h5ro609epu", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "complaints")
    }

    changeSet(author: "amit (generated)", id: "1522486229560-4") {
        addForeignKeyConstraint(baseColumnNames: "photo_id", baseTableName: "complaints_photo", constraintName: "FK51idp7ibl3r10khor5ttp5d6q", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "photo")
    }
}
