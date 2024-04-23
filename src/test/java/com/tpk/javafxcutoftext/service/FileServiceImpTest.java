package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.utils.FilesUtils;
import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FileServiceImpTest {

    @Spy
    @InjectMocks
    private FileServiceImp fileServiceImp;

    @Mock
    private FilesUtils filesUtils;

    @TempDir
    private static Path path;

    private static final String fileName = "data.xlsx";

    @BeforeAll
    @SneakyThrows
    public static void createExcelFile() {
        path = Files.createTempDirectory("TempDir");
        File file = path.resolve(fileName).toFile();
        try (Workbook workbook = new XSSFWorkbook();) {
            createSheetOne(workbook);
            createSheetTwo(workbook);
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    public static void createSheetOne(Workbook workbook) {
        Sheet sheet1 = workbook.createSheet("SheetNameOne");
        Row row1 = sheet1.createRow(0);
        row1.createCell(0).setCellValue("Name");
        row1.createCell(1).setCellValue("Age");
        Row row2 = sheet1.createRow(1);
        row2.createCell(0).setCellValue("John");
        row2.createCell(1).setCellValue(30);
        Row row3 = sheet1.createRow(2);
        row3.createCell(0).setCellValue("Alice");
        row3.createCell(1).setCellValue(25);
    }

    private static void createSheetTwo(Workbook workbook) {
        Sheet sheet2 = workbook.createSheet("Sheet2");
        Row row1 = sheet2.createRow(0);
        row1.createCell(0).setCellValue("City");
        row1.createCell(1).setCellValue("Population");

        Row row2 = sheet2.createRow(1);
        row2.createCell(0).setCellValue("Tokyo");
        row2.createCell(1).setCellValue(13929286);

        Row row3 = sheet2.createRow(2);
        row3.createCell(0).setCellValue("New York");
        row3.createCell(1).setCellValue(8336697);
    }

    @Test
    @SneakyThrows
    public void verifySheetNameNotNull() {
        //prepare data
        String pathFile = path.resolve(fileName).toString();
        FileInputStream fileInputStream = getInputFile(pathFile);
        Workbook sheets = new XSSFWorkbook(fileInputStream);
        //call service
        List<List<SheetRecordModel>> result = fileServiceImp.getSheetExcel(sheets);
        //check
        Assertions.assertNotNull(result);
    }

    @Test
    @SneakyThrows
    public void verifyDataInExcel() {
        //prepare data
        List<Integer> selectRows = List.of(0, 1);
        String pathFile = path.resolve(fileName).toString();
        FileInputStream inputStream = new FileInputStream(pathFile);
        ExcelRecordModel excelRecordModel = ExcelRecordModel.builder()
                .sheetRecordModel(SheetRecordModel.builder()
                                .sheetNumber(0)
                                .sheetName("SheetNameOne")
                                .build())
                .pathFile(pathFile)
                .selectColumn(selectRows)
                .condition("GET_DATA")
                .startRow(0)
                .build();
        // mock method
//        Mockito.when(filesUtils.fileInputStream(Mockito.any())).thenReturn(inputStream);
//        Mockito.when(filesUtils.workbook(Mockito.any())).thenReturn(new XSSFWorkbook(inputStream));
        //call service
        List<List<SheetRecordModel>> result = fileServiceImp.getDataInExcel(excelRecordModel);
        //verify
        Assertions.assertNotNull(result);
        result.forEach(System.out::println);
    }

    @SneakyThrows
    public FileInputStream getInputFile(String pathFile) {
        return new FileInputStream(pathFile);
    }

}