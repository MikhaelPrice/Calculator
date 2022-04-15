package com.App.demo.calc.controllers;

import com.App.demo.calc.Var;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VarController {

    @GetMapping("/Vars")
    public String getVars(Model model) {
        model.addAttribute("vars", Var.printvar());
        return "Vars";
    }
}
