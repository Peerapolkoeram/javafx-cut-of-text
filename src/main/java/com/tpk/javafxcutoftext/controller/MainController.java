package com.tpk.javafxcutoftext.controller;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.service.FileService;
import com.tpk.javafxcutoftext.service.FileServiceImp;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class MainController {

    private final FileServiceImp fileServiceImp = new FileServiceImp();

    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private Button bntChooseFileExcel;

    @FXML
    private TextField inpExcelFile;

    @FXML
    protected void onclickChooseExcelFile() {
        fileChooser.setTitle("Choose Excel File");
        File file = fileChooser.showOpenDialog(null);
        try {
            inpExcelFile.setText(file.getAbsolutePath());
        } catch (Exception ignored) {}

        ExcelRecordModel excelRecordModel = excelRecordModel("SHEET_NUMBER");

        //call service
        List<Object> result = fileServiceImp.getDataInExcel(excelRecordModel);
        result.forEach(System.out::println);
    }

    public ExcelRecordModel excelRecordModel(String condition) {
        if (condition.equals("SHEET_NUMBER")) {
            return  ExcelRecordModel.builder()
                    .condition(condition)
                    .build();
        } else {
            return ExcelRecordModel.builder()
                    .build();
        }
    }
}