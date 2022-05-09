package com.App.demo.calc;

import com.App.demo.calc.controllers.MainController;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public abstract class Var implements Operation {
    private static final Map<String, Var> vars = new HashMap<>();
    public static ResourceBundle rb;

    static Var saveVar(String name, Var var) {
        vars.put(name, var);
        return var;
    }

    public static String printvar() {
        StringBuilder var = new StringBuilder();
        if (!vars.isEmpty()) {
            for (Map.Entry<String, Var> entry : vars.entrySet()) {
                var.append(entry.getKey()).append("=").append(entry.getValue()).append("\n");
            }
        }
        return var.toString();
    }

    static Var createVar(String operand) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        operand = operand.trim().replace("\\s+", "");
        if (operand.matches(Patterns.SCALAR)) {
            return new Scalar(operand);
        } else if (operand.matches(Patterns.VECTOR)) {
            return new Vector(operand);
        } else if (operand.matches(Patterns.MATRIX)) {
            return new Matrix(operand);
        } else if (vars.containsKey(operand)) {
            return vars.get(operand);
        } else {
            MainController.localMessage = rb.getString("NotCreate") + operand;
            throw new calcException(MainController.localMessage);
        }
    }

    @Override
    public Var add(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        MainController.localMessage = rb.getString("NoAdd") + " " + this + "+" + other;
        throw new calcException(MainController.localMessage);
    }

    @Override
    public Var sub(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        MainController.localMessage = rb.getString("NoSub") + " " + this + "-" + other;
        throw new calcException(MainController.localMessage);
    }

    @Override
    public Var mul(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        MainController.localMessage = rb.getString("NoMul") + " " + this + "*" + other;
        throw new calcException(MainController.localMessage);
    }

    @Override
    public Var div(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        MainController.localMessage = rb.getString("NoDiv") + " " + this + "/" + other;
        throw new calcException(MainController.localMessage);
    }
    @Override
    public Var grade(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        MainController.localMessage = rb.getString("NoGrade") + " " + this + "^" + other;
        throw new calcException(MainController.localMessage);
    }
}
