package com.App.demo.calc.controllers;

import com.App.demo.calc.Parser;
import com.App.demo.calc.calcException;
import com.App.demo.calc.models.Calculations;
import com.App.demo.calc.repo.CalculationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ResourceBundle;

@Controller
public class MainController {
    public static String pathLanguage = "ru";
    public static ResourceBundle rb;
    public static String localMessage;

    @Autowired
    private CalculationsRepository calculationsRepository;

    @PostMapping("/")
    public String postDatabase(@RequestParam("expression") String expression,  @RequestParam("expression") String language, Model model ) {
        rb = ResourceBundle.getBundle(pathLanguage);
        String result;
        selectLanguage(language);
        model.addAttribute("Greeting", rb.getString("Greeting"));
        model.addAttribute("Example", rb.getString("Example"));
        model.addAttribute("Count", rb.getString("Count"));
        model.addAttribute("Entering", rb.getString("Entering"));
        model.addAttribute("Result", rb.getString("Result"));
        Parser parser = new Parser();
        try {
            result = parser.calc(expression.trim()).toString();
            model.addAttribute("result", result);
            Calculations calculations = new Calculations(expression, result);
            calculations.setExpression(expression);
            calculations.setResult(result);
            calculationsRepository.save(calculations);
        } catch (calcException e) {
            model.addAttribute("result", localMessage);
        }
        return "home";
    }

    public static void selectLanguage(String line) {
        rb = ResourceBundle.getBundle(pathLanguage);
        line = line.replaceAll(",", "");
        switch (line) {
            case "ru" -> pathLanguage = "ru";
            case "be" -> pathLanguage = "be";
            case "en" -> pathLanguage = "en";
        }
    }
}
