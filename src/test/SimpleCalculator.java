package test;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class SimpleCalculator extends JFrame {
    private JTextField num1Field, num2Field;
    private JLabel resultLabel;

    public SimpleCalculator() {
        setTitle("Modern Calculator");
        setSize(350, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
        
        JPanel inputPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        inputPanel.add(new JLabel("Number 1:"));
        num1Field = new JTextField();
        inputPanel.add(num1Field);
        inputPanel.add(new JLabel("Number 2:"));
        num2Field = new JTextField();
        inputPanel.add(num2Field);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton[] buttons = {new JButton("+"), new JButton("-"), new JButton("*"), new JButton("/")};
        for (JButton btn : buttons) {
            btn.setPreferredSize(new Dimension(50, 40));
            btn.addActionListener(e -> calculate(e.getActionCommand()));
            buttonPanel.add(btn);
        }

        resultLabel = new JLabel("Result: ", SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        resultLabel.setBorder(new EmptyBorder(10, 0, 0, 0));

        mainPanel.add(inputPanel);
        mainPanel.add(buttonPanel);
        mainPanel.add(resultLabel);

        add(mainPanel);
        setVisible(true);
    }

    private void calculate(String operator) {
        try {
            double n1 = Double.parseDouble(num1Field.getText());
            double n2 = Double.parseDouble(num2Field.getText());
            double result = 0;
            switch (operator) {
                case "+": result = n1 + n2; break;
                case "-": result = n1 - n2; break;
                case "*": result = n1 * n2; break;
                case "/":
                    if (n2 == 0) {
                        resultLabel.setText("Result: Error (Div by 0)");
                        return;
                    }
                    result = n1 / n2;
                    break;
            }
            resultLabel.setText("Result: " + String.format("%.2f", result));
        } catch (NumberFormatException ex) {
            resultLabel.setText("Result: Invalid Input");
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(SimpleCalculator::new);
    }
}
