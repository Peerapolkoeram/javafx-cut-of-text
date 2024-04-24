package com.tpk.javafxcutoftext.service;

import com.tpk.javafxcutoftext.model.SheetRecordModel;
import org.apache.poi.ss.usermodel.Workbook;

import java.util.List;

public interface FileService {

   List<List<SheetRecordModel>> readSheetName(Workbook sheets);
   Workbook workbookFile(String pathFile);
}
