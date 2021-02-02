package cz.educanet.tranformations;

import kotlin.NotImplementedError;

import java.util.Arrays;

public class Matrix implements IMatrix {

    private final double[][] rawArray;

    public Matrix(double[][] rawArray) {
        this.rawArray = rawArray;
    }

    @Override
    public int getRows() {
        return rawArray.length;
    }

    @Override
    public int getColumns() {
        if (getRows() > 0)
            return rawArray[0].length;

        return 0;
    }

    public boolean isValid(IMatrix matrix) {
        return matrix.getColumns() == this.getRows();
    }

    @Override
    public IMatrix times(IMatrix matrix) {
        if (!this.isValid(matrix))
            return null;

        IMatrix tposed = matrix.tpose();

        double[][] finMatrix = new double[tposed.getRows()][tposed.getColumns()];

        for (int i = 0; i < this.getRows(); i++) {

            for (int y = 0; y < this.getColumns(); y++) {

                int result = 0;
                for (int x = 0; x < this.getRows(); x++) {
                    result += this.rawArray[i][x] * tposed.get(y, x);
                }

                finMatrix[i][y] = result;
            }
        }
        return MatrixFactory.create(finMatrix);
    }

    @Override
    public IMatrix times(Number scalar) {
        double[][] finMatrix = new double[this.getRows()][this.getColumns()];

        for (int i = 0; i < this.getRows(); i++) {
            for (int y = 0; y < this.getColumns(); y++) {
                finMatrix[i][y] = this.rawArray[i][y] * scalar.doubleValue();
            }
        }
        return MatrixFactory.create(finMatrix);
    }

    @Override
    public IMatrix add(IMatrix matrix) {
        if (!this.isValid(matrix))
            return null;

        double[][] finMatrix = new double[this.getRows()][this.getColumns()];

        for (int i = 0; i < this.getRows(); i++) {
            for (int y = 0; y < this.getColumns(); y++) {
                finMatrix[i][y] = this.rawArray[i][y] + matrix.get(i, y);
            }
        }
        return MatrixFactory.create(finMatrix);

    }

    @Override
    public double get(int n, int m) {
        return this.rawArray[n][m];
    }

    // region Optional
    @Override
    public IMatrix tpose() {

        Matrix matrix = new Matrix(new double[this.getRows()][this.getColumns()]);

        for (int i = 0; i < this.getRows(); i++) {
            for (int y = 0; y < this.getColumns(); y++) {
                matrix.rawArray[i][y] = this.rawArray[y][i];
            }
        }
        return matrix;
    }

    @Override
    public double determinant() {
        return 0;
    }

    // endregion
    // region Generated
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Matrix matrix = (Matrix) o;
        return Arrays.equals(rawArray, matrix.rawArray);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(rawArray);
    }
    // endregion
}
