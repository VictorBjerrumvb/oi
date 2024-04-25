package gui.controller;

import gui.helperclases.CalculatorClass;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class CalculatorController {

    @FXML
    private TextField salaryInput;
    @FXML private TextField multiplierInput;
    @FXML private TextField fixedAmountInput;
    @FXML private TextField workingHoursInput;
    @FXML private TextField utilizationInput;
    @FXML private Label outputLabel;

    private CalculatorClass calculator;

    public CalculatorController() {
        calculator = new CalculatorClass();
    }

    @FXML
    private void handleCalculateHourlyRate() {
        try {
            double salary = Double.parseDouble(salaryInput.getText());
            if (salary < 0) throw new IllegalArgumentException("Salary must be non-negative.");

            double multiplier = Double.parseDouble(multiplierInput.getText());
            double fixedAmount = Double.parseDouble(fixedAmountInput.getText());
            double workingHours = Double.parseDouble(workingHoursInput.getText());
            if (workingHours <= 0) throw new IllegalArgumentException("Working hours must be greater than zero.");

            double utilization = Double.parseDouble(utilizationInput.getText());
            if (utilization < 0 || utilization > 100) throw new IllegalArgumentException("Utilization must be between 0% and 100%.");

            double rate = calculator.calculateHourlyRate(salary, multiplier, fixedAmount, workingHours, utilization);
            outputLabel.setText(String.format("Hourly Rate: %.2f", rate));
        } catch (NumberFormatException e) {
            outputLabel.setText("Please enter valid numbers.");
        } catch (IllegalArgumentException e) {
            outputLabel.setText(e.getMessage());
        }
    }
}
