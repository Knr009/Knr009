import java.util.Random;
import java.util.function.Function;

public class RootFinding {

    public static void main(String[] args) {
        Random rand = new Random();
        int numEquations = 100;
        double[][] coefficients = new double[numEquations][3];

        // Generate 100 random quadratic equations
        for (int i = 0; i < numEquations; i++) {
            coefficients[i][0] = rand.nextDouble() * 10 - 5; // a: range [-5, 5]
            coefficients[i][1] = rand.nextDouble() * 10 - 5; // b: range [-5, 5]
            coefficients[i][2] = rand.nextDouble() * 10 - 5; // c: range [-5, 5]
        }

        for (int i = 0; i < numEquations; i++) {
            double a = coefficients[i][0];
            double b = coefficients[i][1];
            double c = coefficients[i][2];
            System.out.println("Equation " + (i + 1) + ": " + a + "x^2 + " + b + "x + " + c);

            // Bisection Method
            long bisectionTime = measureTime(() -> {
                try {
                    double root = bisectionMethod(a, b, c, -10, 10, 1e-6);
                    System.out.println("Bisection Method Root: " + root);
                } catch (Exception e) {
                    System.out.println("Bisection Method failed: " + e.getMessage());
                }
            });
            System.out.println("Bisection Method Time: " + bisectionTime + " ns");
            System.out.println("The Bisection Method works by repeatedly dividing an interval in half and selecting the subinterval in which the root lies, until the desired tolerance is achieved. This guarantees convergence if the function is continuous.");

            // Secant Method
            long secantTime = measureTime(() -> {
                try {
                    double root = secantMethod(a, b, c, -10, 10, 1e-6);
                    System.out.println("Secant Method Root: " + root);
                } catch (Exception e) {
                    System.out.println("Secant Method failed: " + e.getMessage());
                }
            });
            System.out.println("Secant Method Time: " + secantTime + " ns");
            System.out.println("The Secant Method is an iterative method that uses two initial approximations to estimate the root by drawing a secant line between these points. It converges faster than the Bisection Method but does not guarantee convergence in all cases.");

            // Fixed-Point Iteration
            long fixedPointTime = measureTime(() -> {
                try {
                    double root = fixedPointIteration(a, b, c, 0, 1e-6);
                    System.out.println("Fixed-Point Iteration Root: " + root);
                } catch (Exception e) {
                    System.out.println("Fixed-Point Iteration failed: " + e.getMessage());
                }
            });
            System.out.println("Fixed-Point Iteration Time: " + fixedPointTime + " ns");
            System.out.println("Fixed-Point Iteration rewrites the equation in the form x = g(x) and iteratively applies g(x) until convergence. The convergence depends on the choice of g(x) and the initial guess.");

            // Brute-Force Method
            long bruteForceTime = measureTime(() -> {
                try {
                    double root = bruteForceRootFinding(a, b, c, -10, 10, 0.01);
                    System.out.println("Brute-Force Root: " + root);
                } catch (Exception e) {
                    System.out.println("Brute-Force Method failed: " + e.getMessage());
                }
            });
            System.out.println("Brute-Force Method Time: " + bruteForceTime + " ns");
            System.out.println("The Brute-Force Method checks values incrementally within a given range to find a root. It is simple but computationally expensive compared to other methods.");

            System.out.println();
        }
    }

    // Bisection Method
    /**
     * Bisection Method for finding roots of a quadratic equation.
     * This method works by repeatedly dividing an interval in half and selecting the subinterval where the root lies.
     * The method continues until the interval size is less than the specified tolerance.
     * Pros: Guaranteed to converge if the function is continuous.
     * Cons: Slower compared to other methods like Secant or Newton's Method.
     */
    public static double bisectionMethod(double a, double b, double c, double start, double end, double tolerance) {
        Function<Double, Double> f = (x) -> a * x * x + b * x + c;
        double mid = start;
        while ((end - start) >= tolerance) {
            mid = (start + end) / 2;
            if (f.apply(mid) == 0.0)
                return mid;
            else if (f.apply(start) * f.apply(mid) < 0)
                end = mid;
            else
                start = mid;
        }
        return mid;
    }

    // Secant Method
    /**
     * Secant Method for finding roots of a quadratic equation.
     * This method uses two initial approximations and iteratively refines them to find the root.
     * It is faster than the Bisection Method but may not always converge.
     */
    public static double secantMethod(double a, double b, double c, double x0, double x1, double tolerance) {
        Function<Double, Double> f = (x) -> a * x * x + b * x + c;
        double x2;
        while (Math.abs(x1 - x0) >= tolerance) {
            if (f.apply(x1) - f.apply(x0) == 0) {
                throw new ArithmeticException("Division by zero in Secant Method");
            }
            x2 = x1 - (f.apply(x1) * (x1 - x0)) / (f.apply(x1) - f.apply(x0));
            x0 = x1;
            x1 = x2;
        }
        return x1;
    }

    // Fixed-Point Iteration
    /**
     * Fixed-Point Iteration for finding roots of a quadratic equation.
     * This method rewrites the equation in the form x = g(x) and iteratively applies g(x) until convergence.
     * The choice of g(x) affects the convergence and the initial guess plays a significant role.
     */
    public static double fixedPointIteration(double a, double b, double c, double guess, double tolerance) {
        if (b == 0) {
            throw new ArithmeticException("Division by zero in Fixed-Point Iteration");
        }
        Function<Double, Double> g = (x) -> -((a * x * x + c) / b);
        double x0 = guess;
        double x1 = g.apply(x0);
        while (Math.abs(x1 - x0) >= tolerance) {
            x0 = x1;
            x1 = g.apply(x0);
        }
        return x1;
    }

    // Brute-Force Method
    /**
     * Brute-Force Method for finding roots of a quadratic equation.
     * This method checks values incrementally within a specified range to find the root.
     * It is simple but computationally expensive compared to other methods.
     */
    public static double bruteForceRootFinding(double a, double b, double c, double start, double end, double step) {
        for (double x = start; x <= end; x += step) {
            double fx1 = a * x * x + b * x + c;
            double fx2 = a * (x + step) * (x + step) + b * (x + step) + c;
            if (fx1 * fx2 <= 0) {
                return x; // Root found between x and x + step
            }
        }
        throw new ArithmeticException("Root not found in the interval");
    }

    // Timer Function
    /**
     * Measures the time taken by a method to execute in nanoseconds.
     * @param method The method to be executed.
     * @return The time taken in nanoseconds.
     */
    public static long measureTime(Runnable method) {
        long startTime = System.nanoTime();
        method.run();
        return System.nanoTime() - startTime;
    }
}
