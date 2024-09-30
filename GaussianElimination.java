import java.util.Scanner;

public class GaussianElimination {

    // Function to perform Gaussian Elimination
    public static void gaussianElimination(double[][] matrix, double[] result) {
        int n = result.length;

        // Forward Elimination
        for (int i = 0; i < n; i++) {
            // Find the pivot element
            int max = i;
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(matrix[j][i]) > Math.abs(matrix[max][i])) {
                    max = j;
                }
            }

            // Swap rows if needed
            double[] temp = matrix[i];
            matrix[i] = matrix[max];
            matrix[max] = temp;

            double t = result[i];
            result[i] = result[max];
            result[max] = t;

            // Check for singular matrix (no unique solution)
            if (Math.abs(matrix[i][i]) <= 1e-10) {
                System.out.println("Matrix is singular or nearly singular. No unique solution.");
                return;
            }

            // Eliminate all elements below the pivot
            for (int j = i + 1; j < n; j++) {
                double factor = matrix[j][i] / matrix[i][i];
                result[j] -= factor * result[i];
                for (int k = i; k < n; k++) {
                    matrix[j][k] -= factor * matrix[i][k];
                }
            }
        }

        // Back Substitution
        double[] solution = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += matrix[i][j] * solution[j];
            }
            solution[i] = (result[i] - sum) / matrix[i][i];
        }

        // Display the solution
        System.out.println("Solution:");
        for (int i = 0; i < n; i++) {
            System.out.printf("x%d = %.6f%n", i + 1, solution[i]);
        }
    }

    // Main function to test Gaussian Elimination
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input: number of variables
        System.out.print("Enter the number of variables: ");
        int n = scanner.nextInt();

        // Input: coefficient matrix
        double[][] matrix = new double[n][n];
        System.out.println("Enter the coefficient matrix:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                matrix[i][j] = scanner.nextDouble();
            }
        }

        // Input: result vector
        double[] result = new double[n];
        System.out.println("Enter the result vector:");
        for (int i = 0; i < n; i++) {
            result[i] = scanner.nextDouble();
        }

        // Solve using Gaussian Elimination
        gaussianElimination(matrix, result);

        scanner.close();
    }
}
