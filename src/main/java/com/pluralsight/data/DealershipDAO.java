package com.pluralsight.data;

import com.pluralsight.dealership.Dealership;
import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class DealershipDAO {

    private final int dealershipId = 1;
    private int vin;
    private int year;
    private String make;
    private String model;
    private String type;
    private String color;
    private int mileage;
    private double price;
    private boolean sold;

    private BasicDataSource dataSource;


    public DealershipDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Dealership getDealership() {

        //ArrayList<Dealership> result = new ArrayList<Dealership>();
        Dealership resultingDealership = null;

        String query = """
                SELECT
                name, address, phone
                From Dealerships d
                where d.dealership_id = 1;
                """;

        String query2 = """
                select\s
                v.vin, -- 1
                v.year, -- 2
                v.make, -- 3
                v.model, -- 4
                v.color, -- 5
                v.type,
                v.mileage,
                v.price
                from vehicles v
                join inventory i on v.vin = i.vin
                where i.dealership_id = 1;
                """;


        try (Connection c = dataSource.getConnection();
             PreparedStatement s = c.prepareStatement(query);
             ResultSet queryResult = s.executeQuery()

        ) {

            if (queryResult.next()) {
                String dealershipName = queryResult.getString(1);
                String dealershipAddress = queryResult.getString(2);
                String dealershipPhone = queryResult.getString(3);
                resultingDealership = new Dealership(dealershipName, dealershipAddress, dealershipPhone);
                //assign value to resultingDealership

            }

        } catch (SQLException e) {
            System.out.println("ERROR ERROR ABORT ABORT!!");
        }

        try (Connection c2 = dataSource.getConnection();
             PreparedStatement s2 = c2.prepareStatement(query2);
             ResultSet vehicleSet = s2.executeQuery()) {
            while (vehicleSet.next()) {
                int vin = vehicleSet.getInt(1);
                int year = vehicleSet.getInt(2);
                String make = vehicleSet.getString(3);
                String model = vehicleSet.getString(4);
                String color = vehicleSet.getString(5);
                String vehicleType = vehicleSet.getString(6);
                int odometer = vehicleSet.getInt(7);
                double price = vehicleSet.getDouble(8);


                if (resultingDealership != null) {
                    resultingDealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }


            }


        } catch (SQLException e) {
            System.out.println("ERROR");
        }

        return resultingDealership;

    }


    public void addVehicleToDealership(int vin, int year, String make, String model, String type, String color, int mileage,
                                       double price,
                                       boolean sold) {



        String addVehicle = """
                Insert into vehicles
                (vin, year, make, model,type,color,mileage,price, sold)
                Values(?,?,?,?,?,?,?,?,?);
                """;

        try (Connection c = dataSource.getConnection();
             PreparedStatement ps = c.prepareStatement(addVehicle);
        )
        {
             ps.setInt(1, vin);
             ps.setInt(2,year);
             ps.setString(3,make);
             ps.setString(4, model);
             ps.setString(5,type);
             ps.setString(6,color);
             ps.setInt(7, mileage);
             ps.setDouble(8,price);
             ps.setBoolean(9, sold);

             int rows = ps.executeUpdate();

            System.out.println("ROWS UPDATED " + rows);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }



    }
}








