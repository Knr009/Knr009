import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FloatingPointSimulator {

    public static void main(String[] args) {
        // Create the main GUI frame
        JFrame frame = new JFrame("Floating-Point Arithmetic Simulator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Create a panel to hold components
        JPanel panel = new JPanel();
        frame.add(panel);
        placeComponents(panel);

        // Display the frame
        frame.setVisible(true);
    }

    /**
     * Place GUI components on the panel.
     *
     * @param panel the panel to place components on.
     */
    private static void placeComponents(JPanel panel) {
        panel.setLayout(null);

        // Input fields and labels for numbers
        JLabel label1 = new JLabel("Enter decimal number 1:");
        label1.setBounds(10, 20, 200, 25);
        panel.add(label1);

        JTextField decimalField1 = new JTextField(20);
        decimalField1.setBounds(220, 20, 165, 25);
        panel.add(decimalField1);

        JLabel label2 = new JLabel("Enter decimal number 2:");
        label2.setBounds(10, 50, 200, 25);
        panel.add(label2);

        JTextField decimalField2 = new JTextField(20);
        decimalField2.setBounds(220, 50, 165, 25);
        panel.add(decimalField2);

        // Output area for displaying binary results
        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setBounds(10, 180, 200, 25);
        panel.add(resultLabel);

        JTextArea resultArea = new JTextArea();
        resultArea.setBounds(220, 180, 300, 150);
        panel.add(resultArea);

        // Buttons for operations
        JButton addButton = new JButton("Add");
        addButton.setBounds(10, 80, 80, 25);
        panel.add(addButton);

        JButton subButton = new JButton("Subtract");
        subButton.setBounds(100, 80, 100, 25);
        panel.add(subButton);

        JButton mulButton = new JButton("Multiply");
        mulButton.setBounds(210, 80, 100, 25);
        panel.add(mulButton);

        JButton divButton = new JButton("Divide");
        divButton.setBounds(320, 80, 100, 25);
        panel.add(divButton);

        // Add Action Listeners for arithmetic operations
        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = getValidInput(decimalField1, resultArea);
                double num2 = getValidInput(decimalField2, resultArea);

                if (!Double.isNaN(num1) && !Double.isNaN(num2)) {
                    String binary1 = decimalToBinaryString(num1);
                    String binary2 = decimalToBinaryString(num2);
                    String binaryResult = binaryAddition(binary1, binary2);
                    double result = binaryStringToDecimal(binaryResult);

                    resultArea.setText("Binary 1: " + binary1 + "\nBinary 2: " + binary2 +
                            "\nResult (Binary): " + binaryResult +
                            "\nResult (Decimal): " + result);
                }
            }
        });

        // Subtraction
        subButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = getValidInput(decimalField1, resultArea);
                double num2 = getValidInput(decimalField2, resultArea);

                if (!Double.isNaN(num1) && !Double.isNaN(num2)) {
                    String binary1 = decimalToBinaryString(num1);
                    String binary2 = decimalToBinaryString(num2);
                    String binaryResult = binarySubtraction(binary1, binary2);
                    double result = binaryStringToDecimal(binaryResult);

                    resultArea.setText("Binary 1: " + binary1 + "\nBinary 2: " + binary2 +
                            "\nResult (Binary): " + binaryResult +
                            "\nResult (Decimal): " + result);
                }
            }
        });

        // Multiplication
        mulButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = getValidInput(decimalField1, resultArea);
                double num2 = getValidInput(decimalField2, resultArea);

                if (!Double.isNaN(num1) && !Double.isNaN(num2)) {
                    String binary1 = decimalToBinaryString(num1);
                    String binary2 = decimalToBinaryString(num2);
                    String binaryResult = binaryMultiplication(binary1, binary2);
                    double result = binaryStringToDecimal(binaryResult);

                    resultArea.setText("Binary 1: " + binary1 + "\nBinary 2: " + binary2 +
                            "\nResult (Binary): " + binaryResult +
                            "\nResult (Decimal): " + result);
                }
            }
        });

        // Division
        divButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                double num1 = getValidInput(decimalField1, resultArea);
                double num2 = getValidInput(decimalField2, resultArea);

                if (!Double.isNaN(num1) && !Double.isNaN(num2)) {
                    if (num2 == 0) {
                        resultArea.setText("Error: Division by zero is undefined.");
                    } else {
                        String binary1 = decimalToBinaryString(num1);
                        String binary2 = decimalToBinaryString(num2);
                        String binaryResult = binaryDivision(binary1, binary2);
                        double result = binaryStringToDecimal(binaryResult);

                        resultArea.setText("Binary 1: " + binary1 + "\nBinary 2: " + binary2 +
                                "\nResult (Binary): " + binaryResult +
                                "\nResult (Decimal): " + result);
                    }
                }
            }
        });
    }

    private static double getValidInput(JTextField textField, JTextArea resultArea) {
        try {
            return Double.parseDouble(textField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Error: Invalid input. Please enter a valid decimal number.");
            return Double.NaN;
        }
    }

    public static String decimalToBinaryString(double decimal) {
        long bits = Double.doubleToLongBits(decimal);
        return Long.toBinaryString(bits);
    }

    public static double binaryStringToDecimal(String binaryString) {
        long bits = Long.parseUnsignedLong(binaryString, 2);
        return Double.longBitsToDouble(bits);
    }

    public static String binaryAddition(String binaryString1, String binaryString2) {
        double num1 = binaryStringToDecimal(binaryString1);
        double num2 = binaryStringToDecimal(binaryString2);
        double result = num1 + num2;
        return decimalToBinaryString(result);
    }

    public static String binarySubtraction(String binaryString1, String binaryString2) {
        double num1 = binaryStringToDecimal(binaryString1);
        double num2 = binaryStringToDecimal(binaryString2);
        double result = num1 - num2;
        return decimalToBinaryString(result);
    }

    public static String binaryMultiplication(String binaryString1, String binaryString2) {
        double num1 = binaryStringToDecimal(binaryString1);
        double num2 = binaryStringToDecimal(binaryString2);
        double result = num1 * num2;
        return decimalToBinaryString(result);
    }

    public static String binaryDivision(String binaryString1, String binaryString2) {
        double num1 = binaryStringToDecimal(binaryString1);
        double num2 = binaryStringToDecimal(binaryString2);
        double result = num1 / num2;
        return decimalToBinaryString(result);
    }
}
