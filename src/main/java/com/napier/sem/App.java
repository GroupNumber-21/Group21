package com.napier.sem;

import java.sql.*;
import java.util.Scanner;

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

        a.query3();
        a.query4();
        a.query8();
        a.query9();
        a.query10();
        a.query11();
        a.query12();
        a.query27();
        a.query5();
        a.query6();

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

    public void query3()
    {
        System.out.println("Query3 - Get Country Population in descending order\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);


            if (resultSet.next())
            {
                Country country = new Country();
                country.Name = resultSet.getString("Name");
                country.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query3 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");

        }


    }


    public void query4()
    {
        System.out.println("Query4 - Get Country Population in descending order by region\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population, Region "
                            + "FROM country "
                            + "ORDER BY Region, Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                country.Name = resultSet.getString("Name");
                country.Population = resultSet.getInt("Population");
                country.Region = resultSet.getString("Region");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population")
                            + ", Region- " + resultSet.getString("Region"));
                }
                System.out.println("Query4 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");

        }


    }

    public void query5()
    {
        System.out.println("Query5 - Get the top (user input) populated countries in the world \n");
        Scanner scanner = new Scanner(System.in);
        System.out.print( "Enter the amount of countries you would like to see - " );
        int input = scanner.nextInt();
        input += 1;

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String limit = "LIMIT " + input + " ";

            String strSelect =
                    "SELECT Name, Population "
                            + "FROM country "
                            + "ORDER BY Population DESC "
                            + limit;

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            if (resultSet.next()) {
                Country country = new Country();
                country.Name = resultSet.getString("Name");
                country.Population = resultSet.getInt("Population");
                System.out.println("");

                while (resultSet.next()) {
                    System.out.println(", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query5 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
        }

    }


    public void query8()
    {
        System.out.println("Query8 - Get all cities ordered by population in descending order\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            if (resultSet.next())
            {
                city city = new city();
                city.Name = resultSet.getString("Name");
                city.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
               }
                System.out.println("Query8 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get City details");
        }

    }

    public void query9()
    {
        System.out.println("Query9 - Get all the cities in a continent ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Continent, city.Name, city.Population "
                            + "FROM country "
                            + "JOIN city ON city.CountryCode = country.Code "
                            + "ORDER BY country.Continent, city.Population DESC ";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                city city = new city();
                city.Name = resultSet.getString("Name");
                city.Population = resultSet.getInt("Population");
                country.Continent = resultSet.getString("Continent");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Continent- " + resultSet.getString("Continent")
                            + ", City Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query9 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }

    }

    public void query10()
    {
        System.out.println("Query10 - Get all cities in a region ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Region, city.Name, city.Population "
                            + "FROM country "
                            + "JOIN city ON city.CountryCode = country.Code "
                            + "ORDER BY country.Region, city.Population DESC ";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                city city = new city();
                city.Name = resultSet.getString("Name");
                city.Population = resultSet.getInt("Population");
                country.Region = resultSet.getString("Region");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Region- " + resultSet.getString("Region")
                            + ", City Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query10 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }

    }

    public void query11()
    {
        System.out.println("Query11 - Get all cities in a country ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT country.Name, city.Name, city.Population "
                            + "FROM country "
                            + "JOIN city ON city.CountryCode = country.Code "
                            + "ORDER BY country.Name, city.Population DESC ";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                city city = new city();
                city.Name = resultSet.getString("Name");
                city.Population = resultSet.getInt("Population");
                country.Name = resultSet.getString("Name");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Country Name- " + resultSet.getString("country.Name")
                            + ", City Name- " + resultSet.getString("city.Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query11 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get details");
        }

    }


    public void query12()
    {
        System.out.println("Query12 - Get all cities in a district ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, city.District, city.Population "
                            + "FROM city "
                            + "ORDER BY city.District, city.Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                city city = new city();
                city.Name = resultSet.getString("Name");
                city.District = resultSet.getString("District");
                city.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", District- " + resultSet.getString("District")
                            + ", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query12 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city district details");
        }

    }


    public void query27()
    {
        System.out.println("Query27 - Get all cities in a country ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();
            // Create string for SQL statement
            String strSelect =
                    "SELECT SUM(Population) AS Population "
                            + "FROM country ";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                country.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println(", Population- " + resultSet.getLong("Population"));
                }
                System.out.println("Query27 -finished\n");
            }
            resultSet.getLong("Population");
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
        }

    }

    public void query6()
    {
        System.out.println("Query6 - Get the top (user input) populated countries in a continent\n");
        Scanner scanner = new Scanner(System.in);
        System.out.print( "Enter the amount of countries you would like to see - " );
        int input = scanner.nextInt();
        input += 1;
        System.out.println( "Enter the country you would like to see - " );
        String input_country = scanner.nextLine();

        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String limit = "LIMIT " + input + " " + input_country + " ";

            String strSelect =
                    "SELECT Name, Population, Continent "
                            + "FROM country "
                            + "WHERE Continent = "+ input_country +" "
                            + limit;

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            if (resultSet.next()) {
                Country country = new Country();
                country.Name = resultSet.getString("Name");
                country.Population = resultSet.getInt("Population");
                country.Continent = resultSet.getString("Continent");
                System.out.println("");

                while (resultSet.next()) {
                    System.out.println(", Continent- " + resultSet.getString( "Continent")
                            + ", Name- " + resultSet.getString("Name")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query6 -finished\n");
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get Country details");
        }

    }


}