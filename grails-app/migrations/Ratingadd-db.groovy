databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1522062835933-1") {
        addColumn(tableName: "rating") {
            column(name: "facilties_rating", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522062835933-2") {
        addColumn(tableName: "rating") {
            column(name: "info_rating", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522062835933-3") {
        addColumn(tableName: "rating") {
            column(name: "over_all_rating", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522062835933-4") {
        addColumn(tableName: "rating") {
            column(name: "photo_rating", type: "int4") {
                constraints(nullable: "false")
            }
        }
    }

    changeSet(author: "amit (generated)", id: "1522062835933-5") {
        dropColumn(columnName: "rating", tableName: "rating")
    }
}
