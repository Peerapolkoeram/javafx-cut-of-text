package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.utils.FilesUtils;
import lombok.SneakyThrows;
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
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class FileServiceImpTest {

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
            Sheet sheet = workbook.createSheet("sheetNameOne");
            FileOutputStream outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }

    @Test
    @SneakyThrows
    public void getSheetNameNotNull() {
        //prepare data
        String pathFile = path.resolve(fileName).toString();
        FileInputStream fileInputStream = getInputFile(pathFile);
        Workbook sheets = new XSSFWorkbook(fileInputStream);

        //call service
        List<SheetRecordModel> result = fileServiceImp.getSheetExcel(sheets);

        //check
        Assertions.assertNotNull(result);
    }

    @SneakyThrows
    public FileInputStream getInputFile(String pathFile) {
        return new FileInputStream(pathFile);
    }

}