package com.App.demo.calc;

import com.App.demo.calc.controllers.MainController;

import java.util.Arrays;
import java.util.ResourceBundle;

public class Vector extends Var {
    public static ResourceBundle rb;
    private double[] value;

    public double[] getValue() {
        return value;
    }

    public void setValue(double[] value) {
        this.value = value;
    }

    Vector(double[] value) {
        this.value = value;
    }

    Vector(Vector vector) {
        this.value = vector.value;
    }

    Vector(String strVector) {
        strVector = strVector.replaceAll("[{}]", "");
        String[] strings = strVector.split(",");
        value = new double[strings.length];
        for (int i = 0; i < strings.length; i++) {
            value[i] = Double.parseDouble(strings[i]);
        }
    }

    @Override
    public Var sub(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] -= ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            if (((Vector) other).value.length != this.value.length) {
                MainController.localMessage = rb.getString("VectorsDiffLength");
                throw new calcException(MainController.localMessage);
            }
            for (int i = 0; i < res.length; i++) {
                res[i] -= ((Vector) other).getValue()[i];
            }
            return new Vector(res);
        } else {
            return other.sub(this);
        }
    }

    @Override
    public Var div(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            if (((Scalar) other).getValue() == 0) {
                MainController.localMessage = rb.getString("DivZero");
                throw new calcException(MainController.localMessage);
            }
            for (int i = 0; i < res.length; i++) {
                res[i] /= ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else {
            return super.div(other);
        }
    }

    @Override
    public Var grade(Var other) throws calcException {
        double res = 0, grade = Double.parseDouble(other.toString());
        double[] newVector = Arrays.copyOf(value, value.length);
        for (int i = 0; i < grade; i++) {
            for (int j = 0; j < newVector.length; j++) {
                newVector[i] *= ((Vector) other).getValue()[i];
                res += newVector[i];
            }
        }
        return new Scalar(res);
    }

    @Override
    public Var mul(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] *= ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double sum = 0;
            double[] res = Arrays.copyOf(value, value.length);
            if (((Vector) other).value.length != this.value.length) {
                MainController.localMessage = rb.getString("VectorsDiffLength");
                throw new calcException(MainController.localMessage);
            }
            for (int i = 0; i < res.length; i++) {
                res[i] *= ((Vector) other).getValue()[i];
                sum += res[i];
            }
            return new Scalar(sum);

        } else if (other instanceof Matrix) {
            double[][] res = new double[((Matrix) other).getSizeI()][((Matrix) other).getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = ((Matrix) other).getValue()[i][j];
                }
            }
            double[] result = Arrays.copyOf(value, value.length);
            double[][] newMatrix = new double[res[0].length][1];
            if (result.length != res.length) {
                MainController.localMessage = rb.getString("MatrixVectorDiffLength");
                throw new calcException(MainController.localMessage);
            }
            for (int i = 0; i < res[0].length; i++) {
                for (int j = 0; j < result.length; j++) {
                    newMatrix[i][0] += result[j] * res[j][i];
                }
            }
            return new Matrix(newMatrix);
        } else {
            return other.mul(this);
        }
    }

    @Override
    public Var add(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[] res = Arrays.copyOf(value, value.length);
            for (int i = 0; i < res.length; i++) {
                res[i] += ((Scalar) other).getValue();
            }
            return new Vector(res);
        } else if (other instanceof Vector) {
            double[] res = Arrays.copyOf(value, value.length);
            if (((Vector) other).value.length != this.value.length) {
                MainController.localMessage = rb.getString("VectorsDiffLength");
                throw new calcException(MainController.localMessage);
            }
            for (int i = 0; i < res.length; i++) {
                res[i] += ((Vector) other).getValue()[i];
            }
            return new Vector(res);
        } else {
            return other.add(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder("{");
        for (int i = 0; i < getValue().length; i++) {
            if (i < getValue().length - 1) {
                str.append(getValue()[i]).append(", ");
            } else {
                str.append(getValue()[i]);
            }
        }
        str.append("}");
        return str.toString();
    }
}
