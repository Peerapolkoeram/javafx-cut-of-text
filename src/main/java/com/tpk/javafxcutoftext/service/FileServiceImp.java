package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.SheetExcelModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

public class FileServiceImp {

    public List<SheetExcelModel> getSheetExcel(Workbook sheets) {
        List<SheetExcelModel> listSheetName = new ArrayList<>();
        sheets.forEach(sheet -> {
            int numberSheet = sheets.getNumberOfSheets();
            String sheetName = sheets.getSheetName(numberSheet);
            listSheetName.add(SheetExcelModel.builder()
                    .sheetName(sheetName)
                    .value(numberSheet)
                    .build());
        });
        return listSheetName;
    }

    


}
