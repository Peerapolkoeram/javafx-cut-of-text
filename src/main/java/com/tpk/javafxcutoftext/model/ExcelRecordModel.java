package com.tpk.javafxcutoftext.model;

import lombok.Builder;

import java.util.List;

@Builder
public record ExcelRecordModel(
        SheetRecordModel sheetRecordModel,
        String condition,
        String pathFile,
        List<Integer> selectColumn,
        Integer startRow
) {
}
