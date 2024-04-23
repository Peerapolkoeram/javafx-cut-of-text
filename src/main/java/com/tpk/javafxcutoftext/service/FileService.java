package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface FileService {

   List<List<SheetRecordModel>> getSheetExcel(Workbook sheets);
   List<List<SheetRecordModel>> getDataInExcel(ExcelRecordModel excelRecordModel);
}
