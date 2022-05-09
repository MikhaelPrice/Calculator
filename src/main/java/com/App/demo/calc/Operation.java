package com.App.demo.calc;

public interface Operation {
    Var add(Var other) throws calcException;
    Var sub(Var other) throws calcException;
    Var mul(Var other) throws calcException;
    Var div(Var other) throws calcException;
    Var grade(Var other) throws calcException;
}