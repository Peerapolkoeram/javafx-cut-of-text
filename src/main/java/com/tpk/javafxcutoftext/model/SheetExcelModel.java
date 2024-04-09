package com.tpk.javafxcutoftext.model;

import lombok.Builder;

@Builder
public record SheetExcelModel(
        String sheetName ,
        Integer sheetNumber
) {
}
