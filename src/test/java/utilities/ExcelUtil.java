package utilities;

import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {

    public static Object[][] readExcelData(String fileName, String sheetName) throws IOException {
        FileInputStream fis = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        Sheet sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getPhysicalNumberOfRows();
        int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

        Object[][] data = new Object[rowCount - 1][colCount];
        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <rowCount; i++) { // skip header row
            for (int j = 0; j < colCount; j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                data[i - 1][j] = formatter.formatCellValue(cell);
            }
        }
        
        
/*
        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                Cell cell = sheet.getRow(i).getCell(j);
                if (cell == null) {
                    data[i - 1][j] = "";
                } else {
                    switch (cell.getCellType()) {
                        case STRING:
                            data[i - 1][j] = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                data[i - 1][j] = cell.getDateCellValue().toString();
                            } else {
                                data[i - 1][j] = String.valueOf((long) cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            data[i - 1][j] = String.valueOf(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            data[i - 1][j] = cell.getCellFormula();
                            break;
                        case BLANK:
                        default:
                            data[i - 1][j] = "";
                            break;
                    }
                }
            }
        }*/

        workbook.close();
        fis.close();
        return data;
    }
}
