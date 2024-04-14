package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.utils.FilesUtils;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class FileServiceImp implements FileService{

    private final FilesUtils filesUtils;

    public List<SheetRecordModel> getSheetExcel(Workbook sheets) {
        List<SheetRecordModel> listSheetName = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            int sheetNumber = sheets.getNumberOfSheets();
            String sheetName = sheets.getSheetName(i);
            listSheetName.add(SheetRecordModel.builder()
                    .sheetName(sheetName)
                    .sheetNumber(sheetNumber)
                    .build());
        }
        return listSheetName;
    }

   public List<Object> getDataInExcel(ExcelRecordModel excelRecordModel) {
       List<Object> addRows = new ArrayList<>();
       Workbook workbook = filesUtils.workbook(filesUtils.fileInputStream(excelRecordModel.pathFile()));
       Sheet sheet = workbook.getSheetAt(excelRecordModel.sheetRecordModel().sheetNumber());
       for (Row row : sheet) {
           List<Object> listObject = new ArrayList<>();
           if (row.getRowNum() >= excelRecordModel.startRow()) {
               for (Cell cell : row) {
                   for (int j : excelRecordModel.selectColumn()) {
                       listObject.add(findDataCell(cell,j));
                   }
               }
               addRows.add(listObject);
           }
       }
       return addRows;
   }

    private Object findDataCell(Cell cell, int column) {
        switch (cell.getCellType()) {
            case NUMERIC -> {return cell.getNumericCellValue();}
            case FORMULA -> {return cell.getCellFormula();}
            case BOOLEAN -> {return cell.getBooleanCellValue();}
            default -> {return cell.getStringCellValue();}
        }
   }

}
