package locateplusserver.services

import grails.gorm.transactions.Transactional
import grails.converters.JSON
import locateplusserver.domains.Udid
import org.apache.poi.ss.usermodel.Cell
import org.apache.poi.ss.usermodel.DataFormatter
import org.apache.poi.xssf.usermodel.XSSFWorkbook

@Transactional
class ImportService {

    def parseExcel(def file) {
        // Validate File
        if (file.empty) {
            throw new locateplusserver.ApiException("Invalid File uploaded", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }

        // Convert file to workbook
        def workbook
        try {
            workbook = new XSSFWorkbook(file.getInputStream())
        } catch (Exception e) {
            throw new locateplusserver.ApiException("Invalid File uploaded", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }

        // Get sheet from workbook
        def sheet = workbook.getSheetAt(0)
        DataFormatter df = new DataFormatter()

        // Get columns from sheet
        def columns = []
        def headerRow = sheet.getRow(0)
        for (cell in headerRow.cellIterator()) {
            // Validate cell
            if (!cell) {
                throw new locateplusserver.ApiException("Column names cannot be empty", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
            }

            // Get and validate cell value
            String cellValue = df.formatCellValue(cell).trim()

            // Check for null value
            if (!cellValue) {
                throw new locateplusserver.ApiException("Column names cannot be empty", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
            }

            // Check for duplicate columns
            if (columns.contains(cellValue)) {
                throw new locateplusserver.ApiException("Duplicate column names are not allowed", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
            }

            columns.push(cellValue)
        }

        // Get rows
        def rows = []
        boolean bHeaderFlag = true
        for (sheetRow in sheet.rowIterator()) {
            // Skip first row
            if (bHeaderFlag) {
                bHeaderFlag = false
                continue
            }

            // Iterate through all cells in this row
            def row = []
            for (int i = 0; i < columns.size(); i++) {
                def cell = sheetRow.getCell(i)
                String value = ""

                if (cell) {
                    // Parse value based on cell type
                    if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
                        switch (cell.getCachedFormulaResultType()) {
                            case Cell.CELL_TYPE_NUMERIC:
                                value = String.valueOf(cell.getNumericCellValue())
                                break
                            case Cell.CELL_TYPE_STRING:
                                value = cell.getRichStringCellValue().toString()
                                break
                        }
                    } else {
                        value = df.formatCellValue(cell)
                    }
                }

                // Trim value
                value = value.trim()

                // Push to row
                row.push(value)
            }

            // Ignore row if all values are blank
            def nonBlankVals = row.findAll { String it -> !it }
            if (nonBlankVals.size() != row.size()) {
                // Add row to rows
                rows.push(row)
            }
        }

        // Ensure atleast 1 row is present
        if (!rows.size()) {
            throw new locateplusserver.ApiException("No Rows Found", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }

        return [
                columns: columns,
                rows   : rows
        ]
    }

    def checkColumns(def columns) {
        if (!columns.contains("udid")) {
            throw new locateplusserver.ApiException("udid column not found", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }
        if (!columns.contains("name")) {
            throw new locateplusserver.ApiException("Name column not found", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }
    }

    def checkRows(def rows, def columns) {

        int udId = columns.indexOf("Udid")
        String name = columns.indexOf("Name")

        rows.eachWithIndex { row, i ->
            // Get all rows with this ID
            def duplicates = rows.findAll { it -> it[udId] == row[udId] }

            if (duplicates.size() > 1) {
                // Throw exception
                throw new locateplusserver.ApiException("Duplicate UDID found")
            }
        }


        rows.eachWithIndex { row, i ->
            // Get all rows with this ID
            def duplicates = rows.findAll { it -> it[udId] == null }

            if (duplicates.size() > 1) {
                // Throw exception
                throw new locateplusserver.ApiException("Null name found")
            }
        }
    }

    def parseRow(def columns, def row) {
        // Get and validate mandatory columns
        int udidIdx = columns.indexOf("udid")
        def udid = row[udidIdx]
        log.error('udid'+udid)
        if (!udid) {
            throw new locateplusserver.ApiException("Invalid udid", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }

        int nameIdx = columns.indexOf("name")
        def name = row[nameIdx]
        log.error('name'+name)
        if (!name) {
            throw new locateplusserver.ApiException("Invalid Name", locateplusserver.Constants.HttpCodes.BAD_REQUEST)
        }
            def udidJson = [
                    Udid: udid,
                    Name: name
            ]

            udidJson
    }
}
