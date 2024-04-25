package be;

public class Profile {
    private double annualSalary;
    private double overheadMultiplier; // This is a percentage (e.g., 0.20 for 20%)
    private double fixedAnnualAmount;
    private String geography;
    private String team;
    private double annualEffectiveHours;
    private double utilizationPercentage; // This is a percentage (e.g., 0.75 for 75%)

    // Constructor
    public Profile(double annualSalary, double overheadMultiplier, double fixedAnnualAmount,
                   String geography, String team, double annualEffectiveHours, double utilizationPercentage) {
        this.annualSalary = annualSalary;
        this.overheadMultiplier = overheadMultiplier;
        this.fixedAnnualAmount = fixedAnnualAmount;
        this.geography = geography;
        this.team = team;
        this.annualEffectiveHours = annualEffectiveHours;
        this.utilizationPercentage = utilizationPercentage;
    }

    // Getters
    public double getAnnualSalary() {
        return annualSalary;
    }

    public double getOverheadMultiplier() {
        // This returns the overhead multiplier as a decimal factor
        return overheadMultiplier / 100.0;
    }

    public double getFixedAnnualAmount() {
        return fixedAnnualAmount;
    }

    public String getGeography() {
        return geography;
    }

    public String getTeam() {
        return team;
    }

    public double getAnnualEffectiveHours() {
        return annualEffectiveHours;
    }

    public double getUtilizationPercentage() {
        // This returns the utilization percentage as a decimal factor
        return utilizationPercentage / 100.0;
    }

    // Setters
    public void setAnnualSalary(double annualSalary) {
        this.annualSalary = annualSalary;
    }

    public void setOverheadMultiplier(double overheadMultiplier) {
        this.overheadMultiplier = overheadMultiplier;
    }

    public void setFixedAnnualAmount(double fixedAnnualAmount) {
        this.fixedAnnualAmount = fixedAnnualAmount;
    }

    public void setGeography(String geography) {
        this.geography = geography;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setAnnualEffectiveHours(double annualEffectiveHours) {
        this.annualEffectiveHours = annualEffectiveHours;
    }

    public void setUtilizationPercentage(double utilizationPercentage) {
        this.utilizationPercentage = utilizationPercentage;
    }
}
