package utils.Excel;

import io.cucumber.java8.Fi;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import static testsForExcel.BaseTest.testDataExcelFileName;

public class ExcelUtil {

    public static final String currentDir = System.getProperty("user.dir");
    public static String testDataExcelPath = null;
    private static XSSFWorkbook excelWBook;
    private static XSSFSheet excelWSheet;
    private static XSSFCell cell;
    private static XSSFRow row;
    public static int rowNumber;
    public static int columnNumber;

    public static void setRowNumber(int pRowNumber) {
        rowNumber = pRowNumber;
    }

    public static int getRowNumber() {
        return rowNumber;
    }

    public static void setColumnNumber(int pColumnNumber) {
        columnNumber = pColumnNumber;
    }

    public static int getColumnNumber() {
        return columnNumber;
    }

    public static void setExcelFileSheet(String sheetName) throws IOException {
        if(Platform.getCurrent().toString().equalsIgnoreCase("MAC")) {
            testDataExcelPath = currentDir + "//src//test//resources//ExcelResources";
        } else if(Platform.getCurrent().toString().equalsIgnoreCase("WIN")) {
            testDataExcelPath = currentDir + "\\src\\test\\resources\\ExcelResources";
        }
        try {
            FileInputStream excelFile = new FileInputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook = new XSSFWorkbook(excelFile);
            excelWSheet = excelWBook.getSheet(sheetName);
        } catch (Exception e) {
                throw (e);
        }
    }

    public static String getCellData(int rowNum, int colNum) {
        try {
            cell = excelWSheet.getRow(rowNum).getCell(colNum);
            DataFormatter formatter = new DataFormatter();
            String cellData = formatter.formatCellValue(cell);
            return cellData;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static XSSFRow getRowData(int rowNum) {
        try {
            row = excelWSheet.getRow(rowNum);
            return row;
        } catch (Exception e) {
            throw (e);
        }
    }

    public static void setCellData(String value, int rowNum, int colNum) throws IOException {
        try {
            row = excelWSheet.getRow(rowNum);
            cell = row.getCell(colNum);
            if(cell == null) {
                cell = row.createCell(colNum);
            }
            cell.setCellValue(value);

            FileOutputStream fos = new FileOutputStream(testDataExcelPath + testDataExcelFileName);
            excelWBook.write(fos);
            fos.flush();
            fos.close();
        } catch (IOException e) {
            throw(e);
        }
    }


}
