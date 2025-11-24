import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoanCalculator2OA extends JFrame {

    private JTextField loanAmountField;
    private JTextField annualInterestRateField;
    private JTextField termYearsField;
    private JLabel resultLabel;

    public LoanCalculator2OA() {
        setTitle("Loan Payment Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create UI components
        loanAmountField = new JTextField(10);
        annualInterestRateField = new JTextField(10);
        termYearsField = new JTextField(10);
        resultLabel = new JLabel("Monthly Payment: $0.00");

        JButton calculateButton = new JButton("Calculate");

        // Layout
        setLayout(new GridLayout(5, 2, 10, 10));
        add(new JLabel("Loan Amount ($):"));
        add(loanAmountField);
        add(new JLabel("Annual Interest Rate (%):"));
        add(annualInterestRateField);
        add(new JLabel("Term (Years):"));
        add(termYearsField);
        add(calculateButton);
        add(resultLabel);

        // Button action
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateMonthlyPayment();
            }
        });
    }

    private void calculateMonthlyPayment() {
        try {
            double principal = Double.parseDouble(loanAmountField.getText());
            double annualRate = Double.parseDouble(annualInterestRateField.getText());
            int years = Integer.parseInt(termYearsField.getText());

            double monthlyRate = (annualRate / 100) / 12;
            int months = years * 12;

            double monthlyPayment = (principal * monthlyRate) /
                    (1 - Math.pow(1 + monthlyRate, -months));

            resultLabel.setText(String.format("Monthly Payment: $%.2f", monthlyPayment));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numeric values.",
                    "Input Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoanCalculator2OA().setVisible(true);
        });
    }
}
