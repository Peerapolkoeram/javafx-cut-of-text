package com.tpk.javafxcutoftext.controller;

import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.service.FileService;
import com.tpk.javafxcutoftext.service.FileServiceImp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.util.List;

public class MainController {

    private final FileService fileService = new FileServiceImp();

    private final FileChooser fileChooser = new FileChooser();

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

        int rowCounter = 0;
        for (List<SheetRecordModel> e : result) {
            if (rowCounter == 3) {
                bntSheet.getChildren().add(new HBox());
                rowCounter = 0;
            }
            e.forEach( a -> {
                Button button = new Button(a.sheetName());
                button.setOnAction(this::handleButtonClick);
                button.setPrefWidth(Control.USE_COMPUTED_SIZE);
                button.setPadding(new Insets(5, 10, 5, 10));
                bntSheet.getChildren().add(button);
                bntSheet.setSpacing(10);
                bntSheet.setPadding(new Insets(10));
            });
            rowCounter++;
        }
    }

    private void handleButtonClick(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String buttonText = clickedButton.getText();
        System.out.println(buttonText);
    }
}