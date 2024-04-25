package be;

public class Rate {
    private Profile profile;

    public Rate(Profile profile) {
        this.profile = profile;
    }

    public double calculateHourlyRate() {
        if (profile.getAnnualEffectiveHours() == 0 || profile.getUtilizationPercentage() == 0) {
            throw new IllegalArgumentException("Annual effective hours and utilization percentage must be greater than 0");
        }
        double overheadAdjustedSalary = profile.getAnnualSalary() * (1 + profile.getOverheadMultiplier());
        double annualCost = overheadAdjustedSalary + profile.getFixedAnnualAmount();
        return annualCost / (profile.getAnnualEffectiveHours() * profile.getUtilizationPercentage());
    }

    public double calculateDayRate(double hoursInDay) {
        if (hoursInDay <= 0) {
            throw new IllegalArgumentException("Hours in a day must be greater than 0");
        }
        return calculateHourlyRate() * hoursInDay;
    }

    public double applyMarkup(double hourlyRate, double markup) {
        if (markup < 0 || markup > 100) {
            throw new IllegalArgumentException("Markup must be between 0 and 100");
        }
        return hourlyRate * (1 + markup / 100.0);
    }

    public double applyGrossMargin(double hourlyRate, double gm) {
        if (gm < 0 || gm > 100) {
            throw new IllegalArgumentException("Gross margin must be between 0 and 100");
        }
        if (gm == 100) {
            throw new IllegalArgumentException("Gross margin cannot be 100%");
        }
        return hourlyRate / (1 - gm / 100.0);
    }
}
