databaseChangeLog = {

    changeSet(author: "amit (generated)", id: "1521817992971-1") {
        createTable(tableName: "place_photo") {
            column(name: "place_photos_id", type: "BIGINT") {
                constraints(nullable: "false")
            }

            column(name: "photo_id", type: "BIGINT")
        }
    }

    changeSet(author: "amit (generated)", id: "1521817992971-2") {
        addForeignKeyConstraint(baseColumnNames: "photo_id", baseTableName: "place_photo", constraintName: "FKetb2lkflakjt2pa4ybuxrpiop", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "photo")
    }

    changeSet(author: "amit (generated)", id: "1521817992971-3") {
        addForeignKeyConstraint(baseColumnNames: "place_photos_id", baseTableName: "place_photo", constraintName: "FKi3i7jgfjn4tybpevywguvrr9a", deferrable: "false", initiallyDeferred: "false", referencedColumnNames: "id", referencedTableName: "place")
    }

    changeSet(author: "amit (generated)", id: "1521817992971-4") {
        dropForeignKeyConstraint(baseTableName: "photo", constraintName: "FKsv2aa83c398y2xp3j01yi92rh")
    }

    changeSet(author: "amit (generated)", id: "1521817992971-5") {
        dropColumn(columnName: "place_id", tableName: "photo")
    }
}
