import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver
 */
public class Driver {

    public static void main(String[] args) {

        File file = new File(args[0]);

        Scanner scanner;
        try {
            scanner = new Scanner(file);
            int rows = scanner.nextInt();
            int cols = scanner.nextInt();
            scanner.nextLine();

            double[][] matrix = new double[rows][cols];
            while (scanner.hasNext()) {
                for (int r = 0; r < rows; r++) {
                    for (int c = 0; c < cols; c++) {
                        matrix[r][c] = scanner.nextInt();
                    }
                    scanner.nextLine();
                }
            }

            REF ref = new REF(matrix, true);

            ref.rowEchelonForm();

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Could not open " + args[0]);
        }

    }
}