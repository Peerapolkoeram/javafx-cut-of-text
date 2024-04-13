package com.tpk.javafxcutoftext.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;

//@AllArgsConstructor
public class MainController {

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

    }
}