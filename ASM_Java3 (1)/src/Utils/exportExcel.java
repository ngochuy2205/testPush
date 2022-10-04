/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Utils;

import Controllers.StudentController;
import Models.Student;
import Utils.*;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.BuiltinFormats;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author daung
 */
public class exportExcel {

    public static final int COLUMN_INDEX_ID = 0;
    public static final int COLUMN_INDEX_NAME = 1;
    public static final int COLUMN_INDEX_EMAIL = 2;
    public static final int COLUMN_INDEX_PHONE = 3;
    public static final int COLUMN_INDEX_SEX = 4;
    public static final int COLUMN_INDEX_ADDRESS = 5;
    public static final int COLUMN_INDEX_TA = 6;
    public static final int COLUMN_INDEX_TH = 7;
    public static final int COLUMN_INDEX_GDTC = 8;
    public static final int COLUMN_INDEX_TB = 9;

    private static CellStyle cellStyleFormatNumber = null;

    public static void export(String excelFilePath) throws IOException{
        List<Student> list = getStudents();
        writeExcel(list, excelFilePath);
    }

    public static void writeExcel(List<Student> list, String excelFilePath) throws IOException {
        // Create Workbook
        Workbook workbook = getWorkbook(excelFilePath);
        // Create sheet
        Sheet sheet = workbook.createSheet("Students"); // Create sheet with sheet name

        int rowIndex = 0;

        // Write header
        writeHeader(sheet, rowIndex);

        // Write data
        rowIndex++;
        for (Student std : list) {
            // Create row
            Row row = sheet.createRow(rowIndex);
            // Write data on row
            writeStudent(std, row);
            rowIndex++;
        }

        // Write footer
        // Auto resize column witdth
        int numberOfColumn = sheet.getRow(0).getPhysicalNumberOfCells();
        autosizeColumn(sheet, numberOfColumn);

        // Create file excel
        createOutputFile(workbook, excelFilePath);
        System.out.println("Done!!!");
    }

    // Create dummy data
    private static List<Student> getStudents() {
        return StudentController.getAllStudentInfo();
    }

    // Create workbook
    private static Workbook getWorkbook(String excelFilePath) throws IOException {
        Workbook workbook = null;

        if (excelFilePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook();
        } else if (excelFilePath.endsWith("xls")) {
            workbook = new HSSFWorkbook();
        } else {
            throw new IllegalArgumentException("The specified file is not Excel file");
        }

        return workbook;
    }

    // Write header with format
    private static void writeHeader(Sheet sheet, int rowIndex) {
        // create CellStyle
        CellStyle cellStyle = createStyleForHeader(sheet);

        // Create row
        Row row = sheet.createRow(rowIndex);

        // Create cells
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Mã SV");

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tên sinh viên");

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Email");

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Số điện thoại");

        cell = row.createCell(COLUMN_INDEX_SEX);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Giới tính");

        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Địa chỉ");

        cell = row.createCell(COLUMN_INDEX_TA);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tiếng Anh");

        cell = row.createCell(COLUMN_INDEX_TH);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Tin Học");

        cell = row.createCell(COLUMN_INDEX_GDTC);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("GDTC");

        cell = row.createCell(COLUMN_INDEX_TB);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("Trung bình");
    }

    // Write data
    private static void writeStudent(Student st, Row row) {
        Cell cell = row.createCell(COLUMN_INDEX_ID);
        cell.setCellValue(st.getId());

        cell = row.createCell(COLUMN_INDEX_NAME);
        cell.setCellValue(st.getName());

        cell = row.createCell(COLUMN_INDEX_EMAIL);
        cell.setCellValue(st.getEmail());

        cell = row.createCell(COLUMN_INDEX_PHONE);
        cell.setCellValue(st.getPhone());

        cell = row.createCell(COLUMN_INDEX_SEX);
        cell.setCellValue(st.getSex());

        cell = row.createCell(COLUMN_INDEX_ADDRESS);
        cell.setCellValue(st.getAddress());

        cell = row.createCell(COLUMN_INDEX_TA);
        cell.setCellValue(st.getTienganh());

        cell = row.createCell(COLUMN_INDEX_TH);
        cell.setCellValue(st.getTinhoc());

        cell = row.createCell(COLUMN_INDEX_GDTC);
        cell.setCellValue(st.getGdtc());

        cell = row.createCell(COLUMN_INDEX_TB);
        cell.setCellValue(st.getTB());

    }

    // Create CellStyle for header
    private static CellStyle createStyleForHeader(Sheet sheet) {
        // Create font
        Font font = sheet.getWorkbook().createFont();
        font.setFontName("Times New Roman");
        font.setBold(true);
        font.setFontHeightInPoints((short) 14); // font size
        font.setColor(IndexedColors.BLACK.getIndex()); // text color

        // Create CellStyle
        CellStyle cellStyle = sheet.getWorkbook().createCellStyle();
        cellStyle.setFont(font);
        cellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        return cellStyle;
    }

    // Auto resize column width
    private static void autosizeColumn(Sheet sheet, int lastColumn) {
        for (int columnIndex = 0; columnIndex < lastColumn; columnIndex++) {
            sheet.autoSizeColumn(columnIndex);
        }
    }

    // Create output file
    private static void createOutputFile(Workbook workbook, String excelFilePath) throws IOException {
        try ( OutputStream os = new FileOutputStream(excelFilePath)) {
            workbook.write(os);
        }
    }
}
