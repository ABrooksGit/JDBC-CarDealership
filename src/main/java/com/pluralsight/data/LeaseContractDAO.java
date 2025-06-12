package com.pluralsight.data;

import com.pluralsight.dealership.LeaseContract;
import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAO {

    private BasicDataSource basicDataSource;

    public LeaseContractDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }


    public List<LeaseContract> getLeaseContracts(){

        ArrayList<LeaseContract> leaseResults = new ArrayList<LeaseContract>();
        Vehicle vehicle;

        String query = """
                SELECT date_sold,
                customer_name,
                customer_email,
                v.vin,
                v.year,
                v.make,
                v.model,
                v.type,
                v.color,
                v.mileage,
                v.price,
                lc.total_vehicle_price,
                lc.monthly_payment
                FROM lease_contract lc
                Join vehicles v on lc.vin = v.vin
                """;
        try(Connection c = basicDataSource.getConnection();
            PreparedStatement s = c.prepareStatement(query);
            ResultSet queryResults = s.executeQuery();
        )
        {
            while (queryResults.next()){
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
                double totalVehiclePrice = queryResults.getDouble("total_Vehicle_Price");
                double monthlyPayment = queryResults.getDouble("monthly_payment");
                vehicle = new Vehicle(vin,year,make,model,type,color,mileage,price);
                LeaseContract leaseContract = new LeaseContract(dateSold,customerName,customerEmail,vehicle,totalVehiclePrice,monthlyPayment);
                leaseResults.add(leaseContract);


            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return leaseResults;

    }
}
