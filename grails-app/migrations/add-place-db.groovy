databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521968596627-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-2") {
        createTable(tableName: "admin") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "adminPK")
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

            column(name: "password", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-3") {
        createTable(tableName: "category") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "categoryPK")
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
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-4") {
        createTable(tableName: "facility") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "facilityPK")
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
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-5") {
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

            column(name: "role", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "update_required", type: "BOOLEAN") {
                constraints(nullable: "false")
            }

            column(name: "imei", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-6") {
        createTable(tableName: "photo") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "photoPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "uuid", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "in_appropriate_count", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "is_removed", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-7") {
        createTable(tableName: "place") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "placePK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "review_status", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "stars", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "owner_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "latitude", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "address", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "longitude", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "photo_status", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "description", type: "VARCHAR(255)")

            column(name: "is_removed", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-8") {
        createTable(tableName: "place_facility") {
            column(name: "place_facilities_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "facility_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-9") {
        createTable(tableName: "place_photo") {
            column(name: "place_photos_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "photo_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-10") {
        createTable(tableName: "rating") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "ratingPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rating", type: "INT") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "owner_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "place_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-11") {
        createTable(tableName: "review") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "reviewPK")
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

            column(name: "owner_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "place_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "review", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521968596627-12") {
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

    changeSet(author: "amit (generated)", id: "1521968596627-13") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "place", constraintName: "FK3297dq7rblawjc9n4kx9htui4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-14") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "review", constraintName: "FK5m5ensas61jv3kcodfanv3r0t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-15") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "rating", constraintName: "FKd24eakv927n7mxx4iaa0t10lw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-16") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "rating", constraintName: "FKdy9k3pqrse1jchq1o066dktsq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-17") {
        addForeignKeyConstraint(baseColumnNames: "photo_id", baseTableName: "place_photo", constraintName: "FKetb2lkflakjt2pa4ybuxrpiop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "photo")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-18") {
        addForeignKeyConstraint(baseColumnNames: "place_facilities_id", baseTableName: "place_facility", constraintName: "FKetsccj2o0pkphp9nr0njan2kt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-19") {
        addForeignKeyConstraint(baseColumnNames: "place_photos_id", baseTableName: "place_photo", constraintName: "FKi3i7jgfjn4tybpevywguvrr9a", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-20") {
        addForeignKeyConstraint(baseColumnNames: "facility_id", baseTableName: "place_facility", constraintName: "FKiv81809x5uq78plh0m9an7sqa", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "facility")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-21") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "review", constraintName: "FKn429agmmvh298piqrnnd4gbfg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521968596627-22") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "place", constraintName: "FKne508p8cpndcvao3mi8nb2vdq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }
}
