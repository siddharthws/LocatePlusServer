databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521389506919-1") {
        createSequence(sequenceName: "hibernate_sequence")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-2") {
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

    changeSet(author: "amit (generated)", id: "1521389506919-3") {
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

            column(name: "place_id", type: "BIGINT") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521389506919-4") {
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

            column(name: "imei", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521389506919-5") {
        createTable(tableName: "place") {
            column(autoIncrement: "true", name: "id", type: "BIGINT") {
                constraints(primaryKey: "true", primaryKeyName: "placePK")
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

            column(name: "stars", type: "FLOAT8") {
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

            column(name: "name", type: "VARCHAR(255)") {
                constraints(nullable: "false")
            }

            column(name: "user_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "category_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "is_removed", type: "BOOLEAN") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521389506919-6") {
        createTable(tableName: "place_rating") {
            column(name: "place_ratings_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "rating_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521389506919-7") {
        createTable(tableName: "place_review") {
            column(name: "place_reviews_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "review_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521389506919-8") {
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

    changeSet(author: "amit (generated)", id: "1521389506919-9") {
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

    changeSet(author: "amit (generated)", id: "1521389506919-10") {
        addForeignKeyConstraint(baseColumnNames: "category_id", baseTableName: "place", constraintName: "FK3297dq7rblawjc9n4kx9htui4", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "category")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-11") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "rating", constraintName: "FK5k7plokyoxiokq6nxfg0s63pt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-12") {
        addForeignKeyConstraint(baseColumnNames: "place_ratings_id", baseTableName: "place_rating", constraintName: "FKd27rlr4xtqmphg076kg4vh9nr", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-13") {
        addForeignKeyConstraint(baseColumnNames: "rating_id", baseTableName: "place_rating", constraintName: "FKej6h9etbla9vqtjae2k2ej9r6", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "rating")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-14") {
        addForeignKeyConstraint(baseColumnNames: "review_id", baseTableName: "place_review", constraintName: "FKgfgrb6utiqapxfwurn6cfiunx", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "review")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-15") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "review", constraintName: "FKgh2wroh94cb52bbpl9c9waidv", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-16") {
        addForeignKeyConstraint(baseColumnNames: "place_reviews_id", baseTableName: "place_review", constraintName: "FKh69uju6p03nlbnfyl620hr1yg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-17") {
        addForeignKeyConstraint(baseColumnNames: "user_id", baseTableName: "place", constraintName: "FKs8292qa48t0vak2d2yo728q8g", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521389506919-18") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "facility", constraintName: "FKt39s09idqk5q56x5da9v6umtt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }
}
