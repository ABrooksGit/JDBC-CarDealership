package com.pluralsight.userinterface;


import com.pluralsight.data.*;
import com.pluralsight.dealership.Contract;
import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.LeaseContract;
import com.pluralsight.dealership.SalesContract;
import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.time.LocalDate;
import java.util.ArrayList;


public class UserInterface {


    //    private ArrayList<Contract> c = new ArrayList<>();
    private final Console console = new Console();
    private DealershipDAO d;
    private SalesContractDAO s;
    private LeaseContractDAO l;


    public UserInterface(DealershipDAO d, SalesContractDAO s, LeaseContractDAO l) {
        this.d = d;
        this.s = s;
        this.l = l;
    }

    private void init() {


    }

    public void displayMenu() {
        init();

        display();
    }


    private void displayVehicles(ArrayList<Vehicle> vehicles) {

        for (Vehicle v : vehicles)
            System.out.println(v.toString());

    }

    private void displayLease(ArrayList<Contract> contracts) {

        System.out.println(LeaseContract.getFormattedHeader());
        for (Contract contract : contracts) {
            if (contract instanceof LeaseContract) {
                System.out.println(contract);
            }
        }


    }

    private void displaySales(ArrayList<Contract> contracts) {
        System.out.println(SalesContract.getFormattedHeader());
        for (Contract contract : contracts) {
            if (contract instanceof SalesContract) {
                System.out.println(contract);
            }
        }


    }


    private void display() {


        while (true) {
            String[] homeScreenPrompt = {
                    "Find vehicles within a price range",
                    "Find vehicles by make / model",
                    "Find vehicles by year range",
                    "Find vehicles by color",
                    "Find vehicles by mileage range",
                    "Find vehicles by type (car, truck, SUV, van)",
                    "List ALL vehicles",
                    "Add a vehicle",
                    "Remove a vehicle",
                    "Create A Sale",
                    "Create A Lease",
                    "Return to login screen",
                    "Quit"
            };

            int input = console.promptForOption(homeScreenPrompt);
            switch (input) {
                case 1:
                    processGetByPriceRequest();
                    break;
                case 2:
                    processGetByMakeModelRequest();
                    break;
                case 3:
                    processGetByYearRequest();
                    break;
                case 4:
                    processGetByColorRequest();
                    break;
                case 5:
                    processGetByMileageRequest();
                    break;
                case 6:
                    processGetByVehicleTypeRequest();
                    break;
                case 7:
                    processGetByAllVehiclesRequest();
                    break;
                case 8:
                    processAddVehicleRequest();
                    break;
                case 9:
                    startProcessToRemoveVehicle();
                    break;
                case 10:
                    processVehicleBySale();
                    break;
                case 11:
                    processVehicleByLease();
                    break;
                case 12:
                    return;
                case 13:
                    System.out.println("Quitting...");
                    System.exit(0);
                    break;


            }

        }
    }


    private void processGetByPriceRequest() {

        double min = console.promptForDouble("Input the minimum price: ");
        double max = console.promptForDouble("Input the maximum price: ");

        ArrayList<Vehicle> priceResult = d.getDealership().getVehicleByPrice(min, max);


        if (priceResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles found within price range" + ColorCodes.RESET);
        } else {
            System.out.println("These are the vehicles found within" + " " + min + " and " + max);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(priceResult);

        }
    }


