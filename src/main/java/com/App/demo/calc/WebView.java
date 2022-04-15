package com.App.demo.calc;

import com.App.demo.calc.controllers.MainController;
import org.springframework.ui.Model;

import java.util.ResourceBundle;

public class WebView {
    public static void showView(Model model) {
        model.addAttribute("Greeting", MainController.rb.getString("Greeting"));
        model.addAttribute("Example", MainController.rb.getString("Example"));
        model.addAttribute("Count", MainController.rb.getString("Count"));
        model.addAttribute("Entering", MainController.rb.getString("Entering"));
        model.addAttribute("Result", MainController.rb.getString("Result"));
        model.addAttribute("ShowVariables",MainController.rb.getString("ShowVariables"));
    }

    public static void selectLanguage(String line) {
        MainController.rb = ResourceBundle.getBundle(MainController.pathLanguage);
        line = line.replaceAll(",", "");
        switch (line) {
            case "ru" -> MainController.pathLanguage = "ru";
            case "be" -> MainController.pathLanguage = "be";
            case "en" -> MainController.pathLanguage = "en";
        }
    }
}
