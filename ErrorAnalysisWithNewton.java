import java.util.Scanner;

public class ErrorAnalysisWithNewton {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // User input for a, b, c with default values if user hits return
        System.out.println("Newton's Method is used to find the roots of a quadratic equation ax^2 + bx + c.");
        double a = getInputOrDefault(scanner, "Enter value for a (default 1): ", 1.0);
        double b = getInputOrDefault(scanner, "Enter value for b (default 1): ", 1.0);
        double c = getInputOrDefault(scanner, "Enter value for c (default 1): ", 1.0);

        double initialGuess = getInputOrDefault(scanner, "Enter initial guess: ", 1.0);
        double tolerance = getInputOrDefault(scanner, "Enter tolerance: ", 1e-7);
        int maxIterations = (int) getInputOrDefault(scanner, "Enter maximum iterations: ", 1000);

        System.out.println("\nApplying Standard Newton's Method to find the root of the equation...");
        // Basic Newton's Method
        double root = newtonsMethod(a, b, c, initialGuess, tolerance, maxIterations);
        System.out.println("Root found using Standard Newton's Method: " + root);

        System.out.println("\nIntroducing Truncation Error in Newton's Method...");
        // Newton's Method with Truncation Error
        double rootWithTruncationError = newtonsMethodWithTruncationError(a, b, c, initialGuess, tolerance, maxIterations);
        System.out.println("Root with Truncation Error: " + rootWithTruncationError);

        System.out.println("\nIntroducing Propagation Error in Newton's Method...");
        // Newton's Method with Propagation Error
        double initialError = 0.01;  // Modify this to test the level of error needed
        double rootWithPropagationError = newtonsMethodWithPropagationError(a, b, c, initialGuess, initialError, tolerance, maxIterations);
        System.out.println("Root with Propagation Error: " + rootWithPropagationError);

        // Analysis of the results
        analyzeResults(root, rootWithTruncationError, rootWithPropagationError);
    }

    // Method to get input or default value
    private static double getInputOrDefault(Scanner scanner, String prompt, double defaultValue) {
        System.out.print(prompt);
        String input = scanner.nextLine();
        if (input.isEmpty()) {
            return defaultValue;
        } else {
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input, using default value: " + defaultValue);
                return defaultValue;
            }
        }
    }

    // Standard Newton's Method
    public static double newtonsMethod(double a, double b, double c, double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double fx = a * x * x + b * x + c; // f(x) = ax^2 + bx + c
            double fpx = 2 * a * x + b; // f'(x) = 2ax + b

            if (Math.abs(fpx) < tolerance) {
                System.out.println("Derivative is too small, stopping iteration.");
                return x;
            }

            double xNext = x - fx / fpx; // Newton's method iteration

            if (Math.abs(xNext - x) < tolerance) {
                System.out.println("The method has converged to a root within the desired tolerance.");
                return xNext; // Return root if within tolerance
            }

            x = xNext; // Update x for next iteration
        }

        System.out.println("Max iterations reached without convergence.");
        return x; // Return the last approximation
    }

    // Newton's Method with Truncation Error
    public static double newtonsMethodWithTruncationError(double a, double b, double c, double initialGuess, double tolerance, int maxIterations) {
        double x = initialGuess;
        for (int i = 0; i < maxIterations; i++) {
            double fx = a * x * x + b * x + c; // f(x) = ax^2 + bx + c
            double fpx = 2 * a * x + b; // f'(x) = 2ax + b

            // Truncate to 4 decimal places to simulate truncation error
            fpx = Math.round(fpx * 10000.0) / 10000.0;

            if (Math.abs(fpx) < tolerance) {
                System.out.println("Derivative is too small, stopping iteration.");
                return x;
            }

            double xNext = x - fx / fpx; // Newton's method iteration

            if (Math.abs(xNext - x) < tolerance) {
                System.out.println("The method has converged to a root within the desired tolerance, even with truncation error.");
                return xNext; // Return root if within tolerance
            }

            x = xNext; // Update x for next iteration
        }

        System.out.println("Max iterations reached without convergence.");
        return x; // Return the last approximation
    }

    // Newton's Method with Propagation Error
    public static double newtonsMethodWithPropagationError(double a, double b, double c, double initialGuess, double initialError, double tolerance, int maxIterations) {
        double x = initialGuess + initialError; // Start with an initial error
        for (int i = 0; i < maxIterations; i++) {
            double fx = a * x * x + b * x + c; // f(x) = ax^2 + bx + c
            double fpx = 2 * a * x + b; // f'(x) = 2ax + b

            // Introduce random error in the derivative
            fpx += (Math.random() - 0.5) * initialError;

            if (Math.abs(fpx) < tolerance) {
                System.out.println("Derivative is too small, stopping iteration.");
                return x;
            }

            double xNext = x - fx / fpx; // Newton's method iteration

            if (Math.abs(xNext - x) < tolerance) {
                System.out.println("The method has converged to a root within the desired tolerance, even with propagation error.");
                return xNext; // Return root if within tolerance
            }

            x = xNext; // Update x for next iteration
        }

        System.out.println("Max iterations reached without convergence.");
        return x; // Return the last approximation
    }

    // Analysis of Results
    private static void analyzeResults(double root, double truncationRoot, double propagationRoot) {
        System.out.println("\nAnalysis of Results:");
        System.out.println("Standard Newton's Method finds the root of the equation without any errors introduced.");
        System.out.println("Truncation Error simulates rounding of intermediate results, introducing small errors.");
        System.out.println("Propagation Error simulates random errors in the function's derivative, showing how errors can affect convergence.");

        System.out.println("Root (Standard): " + root);
        System.out.println("Root (Truncation Error): " + truncationRoot);
        System.out.println("Root (Propagation Error): " + propagationRoot);

        System.out.println("Difference between Standard and Truncation Error: " + Math.abs(root - truncationRoot));
        System.out.println("Difference between Standard and Propagation Error: " + Math.abs(root - propagationRoot));
    }
}
