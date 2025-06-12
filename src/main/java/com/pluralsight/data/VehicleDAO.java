package com.pluralsight.data;

import com.pluralsight.dealership.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;

import java.util.List;

public class VehicleDAO {

    private BasicDataSource dataSource;

    public VehicleDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Vehicle> getAllVehicles(){
        return null;
    }




}

