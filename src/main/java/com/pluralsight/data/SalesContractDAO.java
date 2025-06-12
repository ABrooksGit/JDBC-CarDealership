package com.pluralsight.data;

import com.pluralsight.dealership.SalesContract;
import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
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


    public void createASale(LocalDate date, String customerName, String customerEmail,
                            int id, int vin, double salesTax, double processingFee, double recordingFee,
                            double totalPrice, boolean financed, double monthlyPayment){


        String createSale = """
                INSERT INTO sales_contract
                (date_sold,
                customer_name,
                customer_email,
                id,
                vin,
                sales_Tax,
                processingFee,
                Recording_Fee,
                total_Price,
                financed,
                monthly_Payment)
                VALUES (?,?,?,?,?,?,?,?,?,?,?)
                
              
                """;


                try(Connection c = basicDataSource.getConnection();
                    PreparedStatement ps = c.prepareStatement(createSale);


                ){

                    ps.setDate(1, Date.valueOf(date));
                    ps.setString(2, customerName);
                    ps.setString(3, customerEmail);
                    ps.setInt(4, id);
                    ps.setInt(5,vin);
                    ps.setDouble(6,salesTax);
                    ps.setDouble(7,processingFee);
                    ps.setDouble(8,recordingFee);
                    ps.setDouble(9,totalPrice);
                    ps.setBoolean(10,financed);
                    ps.setDouble(11,monthlyPayment);

                    int rows = ps.executeUpdate();
                    System.out.println("ROWS UPDATED " + rows);



                } catch (Exception e) {
                    throw new RuntimeException(e);
                }




    }
}