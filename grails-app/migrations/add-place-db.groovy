databaseChangeLog = {

    changeSet(author: "Rohan (generated)", id: "1521201677059-2") {
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

    changeSet(author: "Rohan (generated)", id: "1521201677059-3") {
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

    changeSet(author: "Rohan (generated)", id: "1521201677059-5") {
        createTable(tableName: "place_details") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "place_detailsPK")
            }

            column(name: "version", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "address", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "date_created", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "longitude", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "last_updated", type: "TIMESTAMP WITHOUT TIME ZONE") {
                constraints(nullable: "false")
            }

            column(name: "stars", type: "FLOAT8") {
                constraints(nullable: "false")
            }

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "latitude", type: "FLOAT8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-6") {
        createTable(tableName: "place_details_facility") {
            column(name: "place_details_facilities_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "facility_id", type: "BIGINT")
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-7") {
        createTable(tableName: "place_details_rating") {
            column(name: "place_details_ratings_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rating_id", type: "BIGINT")
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-8") {
        createTable(tableName: "place_details_review") {
            column(name: "place_details_reviews_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "review_id", type: "BIGINT")
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-9") {
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

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-10") {
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

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "review", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-11") {
        addForeignKeyConstraint(baseColumnNames: "facility_id", baseTableName: "place_details_facility", constraintName: "FK1e6r5twib1u5neljwug32u73r", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "facility")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-12") {
        addForeignKeyConstraint(baseColumnNames: "rating_id", baseTableName: "place_details_rating", constraintName: "FK31t5pxcompiqt2olgvjrkv2ap", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rating")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-13") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "rating", constraintName: "FK5k7plokyoxiokq6nxfg0s63pt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-14") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "place_details", constraintName: "FK9o1kd97af7cf4jj3ipr5pb8j5", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-15") {
        addForeignKeyConstraint(baseColumnNames: "place_details_ratings_id", baseTableName: "place_details_rating", constraintName: "FKg24g7o1e6n0r4tmkqs3fq4qs2", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place_details")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-16") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "review", constraintName: "FKgh2wroh94cb52bbpl9c9waidv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-17") {
        addForeignKeyConstraint(baseColumnNames: "place_details_reviews_id", baseTableName: "place_details_review", constraintName: "FKhvslxckjufo2qkvcihta6iluo", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place_details")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-18") {
        addForeignKeyConstraint(baseColumnNames: "place_details_facilities_id", baseTableName: "place_details_facility", constraintName: "FKosujjxwa4btlfi0v6wh4m7vdn", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place_details")
    }

    changeSet(author: "Rohan (generated)", id: "1521201677059-19") {
        addForeignKeyConstraint(baseColumnNames: "review_id", baseTableName: "place_details_review", constraintName: "FKqj3m8oub25d0cuouj39t5p7ui", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "review")
    }
}
