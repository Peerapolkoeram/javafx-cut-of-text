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

public class FileServiceImp implements FileService{

    private final FilesUtils filesUtils = new FilesUtils();

    public FileServiceImp() {

    }

    public List<Object> getSheetExcel(Workbook sheets) {
        List<Object> objects = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            List<SheetRecordModel> listSheetName = new ArrayList<>();
            int sheetNumber = sheets.getNumberOfSheets();
            String sheetName = sheets.getSheetName(i);
            listSheetName.add(SheetRecordModel.builder()
                    .sheetName(sheetName)
                    .sheetNumber(sheetNumber)
                    .build());
            objects.add(listSheetName);
        }
        return objects;
    }

    public List<Object> getDataInExcel(ExcelRecordModel excelRecordModel) {
        Workbook workbook = filesUtils.workbook(filesUtils.fileInputStream(excelRecordModel.pathFile()));
        if (excelRecordModel.condition().equals("SHEET_NUMBER")) {
            return getSheetExcel(workbook);
        } else {
            return readDataInExcel(excelRecordModel,workbook);
        }
    }

   public List<Object> readDataInExcel(ExcelRecordModel excelRecordModel, Workbook workbook) {
       List<Object> addRows = new ArrayList<>();
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
