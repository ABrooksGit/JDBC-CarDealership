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

    private BasicDataSource dataSource;


    public DealershipDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }


    public Dealership getDealership(){

        //ArrayList<Dealership> result = new ArrayList<Dealership>();
    Dealership resultingDealership = null;

        String query = """
                SELECT
                name, address, phone
                From Dealerships d;
                where d.dealership_id = 1;
                
                """;

        String query2 = """
                select v.vin, v.year, v.make, v.model,v.color,v.type,v.mileage,v.sold
                from vehicles v
                join inventory i on v.vin = i.vin
                where i.dealership_id = 1;
                """;



        try(Connection c = dataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query);
            ResultSet queryResult = s.executeQuery()

        ){
            while (queryResult.next()){

                String dealershipName = queryResult.getString(2);
                String dealershipAddress = queryResult.getString(3);
                String dealershipPhone = queryResult.getString(4);
                resultingDealership = new Dealership(dealershipName,dealershipAddress,dealershipPhone);
//assign value to resultingDealership
            }



        } catch (SQLException e){
            System.out.println("ERROR ERROR ABORT ABORT!!");
        }

        try(Connection c2 = dataSource.getConnection();
            PreparedStatement s2 = c2.prepareStatement(query2);
            ResultSet vehicleSet = s2.executeQuery();

        ){
            while (vehicleSet.next()){
                int vin = vehicleSet.getInt(1);
                int year = vehicleSet.getInt(2);
                String make = vehicleSet.getString(3);
                String model = vehicleSet.getString(4);
                String vehicleType = vehicleSet.getString(5);
                String color = vehicleSet.getString(6);
                int odometer = vehicleSet.getInt(7);
                double price = vehicleSet.getDouble(8);
                if(resultingDealership != null) {
                    resultingDealership.addVehicle(new Vehicle(vin, year, make, model, vehicleType, color, odometer, price));
                }
            }

        } catch (SQLException e){
            System.out.println("ERROR");
        }

        return  resultingDealership;

    }
}
