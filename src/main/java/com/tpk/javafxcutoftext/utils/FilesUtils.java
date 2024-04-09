package com.tpk.javafxcutoftext.utils;

import lombok.SneakyThrows;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;

public class FilesUtils {

    @SneakyThrows
    public FileInputStream fileInputStream(String pathFile) {
        return new FileInputStream(pathFile);
    }

    @SneakyThrows
    public Workbook workbook(FileInputStream file) {
        return new XSSFWorkbook(file);
    }


}
