import java.math.RoundingMode;
import java.text.DecimalFormat;

public class REF {

    private boolean reduced;

    private double[][] matrix;

    public REF(double[][] m, boolean r) {

        this.reduced = r;
        this.matrix = m;

        // iterate through each pivot column to check if all rows below it are 0

        printMatrix();

    }

    int pivot_row = -1;
    int pivot_column = -1;

    int current_row = 0;

    public void rowEchelonForm() {

        System.out.println();

        // find first pivot column point

        for (int c = 0; c < matrix[0].length; c++) {
            for (int r = current_row; r < matrix.length; r++) {
                if (matrix[r][c] != 0) {
                    pivot_column = c;
                    pivot_row = r;
                    break;
                }
            }
            if (pivot_column != -1)
                break;
        }

        if (pivot_column == -1) {
            current_row = matrix.length - 1;
            if (reduced) {
                System.out.println("Reduced: ");
                reducedRowEchelonForm();
            }

            return;
        }
        // make it first row
        rowInterchange(pivot_row, current_row);

        if (pivot_row != current_row) {
            System.out.println("Move row " + (pivot_row + 1) + " to row " + (current_row + 1) + "\n");
            printMatrix();
        }
        System.out.println();

        for (int r = current_row + 1; r < matrix.length; r++) {
            if (matrix[r][pivot_column] != 0) {
                double scalar = matrix[r][pivot_column] / matrix[current_row][pivot_column];
                rowAdd(r, current_row, -scalar);

                if (scalar != 0) {
                    System.out
                            .println("Add row " + (current_row + 1) + " with scalar " + -scalar + " to row " + (r + 1)
                                    + "\n");
                    printMatrix();
                    System.out.println();
                }

            }
        }

        pivot_column = -1;
        pivot_row = -1;

        current_row++;

        rowEchelonForm();

    }

    private void reducedRowEchelonForm() {

        // finding it the other way around

        for (int r = current_row; r >= 0; r--) {
            for (int c = 0; c < matrix[0].length; c++) {
                if (matrix[r][c] != 0) {
                    pivot_column = c;
                    pivot_row = r;
                    break;
                }
            }
            if (pivot_column != -1)
                break;
        }

        if (pivot_column == -1) {
            return;
        }

        // make sure its scaled to 1

        double scalar = 1 / matrix[pivot_row][pivot_column];
        rowMultiply(current_row, scalar);

        if (scalar != 1) {
            System.out.println("Scale row " + (current_row + 1) + " to 1\n");
            printMatrix();
        }

        System.out.println();

        for (int r = current_row - 1; r >= 0; r--) {
            if (matrix[r][pivot_column] != 0) {
                double sc = matrix[r][pivot_column] / matrix[current_row][pivot_column];
                rowAdd(r, current_row, -sc);
                if (sc != 0) {
                    System.out
                            .println("Add row " + (current_row + 1) + " with scalar " + -sc + " to row " + (r + 1)
                                    + "\n");
                    printMatrix();
                    System.out.println();
                }
            }
        }

        pivot_column = -1;
        pivot_row = -1;

        current_row--;

        reducedRowEchelonForm();

    }

    public void printMatrix() {
        for (double[] r : matrix) {
            for (double c : r) {
                System.out.print(format(c) + "\t");

            }

            System.out.println();
        }
    }

    public static String format(double number) {
        DecimalFormat df = new DecimalFormat("#,##0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        String formattedValue = df.format(number);
        formattedValue = formattedValue.replaceAll("^-(?=0(\\.0*)?$)", "");
        return formattedValue;
    }

    // 0 = 1st row
    public void rowInterchange(int row1, int row2) {
        double[] temp = matrix[row2];
        matrix[row2] = matrix[row1];
        matrix[row1] = temp;
    }

    public void rowMultiply(int row, double scalar) {
        for (int i = 0; i < matrix[row].length; i++) {
            matrix[row][i] *= scalar;
        }
    }

    public void rowAdd(int rowToBeAdded, int rowToAdd, double scalar) {
        for (int i = 0; i < matrix[rowToBeAdded].length; i++) {
            matrix[rowToBeAdded][i] += (matrix[rowToAdd][i] * scalar);
        }

    }

}
