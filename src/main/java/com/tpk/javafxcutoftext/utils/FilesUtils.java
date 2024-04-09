package com.tpk.javafxcutoftext.utils;

import lombok.SneakyThrows;

import java.io.FileInputStream;

public class FilesUtils {

    @SneakyThrows
    public FileInputStream fileInputStream(String pathFile) {
        return new FileInputStream(pathFile);
    }


}
