package com.App.demo.calc;

import com.App.demo.calc.controllers.MainController;

import java.util.ResourceBundle;

class Scalar extends Var {
    public static ResourceBundle rb;
    private double value;

    public double getValue() {
        return value;
    }

    Scalar(double value) {
        this.value = value;
    }

    Scalar(String str) {
        this.value = Double.parseDouble(str);
    }

    Scalar(Scalar scalar) {
        this.value = scalar.value;
    }

    @Override
    public Var add(Var other) throws calcException {
        if (other instanceof Scalar) {
            double sum = this.value + ((Scalar) other).value;
            return new Scalar(sum);
        } else {
            return other.add(this);
        }
    }

    @Override
    public Var sub(Var other) throws calcException {
        if (other instanceof Scalar) {
            double sub = this.value - ((Scalar) other).value;
            return new Scalar(sub);
        } else if (other instanceof Vector) {
            double[] res = new double[((Vector) other).getValue().length];
            for (int i = 0; i < res.length; i++) {
                res[i] = ((Vector) other).getValue()[i];
            }
            for (int i = 0; i < res.length; i++) {
                res[i] = this.value - res[i];
            }
            return new Vector(res);
        } else if (other instanceof Matrix) {
            double[][] res = new double[((Matrix) other).getSizeI()][((Matrix) other).getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = ((Matrix) other).getValue()[i][j];
                }
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = this.value - res[i][j];
                }
            }
            return new Matrix(res);
        } else {
            return other.sub(this);
        }
    }

    @Override
    public Var div(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            if (((Scalar) other).value == 0) {
                MainController.localMessage = rb.getString("DivZero");
                throw new calcException(MainController.localMessage);
            }
            double div = this.value / ((Scalar) other).value;
            return new Scalar(div);
        }
        return super.div(other);
    }

    @Override
    public Var grade(Var other) throws calcException {
        double res = 1;
        for (int i = 0; i < Double.parseDouble(other.toString()); i++) {
            res *= this.value;
        }
        return new Scalar(res);
    }

    @Override
    public Var mul(Var other) throws calcException {
        if (other instanceof Scalar) {
            double mul = this.value * ((Scalar) other).value;
            return new Scalar(mul);
        } else {
            return other.mul(this);
        }
    }

    @Override
    public String toString() {
        return Double.toString(value);
    }
}
