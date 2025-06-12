package com.pluralsight.dealership;

import java.util.ArrayList;


public class Dealership {
    private int dealershipID;
    private final String dealershipName;
    private final String address;
    private final String phone;


    private final ArrayList<Vehicle> inventory;

    public Dealership(String dealershipName, String address, String phone) {

        this.dealershipName = dealershipName;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();

    }

    public Dealership(int dealershipID, String dealershipName, String address, String phone) {
        this.dealershipID = dealershipID;
        this.dealershipName = dealershipName;
        this.address = address;
        this.phone = phone;
        this.inventory = new ArrayList<>();

    }

    public int getDealershipID() {
        return dealershipID;
    }

    public void setDealershipID(int dealershipID) {
        this.dealershipID = dealershipID;
    }

    public String getDealershipName() {
        return dealershipName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public ArrayList<Vehicle> getVehicleByVinNumber(int vin) {
        ArrayList<Vehicle> endResult = new ArrayList<>();
        for (Vehicle v : inventory) {
            if (v.getVin() == vin) {
                endResult.add(v);
            }
        }
        return  endResult;
    }



    public ArrayList<Vehicle> getVehicleByPrice(double min , double max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
            for(Vehicle v : inventory){
               if(v.getPrice() >= min && v.getPrice() <= max){
                   endResult.add(v);
                }
            }
            return endResult;

    }
    public ArrayList<Vehicle> getVehicleByMakeModel(String make, String model){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getMake().equalsIgnoreCase(make)){
                endResult.add(v);
            }
        }

        for (Vehicle v : inventory){
            if (v.getModel().equalsIgnoreCase(model)){
                endResult.add(v);
            }
        }

        return endResult;


    }
    public ArrayList<Vehicle> getVehicleByYear(int min , int max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for ( Vehicle v : inventory){
            if(v.getYear() >= min && v.getYear() <= max ){
                endResult.add(v);
            }
        }
        return endResult;


    }
    public ArrayList<Vehicle> getVehicleByColor(String color){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getColor().equalsIgnoreCase(color)){
                endResult.add(v);
            }
        }

        return endResult;
    }
    public ArrayList<Vehicle> getVehicleByMileage(int min, int max){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getOdometer() >= min && v.getOdometer() <= max){
                endResult.add(v);
            }
        }


        return endResult;

    }
    public ArrayList<Vehicle> getVehicleByType(String type){
        ArrayList<Vehicle>endResult = new ArrayList<>();
        for(Vehicle v : inventory){
            if(v.getVehicleType().equalsIgnoreCase(type)){
                endResult.add(v);
            }
        }



        return endResult;
    }
    public ArrayList<Vehicle> getAllVehicles(){
        return inventory;
    }

    public void addVehicle(Vehicle vehicle){

        inventory.add(vehicle);



    }

    public void removeVehicle(Vehicle vehicle){

        inventory.remove(vehicle);
    }


    public String toStringLog() {



        return  this.dealershipName + "|" +
                this.address + "|"+
                this.phone + "|";

    }




}







