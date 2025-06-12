package com.pluralsight.data;

import com.pluralsight.dealership.SalesContract;
import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesContractDAO {

    private BasicDataSource basicDataSource;

    public SalesContractDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }


    public List<SalesContract> getSalesContracts() {

        ArrayList<SalesContract> results = new ArrayList<SalesContract>();
        Vehicle vehicle;


        String query = """
                select date_sold,
                customer_name,
                customer_email,
                id,
                v.vin,
                v.year,
                v.make,
                v.model,
                v.color,
                v.type,
                v.mileage,
                v.price,
                sales_tax,
                recording_fee,
                processingFee,
                total_price
                financed,
                monthly_payment
                From
                sales_contract sc
                join vehicles v on sc.vin = v.vin
                """;


        try (Connection c = basicDataSource.getConnection();
             PreparedStatement s = c.prepareStatement(query);
             ResultSet queryResults = s.executeQuery();
        ) {
            if (queryResults.next()) {
                String date = queryResults.getString(1);
                String customerName = queryResults.getString(2);
                String customerEmail = queryResults.getString(3);
                int vin = queryResults.getInt(4);
                int year = queryResults.getInt(5);
                String make = queryResults.getString(6);
                String model = queryResults.getString(7);
                String color = queryResults.getString(8);
                String type = queryResults.getString(9);
                int odometer = queryResults.getInt(10);
                double price = queryResults.getDouble(11);
                int salesTax = queryResults.getInt(12);
                int recordingFee = queryResults.getInt(13);
                int processingFee = queryResults.getInt(14);
                double totalPrice = queryResults.getDouble(15);
                boolean financed = queryResults.getBoolean(16);
                double monthlyPayment = queryResults.getDouble(17);
                vehicle = new Vehicle(vin,year,make,model,type,color,odometer,price);
                SalesContract salesContract = new SalesContract(date,customerName,customerEmail,vehicle,salesTax,recordingFee,processingFee,totalPrice,financed,monthlyPayment);
                results.add(salesContract);




            }


        } catch (SQLException e) {
            System.out.println("ERRRORING");
        }

        return results;
    }
}