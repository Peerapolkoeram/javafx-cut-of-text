package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.ExcelRecordModel;
import com.tpk.javafxcutoftext.model.SheetRecordModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface FileService {

   public List<SheetRecordModel> getSheetExcel(Workbook sheets);
   public List<Object> getDataInExcel(ExcelRecordModel excelRecordModel);
}
