package com.tpk.javafxcutoftext.controller;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.service.FileService;
import com.tpk.javafxcutoftext.service.FileServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class MainController {

    private final FileService fileService = new FileServiceImp();

    private final FileChooser fileChooser = new FileChooser();

    @FXML
    private Button bntChooseFileExcel;

    @FXML
    private TextField inpExcelFile;

    @FXML
    private HBox bntSheet;

    @FXML
    protected void onclickChooseExcelFile() {
        fileChooser.setTitle("Choose Excel File");
        File file = fileChooser.showOpenDialog(null);
        try {
            inpExcelFile.setText(file.getAbsolutePath());
        } catch (Exception ignored) {}

        List<List<SheetRecordModel>> result = fileService.readSheetName(fileService.workbookFile(file.getAbsolutePath()));

        if (bntSheet.getChildren() != null) {
            bntSheet.getChildren().clear();
        }

        result.forEach( e -> {
            e.forEach( a -> {
                Button button = new Button(a.sheetName());
                button.setOnAction(this::handleButtonClick);
                bntSheet.getChildren().add(button);
                bntSheet.setSpacing(10);
                bntSheet.setPadding(new Insets(10));
            });
        });
    }

    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        System.out.println(buttonText);
    }
}