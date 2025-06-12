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

        ArrayList<SalesContract> salesResults = new ArrayList<SalesContract>();
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
                total_price,
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
            while (queryResults.next()) {
                LocalDate dateSold = queryResults.getDate("date_sold").toLocalDate();
                String customerName = queryResults.getString("customer_name");
                String customerEmail = queryResults.getString("customer_email");
                int vin = queryResults.getInt("vin");
                int year = queryResults.getInt("year");
                String make = queryResults.getString("make");
                String model = queryResults.getString("model");
                String color = queryResults.getString("color");
                String type = queryResults.getString("type");
                int mileage = queryResults.getInt("mileage");
                double price = queryResults.getDouble("price");
                int salesTax = queryResults.getInt("sales_tax");
                int recordingFee = queryResults.getInt("recording_fee");
                int processingFee = queryResults.getInt("processingFee");
                double totalPrice = queryResults.getDouble("total_price");
                boolean financed = queryResults.getBoolean("financed");
                double monthlyPayment = queryResults.getDouble("monthly_payment");
                vehicle = new Vehicle(vin,year,make,model,type,color,mileage,price);
                SalesContract salesContract = new SalesContract(dateSold,customerName,customerEmail,vehicle,salesTax,recordingFee,processingFee,totalPrice,financed,monthlyPayment);
                salesResults.add(salesContract);




            }


        } catch (Exception e) {
           e.printStackTrace();
        }

        return salesResults;
    }
}