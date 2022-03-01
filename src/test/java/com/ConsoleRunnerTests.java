package com;

import com.App.demo.calc.Parser;
import com.App.demo.calc.Var;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CalcTest {

    @Test
    public void scalarAddScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("2+2");
        assertEquals("4.0", var.toString());
    }

    @Test
    public void scalarMulScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("3.5*2");
        assertEquals("7.0", var.toString());
    }

    @Test
    public void scalarSubScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("22.5-1.5");
        assertEquals("21.0", var.toString());
    }

    @Test
    public void scalarDivScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("9/3");
        assertEquals("3.0", var.toString());
    }

    @Test
    public void scalarExpression() throws Exception {
        Parser parser = new Parser();
        assertEquals("7.3", parser.calc("A=2+5.3").toString());
        assertEquals("25.55", parser.calc("B=A*3.5").toString());
        assertEquals("25.0", parser.calc("B1=B+0.11*-5").toString());
        assertEquals("2.65", parser.calc("B2=A/2-1").toString());
    }

    @Test
    public void vectorAddScalar() throws Exception {
        Parser parser = new Parser();
        assertEquals("{4.0, 5.0, 6.0}", parser.calc("{2,3,4}+2").toString());

    }

    @Test
    public void vectorMulScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{1,2}*3");
        assertEquals("{3.0, 6.0}", var.toString());
    }

    @Test
    public void vectorSubScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{5,6,7}-1");
        assertEquals("{4.0, 5.0, 6.0}", var.toString());
    }

    @Test
    public void vectorDivScalar() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{1,2}/4");
        assertEquals("{0.25, 0.5}", var.toString());
    }

    @Test
    public void vectorAddVector() throws Exception {
        Parser parser = new Parser();
        assertEquals("{7.0, 9.0, 11.0}", parser.calc("{2,3,4}+{5,6,7}").toString());

    }

    @Test
    public void vectorMulVector() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{1,2}*{3,4}");
        assertEquals("11.0", var.toString());
    }

    @Test
    public void vectorSubVector() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{5,6,7}-{2,3,4}");
        assertEquals("{3.0, 3.0, 3.0}", var.toString());
    }

    @Test
    public void vectorExpression() throws Exception {
        Parser parser = new Parser();
        assertEquals("7.3", parser.calc("A=2+5.3").toString());
        assertEquals("25.55", parser.calc("B=A*3.5").toString());
        assertEquals("40.15", parser.calc("C=B+(A*2)").toString());
        assertEquals("10.0", parser.calc("D=((C-0.15)-20)/(7-5)").toString());
        assertEquals("{10.0, 15.0}", parser.calc("E={2,3}*(D/2)").toString());
    }

    @Test
    public void matrixMulVector() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{{1,2},{8,3}}*{1,2}");
        assertEquals("{5.0, 14.0}", var.toString());
    }

    @Test
    public void matrixAddMatrix() throws Exception {
        Parser parser = new Parser();
        assertEquals("{{4.0, 7.0}, {9.0, 7.0}}", parser.calc("{{3,5},{1,4}}+{{1,2},{8,3}}").toString());

    }

    @Test
    public void matrixMulMatrix() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{{1,2},{8,3}}*{{2,5},{7,2}}");
        assertEquals("{{16.0, 9.0}, {37.0, 46.0}}", var.toString());
    }

    @Test
    public void matrixSubMatrix() throws Exception {
        Parser parser = new Parser();
        Var var = parser.calc("{{4,5},{9,7}}-{{1,2},{8,3}}");
        assertEquals("{{3.0, 3.0}, {1.0, 4.0}}", var.toString());
    }

    @Test
    public void matrixExpression() throws Exception {
        Parser parser = new Parser();
        assertEquals("7.3", parser.calc("A=2+5.3").toString());
        assertEquals("25.55", parser.calc("B=A*3.5").toString());
        assertEquals("40.15", parser.calc("C=B+(A*2)").toString());
        assertEquals("{{38.0, 20.0}, {68.0, 54.0}}", parser.calc("({{1,2},{8,3}}*{{1,2},{8,3}}+2)*2").toString());
        assertEquals("{{9.0, 13.0}, {37.0, 17.0}}", parser.calc("({{1,2},{8,3}}+{{1,2},{8,3}})*(A-5.3)+(B/5-0.11)").toString());
    }

    @Test
    public void extraExpressions() throws Exception{
        Parser parser = new Parser();
        assertEquals("{44.0, -10.0, 8.0}", parser.calc("B=({13,-5,1}*3)+5").toString());
        assertEquals("{48.666666666666664, 51.333333333333336}", parser.calc("A=15+({{5,6,1},{3,10,4}}*{14,3,8}+5)/3").toString());
    }
}


