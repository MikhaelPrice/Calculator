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

    public Calculations(String expression, String result) {
        this.expression = expression;
        this.result = result;
    }

    @Column(name = "expression", updatable = false, nullable = false)
    private String expression;

    @Column(name = "result", updatable = false)
    private String result;

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
