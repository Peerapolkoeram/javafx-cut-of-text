package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.SheetExcelModel;
import com.tpk.javafxcutoftext.utils.FilesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FileServiceImp {

    private final FilesUtils filesUtils;

    public List<SheetExcelModel> getSheetExcel(Workbook sheets) {
        List<SheetExcelModel> listSheetName = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            int sheetNumber = sheets.getNumberOfSheets();
            String sheetName = sheets.getSheetName(i);
            listSheetName.add(SheetExcelModel.builder()
                    .sheetName(sheetName)
                    .sheetNumber(sheetNumber)
                    .build());
        }

        return listSheetName;
    }

   public List<String> getDataInExcel(String pathFile) {
        FileInputStream fileInputStream = filesUtils.fileInputStream(pathFile);
        return null;
   }

}
