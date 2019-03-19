package com.napier.sem;

import java.sql.*;
import java.util.ArrayList;

public class App {

    static App a;
    public static void main(String[] args)
    {
        // Create new Application
        a = new App();

        System.out.println("hello");
        // Connect to database
        if (args.length <1) {
            a.connect("localhost:33060");
        }
        else {
            a.connect(args[0]);
        }

        ArrayList<Country> countries  = a.getCountries();
        a.printCountries(countries);

        ArrayList<city> cities  = a.getCities();
        a.printCities(cities);

        ArrayList<countrylanguage> countrylanguages = a.getCountrylanguages();
        a.printCountrylanguage(countrylanguages);

        // Disconnect from database
        a.disconnect();
    }

    /**
     * Connection to MySQL database.
     */
    private Connection con = null;

    /**
     * Connect to the MySQL database.
     */
    public void connect (String location)
    {
        try {
            // Load Database driver
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Could not load SQL driver");
            System.exit(-1);
        }

        int retries = 10;
        for (int i = 0; i < retries; ++i) {
            System.out.println("Connecting to database...");
            try {

                // Connect to database
                con = DriverManager.getConnection("jdbc:mysql://" + location +"/world?allowPublicKeyRetrieval=true&useSSL=false", "root", "example");
                System.out.println("Successfully connected");
                break;
            } catch (SQLException sqle) {
                System.out.println("Failed to connect to database attempt " + Integer.toString(i));
                System.out.println(sqle.getMessage());
            }
            try {
                // Wait a bit for db to start
                Thread.sleep(30000);
            } catch (InterruptedException ie) {
                System.out.println("Thread interrupted? Should not happen.");
            }
        }

    }

    /**
     * Disconnect from the MySQL database.
     */
    public void disconnect ()
    {
        if (con != null) {
            try {
                // Close connection
                con.close();
            } catch (Exception e) {
                System.out.println("Error closing connection to database");
            }
        }
    }

    public ArrayList<Country> getCountries()
    {
        ArrayList<Country> result = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return name if valid.
            // Check one is returned
            while(rset.next())
            {
                Country country = new Country();
                /**name.ID = rset.getInt("ID");*/
                country.Name = rset.getString("Name");
                country.Population = rset.getInt("Population");
                result.add(country);

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
            return null;
        }

        return result;
    }


    public void displaycountry(Country name)
    {
        if (name != null)

        {
            System.out.println(
                    name.Name + " "
                            + name.Population);
        }
    }

    public void printCountries(ArrayList<Country> countries)
    {
        if (countries == null)
            return;

        System.out.println(countries.size());
        for(Country c : countries)
        {
            if (c == null)
                continue;

            a.displaycountry(c);
        }
    }



    public ArrayList<city> getCities()
    {
        ArrayList<city> result = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return name if valid.
            // Check one is returned
            while(rset.next())
            {
                city city = new city();
                /**name.ID = rset.getInt("ID");*/
                city.Name = rset.getString("Name");
                city.Population = rset.getInt("Population");
                result.add(city);

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city details");
            return null;
        }

        return result;
    }


    public void displaycity(city name)
    {
        if (name != null)

        {
            System.out.println(
                    name.Name + " "
                            + name.Population);
        }
    }

    public void printCities(ArrayList<city> cities)
    {
        if (cities == null)
            return;

        System.out.println(cities.size());
        for(city c : cities)
        {
            if (c == null)
                continue;

            a.displaycity(c);
        }
    }


    public ArrayList<countrylanguage> getCountrylanguages()
    {
        ArrayList<countrylanguage> result = new ArrayList<>();
        try
        {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT Language, Percentage "
                            + "FROM countrylanguage "
                            + "ORDER BY Percentage DESC";
            // Execute SQL statement
            ResultSet rset = stmt.executeQuery(strSelect);
            // Return name if valid.
            // Check one is returned
            while(rset.next())
            {
                countrylanguage countrylanguage = new countrylanguage();
                /**name.ID = rset.getInt("ID");*/
                countrylanguage.Language = rset.getString("Language");
                countrylanguage.Percentage = rset.getFloat("Percentage");
                result.add(countrylanguage);

            }

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get countrylanguage details");
            return null;
        }

        return result;
    }


    public void displaycountrylanguage(countrylanguage name)
    {
        if (name != null)

        {
            System.out.println(
                    name.Language + " "
                            + name.Percentage);
        }
    }

    public void printCountrylanguage(ArrayList<countrylanguage> countrylanguages)
    {
        if (countrylanguages == null)
            return;

        System.out.println(countrylanguages.size());
        for(countrylanguage c : countrylanguages)
        {
            if (c == null)
                continue;

            a.displaycountrylanguage(c);
        }
    }


}