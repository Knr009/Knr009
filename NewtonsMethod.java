import java.util.Scanner;

public class NewtonsMethod {

    // Function to compute the value of f(x)
    public static double function(double x) {
        return x * x - 4;  // Example function f(x) = x^2 - 4
    }

    // Function to compute the derivative f'(x)
    public static double derivative(double x) {
        return 2 * x;  // Derivative of f(x) = 2x
    }

    // Newton's Method implementation
    public static double newtonsMethod(double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        double fx = function(x);
        double dfx = derivative(x);

        int iteration = 0;

        while (Math.abs(fx) > tolerance && iteration < maxIterations) {
            // Check if the derivative is close to zero (avoid division by zero)
            if (Math.abs(dfx) < 1e-6) {
                System.out.println("Derivative too small. Stopping iteration to avoid division by zero.");
                return Double.NaN;
            }

            // Update x using Newton's method formula: x = x - f(x) / f'(x)
            x = x - fx / dfx;

            // Recompute f(x) and f'(x) at the new x
            fx = function(x);
            dfx = derivative(x);

            // Print current iteration and x value
            System.out.printf("Iteration %d: x = %.6f, f(x) = %.6f%n", iteration + 1, x, fx);

            // Increment iteration count
            iteration++;
        }

        // Check for convergence
        if (Math.abs(fx) <= tolerance) {
            System.out.println("Converged successfully.");
        } else {
            System.out.println("Failed to converge within the maximum number of iterations.");
        }

        return x;
    }

    // Main function to test Newton's Method
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for initial guess, tolerance, and maximum iterations
        System.out.print("Enter initial guess: ");
        double initialGuess = scanner.nextDouble();

        System.out.print("Enter tolerance: ");
        double tolerance = scanner.nextDouble();

        System.out.print("Enter maximum iterations: ");
        int maxIterations = scanner.nextInt();

        // Call Newton's method and print the final result
        double result = newtonsMethod(initialGuess, tolerance, maxIterations);

        if (!Double.isNaN(result)) {
            System.out.printf("The root is approximately: %.6f%n", result);
        } else {
            System.out.println("No valid root found.");
        }

        scanner.close();
    }
}
