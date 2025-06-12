package com.pluralsight.data;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.Vehicle;

import java.io.*;
import java.util.regex.Pattern;

public class DealershipFileManagerNonStaticExample {


    private String fileName;

    public DealershipFileManagerNonStaticExample(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName){
        this.fileName = fileName;
    }


    public Dealership getDealership() {

        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(this.fileName))) {
            Dealership dealership = null;
            String dealerShipCsv;
            while((dealerShipCsv = bufferedReader.readLine()) != null){
                createDealershipFromEncodedString(dealerShipCsv);
            }
            String inputString;
            while ((inputString = bufferedReader.readLine()) != null) {
                if (inputString.trim().isEmpty()) {
                    continue;
                }
                dealership.addVehicle(createVehicleEncodedString(inputString));
            }
            return dealership;
        } catch(IOException e){
            throw new RuntimeException(e);
        }
    }

    private Dealership createDealershipFromEncodedString(String encodedString){

        String[] dealershipCsvParts = encodedString.split(Pattern.quote("|"));
        String dealerName = dealershipCsvParts[0];
        String dealerAddress = dealershipCsvParts[1];
        String dealerPhone = dealershipCsvParts[2];

        return new Dealership(dealerName, dealerAddress,dealerPhone);




    }

    private Vehicle createVehicleEncodedString(String encodedString){
        String[] parts = encodedString.split(Pattern.quote("|"));

        int vin = Integer.parseInt(parts[0]);
        int year = Integer.parseInt(parts[1]);
        String make = parts[2];
        String model = parts[3];
        String vehicleType = parts[4];
        String color = parts[5];
        int odometer = Integer.parseInt(parts[6]);
        double price = Double.parseDouble(parts[7]);


        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
    }



    public void saveDealership(Dealership dealership) {

        try {
            FileWriter dealerShipLog = new FileWriter(fileName);
            BufferedWriter bufferedWriter = new BufferedWriter(dealerShipLog);

            bufferedWriter.write(dealership.toStringLog());

            for(Vehicle v : dealership.getAllVehicles()){
                bufferedWriter.write("\n" + v.toStringLog());
            }

            bufferedWriter.close();

        } catch (IOException e) {

            throw new RuntimeException(e);
        }


    }

}
