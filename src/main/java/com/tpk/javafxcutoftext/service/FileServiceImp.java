package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import com.tpk.javafxcutoftext.utils.FilesUtils;
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

    public List<List<SheetRecordModel>> getSheetExcel(Workbook sheets) {
        List<List<SheetRecordModel>> result = new ArrayList<>();
        for (int i = 0; i < sheets.getNumberOfSheets(); i++) {
            List<SheetRecordModel> listSheetName = new ArrayList<>();
            String sheetName = sheets.getSheetName(i);
            listSheetName.add(SheetRecordModel.builder()
                    .sheetName(sheetName)
                    .sheetNumber(i)
                    .build());
            result.add(listSheetName);
        }
        return result;
    }

    public List<List<SheetRecordModel>> getDataInExcel(ExcelRecordModel excelRecordModel) {
        Workbook workbook = filesUtils.workbook(filesUtils.fileInputStream(excelRecordModel.pathFile()));
        return getSheetExcel(workbook);
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

    private Object findDataCell(Cell cell) {
        switch (cell.getCellType()) {
            case NUMERIC -> {return cell.getNumericCellValue();}
            case FORMULA -> {return cell.getCellFormula();}
            case BOOLEAN -> {return cell.getBooleanCellValue();}
            default -> {return cell.getStringCellValue();}
        }
   }

}
