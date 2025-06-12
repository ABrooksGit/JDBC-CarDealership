package com.pluralsight.dealership;

import com.pluralsight.userinterface.ColorCodes;

import java.text.NumberFormat;

public class Vehicle {

    private int vin;
    private int year;
    private String make;
    private String model;
    private String vehicleType;
    private String color;
    private int odometer;
    private double price;

    public Vehicle(int vin, int year, String make, String model, String vehicleType, String color, int odometer, double price) {
        this.vin = vin;
        this.year = year;
        this.make = make;
        this.model = model;
        this.vehicleType = vehicleType;
        this.color = color;
        this.odometer = odometer;
        this.price = price;
    }

    public int getVin() {
        return vin;
    }

    public int getYear() {
        return year;
    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getColor() {
        return color;
    }

    public int getOdometer() {
        return odometer;
    }

    public double getPrice() {
        return price;
    }


    public String toString() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String dollarSign = currencyFormatter.format(price);

        return String.format(
                "%-7s %-8s %-12s %-15s %-12s %-10s %-10s %-11s",
                vin, year, make, model, vehicleType, color, odometer, ColorCodes.BLUE + dollarSign + ColorCodes.RESET
        );
    }

    public String toStringTwo() {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance();
        String dollarSign = currencyFormatter.format(price);


        return String.format(
                "%-10s %-15s %-12s %-12s %-10s %-10s %-18s %-15s",
                vin, year, make, model, vehicleType, color, odometer, dollarSign
        );
    }




    public static String getFormattedHeader() {
        return """
                
                  VIN      YEAR       MAKE         MODEL       VEHICLE TYPE   COLOR     ODOMETER   PRICE ($)
                -------|--------|------------|---------------|------------|----------|----------|-----------""";
    }



    public String toStringLog() {
        return String.format("%s|%s|%s|%s|%s|%s|%s|%.2f" ,vin, year, make, model, color, vehicleType, odometer, price);
    }



}
