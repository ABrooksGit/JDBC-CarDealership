package com.pluralsight.dealership;


public abstract class Contract {

    private String contracts;
    private Vehicle vehicle;
    private String date;
    private String customerName;
    private String customerEmail;
    private double totalPrice;
    private double monthlyPayment;


    public Contract(String contracts, String date, String customerName, String customerEmail, Vehicle vehicle) {
        this.contracts = contracts;
        this.vehicle = vehicle;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;

    }

    public String getContracts() {
        return contracts;
    }

    public void setContracts(String contracts) {
        this.contracts = contracts;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }


    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }


    public abstract double getMonthlyPayment();

    public abstract double getTotalPrice();








}
