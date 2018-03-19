databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521443841127-1") {
        createTable(tableName: "place_facility") {
            column(name: "place_facilities_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "facility_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-2") {
        addColumn(tableName: "place") {
            column(name: "owner_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-3") {
        addColumn(tableName: "rating") {
            column(name: "owner_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-4") {
        addColumn(tableName: "review") {
            column(name: "owner_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-5") {
        addColumn(tableName: "rating") {
            column(name: "place_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-6") {
        addColumn(tableName: "review") {
            column(name: "place_id", type: "int8") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1521443841127-7") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "review", constraintName: "FK5m5ensas61jv3kcodfanv3r0t", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-8") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "rating", constraintName: "FKd24eakv927n7mxx4iaa0t10lw", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-9") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "rating", constraintName: "FKdy9k3pqrse1jchq1o066dktsq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-10") {
        addForeignKeyConstraint(baseColumnNames: "place_facilities_id", baseTableName: "place_facility", constraintName: "FKetsccj2o0pkphp9nr0njan2kt", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-11") {
        addForeignKeyConstraint(baseColumnNames: "facility_id", baseTableName: "place_facility", constraintName: "FKiv81809x5uq78plh0m9an7sqa", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "facility")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-12") {
        addForeignKeyConstraint(baseColumnNames: "place_id", baseTableName: "review", constraintName: "FKn429agmmvh298piqrnnd4gbfg", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-13") {
        addForeignKeyConstraint(baseColumnNames: "owner_id", baseTableName: "place", constraintName: "FKne508p8cpndcvao3mi8nb2vdq", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "lp_user")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-14") {
        dropForeignKeyConstraint(baseTableName: "rating", constraintName: "FK5k7plokyoxiokq6nxfg0s63pt")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-15") {
        dropForeignKeyConstraint(baseTableName: "place_rating", constraintName: "FKd27rlr4xtqmphg076kg4vh9nr")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-16") {
        dropForeignKeyConstraint(baseTableName: "place_rating", constraintName: "FKej6h9etbla9vqtjae2k2ej9r6")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-17") {
        dropForeignKeyConstraint(baseTableName: "place_review", constraintName: "FKgfgrb6utiqapxfwurn6cfiunx")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-18") {
        dropForeignKeyConstraint(baseTableName: "review", constraintName: "FKgh2wroh94cb52bbpl9c9waidv")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-19") {
        dropForeignKeyConstraint(baseTableName: "place_review", constraintName: "FKh69uju6p03nlbnfyl620hr1yg")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-20") {
        dropForeignKeyConstraint(baseTableName: "place", constraintName: "FKs8292qa48t0vak2d2yo728q8g")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-21") {
        dropForeignKeyConstraint(baseTableName: "facility", constraintName: "FKt39s09idqk5q56x5da9v6umtt")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-22") {
        dropTable(tableName: "place_rating")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-23") {
        dropTable(tableName: "place_review")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-24") {
        dropColumn(columnName: "place_id", tableName: "facility")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-25") {
        dropColumn(columnName: "user_id", tableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-26") {
        dropColumn(columnName: "user_id", tableName: "rating")
    }

    changeSet(author: "amit (generated)", id: "1521443841127-27") {
        dropColumn(columnName: "user_id", tableName: "review")
    }
}
