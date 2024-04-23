package com.tpk.javafxcutoftext.controller;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.service.FileService;
import com.tpk.javafxcutoftext.service.FileServiceImp;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainController {

    private final List<String> condition = List.of("GET_SHEET","GET_DATA");
    private final FileService fileService = new FileServiceImp();

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

        List<List<SheetRecordModel>> result = null;
        for (String e : condition) {
            if (e.equals("GET_SHEET")) {
                result = fileService.getDataInExcel(ExcelRecordModel.builder()
                        .pathFile(file.getAbsolutePath()).build());
            }
        }
        VBox root = new VBox();
        result.forEach( e -> {
            e.forEach( a -> {
                Label label = new Label(a.sheetName());
                root.getChildren().add(label);
            });
        });

    }
}