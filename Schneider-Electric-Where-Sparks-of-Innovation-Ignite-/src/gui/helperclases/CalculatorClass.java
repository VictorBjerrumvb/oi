package gui.helperclases;

public class CalculatorClass {

    public double calculateHourlyRate(double salary, double multiplier, double fixedAmount, double workingHours, double utilization) {
        double baseRate = salary / workingHours;
        baseRate += fixedAmount / workingHours;
        baseRate *= (1 + multiplier);
        return baseRate * (utilization / 100);
    }
}
