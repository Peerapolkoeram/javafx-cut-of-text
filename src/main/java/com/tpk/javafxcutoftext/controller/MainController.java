package com.tpk.javafxcutoftext.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label txtExcelFileName;

    @FXML
    private Button bntChooseFileExcel;

    @FXML
    protected void onclickChooseExcelFile() {

        txtExcelFileName.setText("Test Data Field");

    }
}