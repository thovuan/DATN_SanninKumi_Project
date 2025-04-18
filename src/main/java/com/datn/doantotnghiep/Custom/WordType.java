package com.datn.doantotnghiep.Custom;

public enum WordType {

    DANH_TU("danh từ"),
    DONG_TU("động từ"),
    TINH_TU("tính từ"),
    TRO_TU("trợ từ"),
    PHO_TU("phó từ");

    private final String label;

    WordType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}
