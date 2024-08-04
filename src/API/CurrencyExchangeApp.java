package API;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CurrencyExchangeApp {
    public static void main(String[] args) {

        // FRAME
        JFrame frame = new JFrame("Currency Exchange");
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);

        // Dark theme colors
        Color backgroundColor = new Color(255, 193, 210);
        Color textColor = new Color(255,255,255);
        Color buttonColor = new Color(255, 175, 204);

        // Set frame background colour
        frame.getContentPane().setBackground(backgroundColor);

        // COMPONENTS
        JLabel amountLabel = new JLabel("Amount:");
        amountLabel.setBounds(165, 20, 150, 25);
        amountLabel.setForeground(textColor);
        frame.add(amountLabel);

        JTextField amountField = new JTextField();
        amountField.setBounds(220, 20, 100, 25);
        amountField.setBackground(buttonColor);
        amountField.setForeground(textColor);
        frame.add(amountField);

        JLabel baseCurrencyLabel = new JLabel("From:");
        baseCurrencyLabel.setBounds(60, 75, 150, 25);
        baseCurrencyLabel.setForeground(textColor);
        frame.add(baseCurrencyLabel);

        String[] currencies = {"USD", "EUR", "CAD", "PLN"};
        JComboBox<String> baseCurrencyBox = new JComboBox<>(currencies);
        baseCurrencyBox.setBounds(100, 75, 100, 25);
        baseCurrencyBox.setBackground(buttonColor);
        baseCurrencyBox.setForeground(textColor);
        frame.add(baseCurrencyBox);

        JLabel targetCurrencyLabel = new JLabel("To:");
        targetCurrencyLabel.setBounds(240, 75, 150, 25);
        targetCurrencyLabel.setForeground(textColor);
        frame.add(targetCurrencyLabel);

        JComboBox<String> targetCurrencyBox = new JComboBox<>(currencies);
        targetCurrencyBox.setBounds(270, 75, 100, 25);
        targetCurrencyBox.setBackground(buttonColor);
        targetCurrencyBox.setForeground(textColor);
        frame.add(targetCurrencyBox);

        JButton convertButton = new JButton("Convert");
        convertButton.setBounds(170, 130, 150, 25);
        convertButton.setBackground(buttonColor);
        convertButton.setForeground(textColor);
        frame.add(convertButton);

        JLabel resultLabel = new JLabel("Converted amount: ");
        resultLabel.setBounds(100, 200, 150, 25);
        resultLabel.setForeground(textColor);
        frame.add(resultLabel);

        JTextField resultField = new JTextField();
        resultField.setBounds(220, 200, 100, 25);
        resultField.setBackground(buttonColor);
        resultField.setForeground(textColor);
        resultField.setEditable(false);
        frame.add(resultField);

        // Add action listener for the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String baseCurrency = baseCurrencyBox.getSelectedItem().toString();
                    String targetCurrency = targetCurrencyBox.getSelectedItem().toString();
                    String response = CurrencyAPI.getRates(baseCurrency, targetCurrency);
                    CurrencyConverter converter = new CurrencyConverter();
                    converter.parseRates(response, targetCurrency);
                    double amount = Double.parseDouble(amountField.getText());
                    double convertedAmount = converter.convert(amount);
                    resultField.setText(String.format("%.2f", convertedAmount));
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(frame, "Error fetching data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Set frame to visible
        frame.setVisible(true);
    }
}
