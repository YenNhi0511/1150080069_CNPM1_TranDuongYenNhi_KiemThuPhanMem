package tests;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * TestDataGenerator - Tao file login_data.xlsx voi 3 sheet.
 * Chay main() mot lan de sinh file test data.
 * Lab 9 Bai 3: Tao du lieu Excel cho Data-Driven testing.
 */
public class TestDataGenerator {

    public static void main(String[] args) {
        String outputPath = "src/test/resources/testdata/login_data.xlsx";
        generateLoginData(outputPath);
        System.out.println("Da tao file: " + outputPath);
    }

    public static void generateLoginData(String filePath) {
        try (Workbook workbook = new XSSFWorkbook()) {

            // ===== Sheet 1: SmokeCases (Happy path - dang nhap thanh cong) =====
            Sheet smokeSheet = workbook.createSheet("SmokeCases");
            createHeader(smokeSheet, "username", "password", "expected_url", "description");

            createRow(smokeSheet, 1, "standard_user", "secret_sauce",
                    "inventory.html", "Dang nhap thanh cong voi standard_user");
            createRow(smokeSheet, 2, "problem_user", "secret_sauce",
                    "inventory.html", "Dang nhap thanh cong voi problem_user");
            createRow(smokeSheet, 3, "performance_glitch_user", "secret_sauce",
                    "inventory.html", "Dang nhap thanh cong voi performance_glitch_user");

            // ===== Sheet 2: NegativeCases (Dang nhap that bai) =====
            Sheet negativeSheet = workbook.createSheet("NegativeCases");
            createHeader(negativeSheet, "username", "password", "expected_error", "description");

            createRow(negativeSheet, 1, "locked_out_user", "secret_sauce",
                    "locked out", "Tai khoan bi khoa");
            createRow(negativeSheet, 2, "standard_user", "wrong_password",
                    "do not match", "Sai password");
            createRow(negativeSheet, 3, "invalid_user", "secret_sauce",
                    "do not match", "User khong ton tai");
            createRow(negativeSheet, 4, "", "secret_sauce",
                    "Username is required", "De trong username");
            createRow(negativeSheet, 5, "standard_user", "",
                    "Password is required", "De trong password");

            // ===== Sheet 3: BoundaryCases (Du lieu bien) =====
            Sheet boundarySheet = workbook.createSheet("BoundaryCases");
            createHeader(boundarySheet, "username", "password", "expected_error", "description");

            createRow(boundarySheet, 1,
                    "a".repeat(100), "secret_sauce",
                    "do not match", "Username dai 100 ky tu");
            createRow(boundarySheet, 2,
                    "standard_user", "a".repeat(100),
                    "do not match", "Password dai 100 ky tu");
            createRow(boundarySheet, 3,
                    "<script>alert('xss')</script>", "secret_sauce",
                    "do not match", "Username chua ky tu dac biet (XSS)");
            createRow(boundarySheet, 4,
                    "admin' OR '1'='1", "secret_sauce",
                    "do not match", "Username chua SQL injection pattern");

            // Auto-size columns cho dep
            for (Sheet sheet : new Sheet[]{smokeSheet, negativeSheet, boundarySheet}) {
                for (int i = 0; i < 4; i++) {
                    sheet.autoSizeColumn(i);
                }
            }

            // Ghi file
            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            throw new RuntimeException("Khong the tao file Excel: " + filePath, e);
        }
    }

    private static void createHeader(Sheet sheet, String... headers) {
        Row headerRow = sheet.createRow(0);
        CellStyle headerStyle = sheet.getWorkbook().createCellStyle();
        Font headerFont = sheet.getWorkbook().createFont();
        headerFont.setBold(true);
        headerStyle.setFont(headerFont);

        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
            cell.setCellStyle(headerStyle);
        }
    }

    private static void createRow(Sheet sheet, int rowNum, String... values) {
        Row row = sheet.createRow(rowNum);
        for (int i = 0; i < values.length; i++) {
            row.createCell(i).setCellValue(values[i]);
        }
    }
}
