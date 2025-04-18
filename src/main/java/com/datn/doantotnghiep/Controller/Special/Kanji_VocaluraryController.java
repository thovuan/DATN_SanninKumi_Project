package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Model.Kanji;
import com.datn.datnai.Service.Vocaluary_KanjiServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class Kanji_VocaluraryController {

    @Autowired
    private Vocaluary_KanjiServices vocaluary_KanjiServices;

    @GetMapping("/kanji-list")
    public String getAllKanjis(Model model) {
        List<Kanji> kanjiList = vocaluary_KanjiServices.listofKanji();
        model.addAttribute("kanjiList", kanjiList);
        return "/QUANLYTHUVIEN/KanjiList";
    }

    @GetMapping("/vocalubrary-list")
    public String getVCLP (Model model) {
        return "/QUANLYTHUVIEN/Vocalubrary";
    }
}
