import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculatorGUI extends JFrame implements ActionListener {
    // GUI Components
    private JTextField display;
    private JPanel panel;
    private String[] buttonLabels = {
        "7", "8", "9", "/",
        "4", "5", "6", "*",
        "1", "2", "3", "-",
        "C", "0", "=", "+"
    };
    private JButton[] buttons = new JButton[16];

    // Variables to track calculation state
    private String num1 = "";
    private String operator = "";
    private boolean isOperatorClicked = false;

    // Instantiate our OOP Calculator logic class
    private Calculator calc = new Calculator();

    public CalculatorGUI() {
        // 1. Set up the main window frame
        setTitle("OOP Java Calculator");
        setSize(350, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 2. Create and customize the text display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // 3. Create the button layout grid (4 rows, 4 columns)
        panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // 4. Initialize and add buttons to the panel
        for (int i = 0; i < buttonLabels.length; i++) {
            buttons[i] = new JButton(buttonLabels[i]);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 20));
            buttons[i].addActionListener(this); // Listen for button clicks
            panel.add(buttons[i]);
        }

        add(panel, BorderLayout.CENTER);
        setVisible(true);
    }

    // 5. Handle button clicks (Event Handling)
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Clear button logic
        if (command.equals("C")) {
            num1 = "";
            operator = "";
            display.setText("");
            isOperatorClicked = false;
        } 
        // Equals button logic (Perform calculation)
        else if (command.equals("=")) {
            String num2 = display.getText();
            try {
                double result = 0;
                double n1 = Double.parseDouble(num1);
                double n2 = Double.parseDouble(num2);

                switch (operator) {
                    case "+": result = calc.add(n1, n2); break;
                    case "-": result = calc.subtract(n1, n2); break;
                    case "*": result = calc.multiply(n1, n2); break;
                    case "/": result = calc.divide(n1, n2); break;
                }
                display.setText(String.valueOf(result));
            } catch (Exception ex) {
                display.setText("Error");
            }
            isOperatorClicked = false;
        } 
        // Operator buttons (+, -, *, /)
        else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            num1 = display.getText();
            operator = command;
            isOperatorClicked = true;
        } 
        // Number buttons (0-9)
        else {
            if (isOperatorClicked) {
                display.setText(""); // Clear display for the second number
                isOperatorClicked = false;
            }
            display.setText(display.getText() + command);
        }
    }

    // Main method to run the GUI app
    public static void main(String[] args) {
        new CalculatorGUI();
    }
}
