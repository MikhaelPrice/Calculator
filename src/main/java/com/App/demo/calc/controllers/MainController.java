package com.App.demo.calc.controllers;

import com.App.demo.calc.Parser;
import com.App.demo.calc.calcException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ResourceBundle;

@Controller
public class MainController {
    public static String pathLanguage = "ru";
    public static ResourceBundle rb;
    public static String localMessage;

    @GetMapping("/")
    public String getExpression(@RequestParam("expression") String expression, @RequestParam("expression") String language, Model model) {
        rb = ResourceBundle.getBundle(pathLanguage);
        String result;
        selectLanguage(language);
        model.addAttribute("Greeting", rb.getString("Greeting"));
        model.addAttribute("Example",rb.getString("Example"));
        model.addAttribute("Count",rb.getString("Count"));
        model.addAttribute("Entering", rb.getString("Entering"));
        model.addAttribute("Result", rb.getString("Result"));
        Parser parser = new Parser();
        try {
            result = parser.calc(expression.trim()).toString();
            model.addAttribute("result", result);
        } catch (calcException e) {
            model.addAttribute("result", localMessage);
        }
        return "home";
    }

    public static void selectLanguage(String line) {
        rb = ResourceBundle.getBundle(pathLanguage);
        line = line.replaceAll(",","");
        switch (line) {
            case "ru" -> pathLanguage =  "ru";
            case "be" -> pathLanguage = "be";
            case "en" -> pathLanguage = "en";
        }
    }
}
