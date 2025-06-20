package com.pluralsight.userinterface;

import com.pluralsight.data.*;
import com.pluralsight.dealership.Contract;
import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.LeaseContract;
import com.pluralsight.dealership.SalesContract;
import com.pluralsight.dealership.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class AdminUserInterface {

//    private Dealership d;
//    private  ArrayList<Contract> c = new ArrayList<>();
    private final  Console console = new Console();

    private DealershipDAO d;
    private SalesContractDAO s;
    private LeaseContractDAO l;


    public AdminUserInterface(DealershipDAO d, SalesContractDAO s, LeaseContractDAO l) {
        this.d = d;
        this.s = s;
        this.l = l;
    }


    private void displayAllContacts(){

        System.out.println("Lease Contracts: ");
        l.getLeaseContracts().stream().forEach(l -> System.out.println(l.toString()));
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Sales Contracts: ");
        s.getSalesContracts().stream().forEach( s -> System.out.println(s.toString()));



    }


    public void display() {

        System.out.println("Welcome Admin What do you want to do");

        while (true) {
            String[] homeScreenPrompt = {
                    "Make Vehicle Lease",
                    "Make Vehicle Sale",
                    "Display All",
                    "Display Leases",
                    "Display Sales",
                    "Return to Login Menu",
                    "Quit"
            };

            int input = console.promptForOption(homeScreenPrompt);
            switch (input) {
                case 1:
                    processVehicleByLease();
                    break;
                case 2:
                    processVehicleBySale();
                    break;
                case 3:
                    displayAllContacts();
                    break;
                case 4:
                    displayLease();
                    break;
                case 5:
                    displaySales();
                    break;
                case 6:
                    return;
                case 7:
                    System.out.println("Quitting...");
                    break;
            }
        }
    }












    private void displayLease(){

//        System.out.println(LeaseContract.getFormattedHeader());
//        for(Contract contract : contracts){
//            if(contract instanceof LeaseContract){
//                System.out.println(contract);
//            }
//        }

        List<LeaseContract> leaseContracts = l.getLeaseContracts();
        if(leaseContracts.stream().count() <= 0){
            System.out.println("No lease contacts here...");
        } else{
            System.out.println(" ");
            System.out.println("Lease Contracts: ");
            leaseContracts.stream().forEach(l -> System.out.println(l.toString()));
            System.out.println(" ");
        }


    }

    private void displaySales(/*ArrayList<Contract> contracts*/){
//        System.out.println(SalesContract.getFormattedHeader());
//        for (Contract contract : contracts){
//            if(contract instanceof  SalesContract){
//                System.out.println(contract);
//            }
//        }

        List<SalesContract> salesContracts = s.getSalesContracts();
        if(salesContracts.stream().count() <= 0){
            System.out.println("No sale contracts found...");
        }
        else {
            System.out.println(" ");
            System.out.println("Sales Contracts: ");
            salesContracts.stream().forEach( s -> System.out.println(s.toString()));
            System.out.println(" ");
        }


    }

    private void processVehicleByLease(){
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information");
        int vin = console.promptForInt("Enter VIN: ");




        ArrayList<Vehicle> vinNumber = d.getDealership().getVehicleByVinNumber(vin);
        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

//        for(Vehicle vehicle : vinNumber){
//            LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle, vehicle.getPrice(), vehicle.getPrice());
//            c.add(leaseContract);
//            displayLease();

//        }





    }


    private void processVehicleBySale(){
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle's vin Number");
        int vin = console.promptForInt("Enter VIN: ");

        ArrayList<Vehicle> vinNumber = d.getDealership().getVehicleByVinNumber(vin);

        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        int finance = console.promptForInt("Do you want finance? Yes = 1. No = 0: ");
        boolean financeSelected = finance == 1;


//        for(Vehicle vehicle : vinNumber){

//            double salesTax = vehicle.getPrice() * 0.05;
//            double recordingFee = 500;
//            double processingFee = (vehicle.getPrice() < 10000) ? 295 : 495;

//            SalesContract salesContract = new SalesContract(date, name, email, vehicle, financeSelected);
//            SalesContract salesContract = new SalesContract(date,name,email,vehicle,salesTax,recordingFee,processingFee,financeSelected);
//            c.add(salesContract);
//            displaySales();

//            d.removeVehicle(vehicle);
//            DealershipFileManager.saveDealership(d);

        }


}








//        int year = console.promptForInt("Enter Year: ");
//        String  make = console.promptForString("Enter Make: ");
//        String model = console.promptForString("Enter Model: ");
//        String type = console.promptForString("Vehicle Type: ");
//        String color = console.promptForString("Enter Color: ");
//        int odometer = console.promptForInt("Enter Distance ");
//        double price = console.promptForDouble("Enter Price: ");
//Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);

//        int year = console.promptForInt("Enter Year: ");
//        String  make = console.promptForString("Enter Make: ");
//        String model = console.promptForString("Enter Model: ");
//        String type = console.promptForString("Vehicle Type: ");
//        String color = console.promptForString("Enter Color: ");
//        int odometer = console.promptForInt("Enter Distance ");
//        double price = console.promptForDouble("Enter Price: ");