package com.datn.doantotnghiep.Model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class KanjiDTO {

    private String kanji;
    private String hanViet;
    private String imi;
    private List<String> onyomiList;
    private List<String> kunyomiList;

//    // Getters và Setters
//    public String getKanji() {
//        return kanji;
//    }
//
//    public void setKanji(String kanji) {
//        this.kanji = kanji;
//    }
//
//    public List<String> getOnyomiList() {
//        return onyomiList;
//    }
//
//    public void setOnyomiList(List<String> onyomiList) {
//        this.onyomiList = onyomiList;
//    }
//
//    public List<String> getKunyomiList() {
//        return kunyomiList;
//    }
//
//    public void setKunyomiList(List<String> kunyomiList) {
//        this.kunyomiList = kunyomiList;
//    }
}