    private void processGetByMakeModelRequest() {

        String make = console.promptForString("Input Make you want to search for: ");
        String model = console.promptForString("Input Model you want to search for: ");

        ArrayList<Vehicle> makeModelResult = d.getDealership().getVehicleByMakeModel(make, model);

        if (makeModelResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles found within " + make + " " + model + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles found within " + make + " " + model);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(makeModelResult);
        }

    }


    private void processGetByYearRequest() {
        int minYear = console.promptForInt("Enter in the minimum year: ");
        int maxYear = console.promptForInt("Enter in the maximum year: ");
        ArrayList<Vehicle> yearResult = d.getDealership().getVehicleByYear(minYear, maxYear);


        if (yearResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicle found for " + minYear + " " + maxYear + ColorCodes.RESET);

        } else {
            System.out.println("These Vehicles are inside of this year: " + yearResult);
            System.out.println(Vehicle.getFormattedHeader());


            displayVehicles(yearResult);
        }


    }


    private void processGetByColorRequest() {

        String color = console.promptForString("Enter in a color: ");
        ArrayList<Vehicle> colorResult = d.getDealership().getVehicleByColor(color);


        if (colorResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles of this color: " + color + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles of this color: " + color);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(colorResult);
        }

    }


    private void processGetByMileageRequest() {

        int startingMileage = console.promptForInt("Enter in the starting Mileage you want to search: ");
        int endingMileage = console.promptForInt("Enter in the ending Mileage you want to search: ");

        ArrayList<Vehicle> mileageResult = d.getDealership().getVehicleByMileage(startingMileage, endingMileage);

        if (mileageResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicles have a mileage between these two numbers " + startingMileage + " " + endingMileage + ColorCodes.RESET);
        } else {
            System.out.println("These Vehicles have a mileage between " + startingMileage + " " + endingMileage);
            System.out.println(Vehicle.getFormattedHeader());
            displayVehicles(mileageResult);
        }

    }


    private void processGetByVehicleTypeRequest() {
        String type = console.promptForString("Enter Vehicle Type ");

        ArrayList<Vehicle> typeResult = d.getDealership().getVehicleByType(type);

        if (typeResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "There are no Vehicles with the type of " + type + ColorCodes.RESET);
        } else {
            System.out.println("These are the Vehicles with the type of " + type);
            System.out.println(Vehicle.getFormattedHeader());

            displayVehicles(typeResult);
        }

    }


    private void processGetByAllVehiclesRequest() {
        System.out.println("Here are all the vehicles");
        System.out.println(Vehicle.getFormattedHeader());

        displayVehicles(d.getDealership().getAllVehicles());

    }


    private void processAddVehicleRequest() {
        System.out.println("Enter vehicle information");

        int vin = console.promptForInt("Enter VIN: ");
        int year = console.promptForInt("Enter Year: ");
        String make = console.promptForString("Enter Make: ");
        String model = console.promptForString("Enter Model: ");
        String type = console.promptForString("Vehicle Type: ");
        String color = console.promptForString("Enter Color: ");
        int odometer = console.promptForInt("Enter Distance ");
        double price = console.promptForDouble("Enter Price: ");
        boolean sold = false;

//        Vehicle vehicle = new Vehicle(vin, year, make, model, type, color, odometer, price);
        d.addVehicleToDealership(vin, year, make, model, type, color, odometer, price, sold);
        d.addVehicleToInventory(vin);


//        DealershipFileManager.saveDealership(d);

    }


    private void startProcessToRemoveVehicle() {
        int choice;
        do {


            choice = console.promptForInt("Do you want to check the Vehicle list? (1 or 2 ):   ");
            switch (choice) {
                case 1:
                    displayVehicles(d.getDealership().getAllVehicles());
                    break;
                case 2:
                    processRemoveVehicleRequest();
                    break;
            }


        } while (choice != 2);
    }


    private void processRemoveVehicleRequest() {

        System.out.println("Which Vehicle do you want to Remove?: ");
        int vin = console.promptForInt("Enter VIN: ");

        ArrayList<Vehicle> choiceResult = d.getDealership().getVehicleByVinNumber(vin);


        if (choiceResult.isEmpty()) {
            System.out.println(ColorCodes.RED + "No Vehicle...." + ColorCodes.RESET);

        } else {
            d.removeVehicleFromDealership(vin);
            System.out.println(ColorCodes.PURPLE + choiceResult + " Was Removed...." + ColorCodes.RESET);
        }
    }



    private void processVehicleByLease() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information");
        int vin = console.promptForInt("Enter VIN: ");


        ArrayList<Vehicle> vinNumber = d.getDealership().getVehicleByVinNumber(vin);
        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        for (Vehicle vehicle : vinNumber) {
            int id = console.promptForInt("Enter Int ID: ");
            LeaseContract leaseContract = new LeaseContract(date, name, email, vehicle, vehicle.getPrice(), vehicle.getPrice());
            l.getLeaseContracts().add(leaseContract);

            l.createLease(LocalDate.parse(date), name,email,id,vin, leaseContract.getTotalPrice(), leaseContract.getMonthlyPayment());
        }
    }


    private void processVehicleBySale() {
        String date = console.promptForString("Enter the Date: ");
        String name = console.promptForString("Enter your name: ");
        String email = console.promptForString("Enter your email: ");
        System.out.println("Enter vehicle information: ");


        int vin = console.promptForInt("Enter VIN: ");
        ArrayList<Vehicle> vinNumber = d.getDealership().getVehicleByVinNumber(vin);




        int finance = console.promptForInt("Do you want finance? Yes = 1. No = 0: ");
        boolean financeSelected = (finance == 1);


        if(vinNumber.isEmpty()){
            System.out.println( ColorCodes.RED + "No Vehicles with this Vin Number..." + ColorCodes.RESET);
        }

        for (Vehicle vehicle : vinNumber) {



            int id = console.promptForInt("Enter an ID: ");
            SalesContract salesContract = new SalesContract(date, name, email, vehicle, financeSelected);
            s.getSalesContracts().add(salesContract);
            s.createASale(LocalDate.parse(date), name,email, id, vin, salesContract.getSalesTax(), salesContract.getProcessingFee(), salesContract.getRecordingFee(), salesContract.getTotalPrice(), financeSelected, salesContract.getMonthlyPayment());

        }
    }

}