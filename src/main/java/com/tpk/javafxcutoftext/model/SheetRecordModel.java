package com.tpk.javafxcutoftext.model;

import lombok.Builder;

@Builder
public record SheetRecordModel(
        String sheetName ,
        Integer sheetNumber
) {
}
