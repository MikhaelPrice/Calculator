package com.App.demo.calc.models;

import javax.persistence.*;

@Entity
public class Calculations {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", updatable = false, nullable = false, unique = true)
    private Long id;

    public Calculations() {
    }

    public Calculations(String expressions, String results) {
        this.expression = expressions;
        this.result = results;
    }

    private String expression, result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression;
    }
}
