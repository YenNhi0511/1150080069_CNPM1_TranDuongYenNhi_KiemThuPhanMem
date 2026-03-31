package framework.utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * ExcelReader - Doc test data tu file Excel (.xlsx) bang Apache POI.
 * Ho tro xu ly 4 kieu cell: STRING, NUMERIC, BOOLEAN, FORMULA.
 * Ho tro doc theo ten sheet.
 */
public class ExcelReader {

    /**
     * Doc toan bo data tu 1 sheet trong file Excel.
     * Dong dau tien la header, cac dong sau la data.
     *
     * @param filePath duong dan toi file .xlsx
     * @param sheetName ten sheet can doc
     * @return mang 2 chieu Object[][] de dung voi @DataProvider
     */
    public static Object[][] readExcelData(String filePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (InputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Khong tim thay sheet: " + sheetName);
            }

            // Dong dau la header => bo qua
            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows <= 1) {
                return new Object[0][0];
            }

            Row headerRow = sheet.getRow(0);
            int totalCols = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Object[] rowData = new Object[totalCols];
                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = getCellValue(cell);
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Khong the doc file Excel: " + filePath, e);
        }

        return data.toArray(new Object[0][0]);
    }

    /**
     * Doc data tu file trong classpath (resources).
     */
    public static Object[][] readExcelFromResources(String resourcePath, String sheetName) {
        List<Object[]> data = new ArrayList<>();

        try (InputStream fis = ExcelReader.class.getClassLoader().getResourceAsStream(resourcePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            if (fis == null) {
                throw new RuntimeException("Khong tim thay resource: " + resourcePath);
            }

            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Khong tim thay sheet: " + sheetName);
            }

            int totalRows = sheet.getPhysicalNumberOfRows();
            if (totalRows <= 1) {
                return new Object[0][0];
            }

            Row headerRow = sheet.getRow(0);
            int totalCols = headerRow.getPhysicalNumberOfCells();

            for (int i = 1; i < totalRows; i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;

                Object[] rowData = new Object[totalCols];
                for (int j = 0; j < totalCols; j++) {
                    Cell cell = row.getCell(j);
                    rowData[j] = getCellValue(cell);
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            throw new RuntimeException("Khong the doc file Excel tu resources: " + resourcePath, e);
        }

        return data.toArray(new Object[0][0]);
    }

    /**
     * Xu ly gia tri cell: STRING, NUMERIC, BOOLEAN, FORMULA, null.
     */
    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                // Kiem tra co phai Date khong
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                // Tra ve so nguyen neu khong co phan thap phan
                double numericValue = cell.getNumericCellValue();
                if (numericValue == Math.floor(numericValue)) {
                    return String.valueOf((long) numericValue);
                }
                return String.valueOf(numericValue);

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                try {
                    return cell.getStringCellValue();
                } catch (Exception e) {
                    return String.valueOf(cell.getNumericCellValue());
                }

            case BLANK:
                return "";

            default:
                return "";
        }
    }
}
