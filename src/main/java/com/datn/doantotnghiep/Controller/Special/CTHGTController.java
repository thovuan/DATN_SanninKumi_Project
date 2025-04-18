package com.datn.doantotnghiep.Controller.Special;

import com.datn.datnai.Model.TinhhuongGiaoTiep;
import com.datn.datnai.Service.THGTServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CTHGTController {

    @Autowired
    private THGTServices thgtService;

    @GetMapping("/CTHGT")
    public String CTHGTOpen (Model model) {
        List<TinhhuongGiaoTiep> list = thgtService.thgt();
        model.addAttribute("entities", list);
        return "/QUANLYTHUVIEN/CTHGT";
    }
}
