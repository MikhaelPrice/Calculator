package com.App.demo.calc;

import com.App.demo.calc.controllers.MainController;

import java.util.Arrays;
import java.util.ResourceBundle;

public class Matrix extends Var {
    private double[][] value;
    private int sizeI;
    private int sizeJ;
    public static ResourceBundle rb;
    public int getSizeI() {
        return sizeI;
    }

    public void setSizeI(int sizeI) {
        this.sizeI = sizeI;
    }

    public int getSizeJ() {
        return sizeJ;
    }

    public void setSizeJ(int sizeJ) {
        this.sizeJ = sizeJ;
    }

    public double[][] sizeI() {
        return value;
    }

    public double[][] getValue() {
        return value;
    }

    public void setValue(double[][] value) {
        this.value = value;
    }

    Matrix(double[][] value) {
        this.value = value;
    }

    Matrix(Matrix matrix) {
        this.value = matrix.value;
    }

    Matrix(String strMatrix) {
        strMatrix = strMatrix.substring(1, strMatrix.length() - 1);
        String[] stringsI = strMatrix.split("");
        for (int i = 0; i < stringsI.length; i++) {
            if (stringsI[i].equals("}")) {
                sizeI++;
            }
        }
        stringsI = strMatrix.split("[{}]");
        String[] stringsJ;
        for (String s : stringsI) {
            if (!s.equals("") && !s.equals(",")) {
                stringsJ = s.split(",");
                sizeJ = stringsJ.length;
            }
        }
        value = new double[sizeI][sizeJ];
        strMatrix = strMatrix.replaceAll("[{}]", "");
        String[] resStr = strMatrix.split(",");
        int str = 0;
        for (int i = 0; i < sizeI; i++) {
            for (int j = 0; j < sizeJ; j++) {
                if (resStr.length > str) {
                    value[i][j] = Double.parseDouble(resStr[str++]);
                }
            }
        }
    }

    @Override
    public Var div(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            if (((Scalar) other).getValue() == 0) {
                throw new calcException(rb.getString("DivZero"));
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] /= ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else {
            return super.div(other);
        }
    }

    @Override
    public Var add(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] += ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Vector) {
            return super.add(other);
        } else if (other instanceof Matrix) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            if (getSizeI() != ((Matrix) other).sizeI || getSizeJ() != ((Matrix) other).sizeJ) {
                throw new calcException(rb.getString("MatrixDiffLength"));
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] += ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(res);
        } else {
            return super.add(other);
        }
    }

    @Override
    public Var sub(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] -= ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Vector) {
            return super.sub(other);
        } else if (other instanceof Matrix) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            if (getSizeI() != ((Matrix) other).sizeI || getSizeJ() != ((Matrix) other).sizeJ) {
                throw new calcException(rb.getString("MatrixDiffLength"));
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] -= ((Matrix) other).value[i][j];
                }
            }
            return new Matrix(res);
        } else {
            return super.sub(other);
        }
    }

    @Override
    public Var mul(Var other) throws calcException {
        rb = ResourceBundle.getBundle(MainController.pathLanguage);
        if (other instanceof Scalar) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] *= ((Scalar) other).getValue();
                }
            }
            return new Matrix(res);
        } else if (other instanceof Vector) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            double[] result = Arrays.copyOf(((Vector) other).getValue(), ((Vector) other).getValue().length);
            double[] newVector = new double[res.length];
            if (res[0].length != result.length) {
                throw new calcException(rb.getString("MatrixVectorDiffLength"));
            }
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    newVector[i] += res[i][j] * result[j];
                }
            }
            return new Vector(newVector);
        } else if (other instanceof Matrix) {
            double[][] res = new double[getSizeI()][getSizeJ()];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < res[i].length; j++) {
                    res[i][j] = value[i][j];
                }
            }
            double[][] result = new double[((Matrix) other).getSizeI()][((Matrix) other).getSizeJ()];
            for (int i = 0; i < result.length; i++) {
                for (int j = 0; j < result[i].length; j++) {
                    result[i][j] = ((Matrix) other).value[i][j];
                }
            }
            if (res[0].length != result.length) {
                throw new calcException(rb.getString("DiffLengthInMatrix"));
            }
            double[][] newMatrix = new double[res.length][result[0].length];
            for (int i = 0; i < res.length; i++) {
                for (int j = 0; j < result[0].length; j++) {
                    for (int k = 0; k < result.length; k++) {
                        newMatrix[i][j] += res[i][k] * result[k][j];
                    }
                }
            }
            return new Matrix(newMatrix);
        } else {
            return super.mul(other);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("{{");
        for (int i = 0; i < value.length; i++) {
            if (i > 0) {
                sb.append("}, {");
            }
            for (int j = 0; j < value[i].length; j++) {
                if (j < value[i].length - 1) {
                    sb.append(value[i][j]).append(", ");
                } else {
                    sb.append(value[i][j]);
                }
            }
        }
        sb.append("}}");
        return sb.toString();
    }
}
