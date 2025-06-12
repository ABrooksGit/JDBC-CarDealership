package com.pluralsight.dealership;

import java.text.NumberFormat;
import java.time.LocalDate;

public class LeaseContract extends Contract {

    private double expectedEndingValue;
    private double leaseFee;

    public LeaseContract(String date, String customerName, String customerEmail, Vehicle vehicle, double totalVehiclePrice, double originalPrice) {
        super("Lease", date, customerName, customerEmail, vehicle);
        this.expectedEndingValue = totalVehiclePrice * .5;
        this.leaseFee = originalPrice * .07;
    }

    public LeaseContract(LocalDate date, String customerName, String customerEmail, Vehicle vehicle, double totalVehiclePrice, double originalPrice) {
        super("Lease", String.valueOf(date), customerName, customerEmail, vehicle);
        this.expectedEndingValue = totalVehiclePrice * .5;
        this.leaseFee = originalPrice * .07;
    }

    public double getExpectedEndingValue() {
        return expectedEndingValue;
    }

    public void setExpectedEndingValue(double expectedEndingValue) {
        this.expectedEndingValue = expectedEndingValue;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public double getTotalPrice() {

        double totalVehiclePrice = getVehicle().getPrice();



        return totalVehiclePrice + leaseFee - expectedEndingValue;


    }


    @Override
    public double getMonthlyPayment() {

        double totalVehiclePrice = getVehicle().getPrice();


        double financeAmount = totalVehiclePrice - expectedEndingValue;

        financeAmount += leaseFee;

        double interestRate = 0.04;
        int financedLease = 36;

        double monthlyInterestRate = interestRate / 12;


        double interest = financeAmount * monthlyInterestRate;

        return (financeAmount + interest) / financedLease;

    }

    @Override
    public String toString() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String dollarSign = currencyFormatter.format(getTotalPrice());


        return String.format("%-12s  %-20s  %-35s  %-70s  %-15s  %-16.2f",
                getDate(),
                getCustomerName(),
                getCustomerEmail(),
                getVehicle().toStringTwo(),
                dollarSign,
                getMonthlyPayment());
    }

    public static String getFormattedHeader() {
        return """
            Date        | Name                | Email                                 | Vehicle Information List -------------------------------------------------------------------------------->| Total Price   | Monthly payment
            |-----------|---------------------|---------------------------------------|-----------------------------------------------------------------------------------------------------------|---------------|------------------
            """;
    }



    public String toStringLog() {
        return String.format("LEASE|%s|%s|%s|%s|%s|%.2f",
                getDate(), getCustomerName(), getCustomerEmail(),
                getVehicle().toStringLog(), getTotalPrice(), getMonthlyPayment());
    }



}



