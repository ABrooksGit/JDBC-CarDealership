package com.pluralsight;


import com.pluralsight.data.DealershipDAO;
import com.pluralsight.data.LeaseContractDAO;
import com.pluralsight.data.SalesContractDAO;
import com.pluralsight.userinterface.AdminUserInterface;
import com.pluralsight.userinterface.Console;
import com.pluralsight.userinterface.UserInterface;
import org.apache.commons.dbcp2.BasicDataSource;

public class Main {

    public static void main(String[] args) {
        BasicDataSource basicDataSource = getBasicDataSourceFromArgs(args);
        DealershipDAO dealershipDAO = new DealershipDAO(basicDataSource);
        SalesContractDAO salesContractDAO = new SalesContractDAO(basicDataSource);
        LeaseContractDAO leaseContractDAO = new LeaseContractDAO(basicDataSource);

        if(args.length != 3) {
            System.out.println("You must run this with three arguments. <username>, <password>, <url>");
            System.exit(-1);
        }


        Console console = new Console();
        String password;

        System.out.println("Welcome to the Car Dealership");
        System.out.println("If you are an Admin, please sign in with your password");
        System.out.println("If you are a User, Just ignore the password and press Enter");
        System.out.println("If you want to quit please Enter the 0 key");
        do {
            System.out.println("If you are a User, Just ignore the password and press Enter");


            password = console.promptForString("Enter your password: ", true);

            if(password.equalsIgnoreCase("Admin")){
                System.out.println("Correct Password... Moving to Admin UI");

                AdminUserInterface UI = new AdminUserInterface(dealershipDAO,salesContractDAO,leaseContractDAO);

                UI.displayMenu();



            } else if(password.isEmpty()){
                System.out.println("Moving to User Interface");

                UserInterface UI = new UserInterface(dealershipDAO,salesContractDAO, leaseContractDAO);

                UI.displayMenu();
                break;
            } else if (password.equalsIgnoreCase("0")) {
                System.out.println("Quitting application...");
                break;

            } else {
                System.out.println("Wrong Password...");

            }

        } while(!password.equalsIgnoreCase("AdminPassword"));


    }



    private static BasicDataSource getBasicDataSourceFromArgs(String[] args){
        String username = args[0];
        String password = args[1];
        String url = args[2];
        BasicDataSource source = new BasicDataSource();
        source.setUsername(username);
        source.setPassword(password);
        source.setUrl(url);
        return source;

    }


    private static void uiManagement(){


}





}