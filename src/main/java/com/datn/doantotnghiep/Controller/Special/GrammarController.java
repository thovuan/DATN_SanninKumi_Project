package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Service.GrammarCheckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/grammar")
public class GrammarController {

    @Autowired
    private GrammarCheckService grammarCheckService;

    @PostMapping("/check")
    public String checkGrammar(@RequestBody String sentence) {
        return grammarCheckService.checkJapaneseGrammar(sentence);
    }
}
