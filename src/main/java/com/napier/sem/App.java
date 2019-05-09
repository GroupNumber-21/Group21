package com.napier.sem;

import java.sql.*;

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

        a.query12();

        a.query18();
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

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                Country country = new Country();
                /**name.ID = rset.getInt("ID");*/
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
                /**name.ID = rset.getInt("ID");*/
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

    public void query8()
    {
        System.out.println("Query8 - Get City names ordered by Population in descending order\n");
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

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                city city = new city();
                /**name.ID = rset.getInt("ID");*/
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

    public void query12()
    {
        System.out.println("Query12 - Get all cities in a district ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT Name, District, Population "
                            + "FROM city "
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                city city = new city();
                /**name.ID = rset.getInt("ID");*/
                city.Name = resultSet.getString("Name");
                city.District = resultSet.getString("District");
                city.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Name- " + resultSet.getString("Name")
                            + ", District- " + resultSet.getString("District")
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



    public void query18()
    {
        System.out.println("Query18 - Get all cities in a district ordered by population descending\n");
        try {
            // Create an SQL statement
            Statement stmt = con.createStatement();

            // Create string for SQL statement
            String strSelect =
                    "SELECT city.Name, country.Name, country.Capital, city.Population "
                            + "FROM city"
                            + "JOIN country ON country.Code = city.CountryCode"
                            + "ORDER BY Population DESC";

            // Execute SQL statement
            ResultSet resultSet = stmt.executeQuery(strSelect);

            // Return name if valid.
            // Check one is returned
            if (resultSet.next())
            {
                city city = new city();
                /**name.ID = rset.getInt("ID");*/
                city.Name = resultSet.getString("Name");
                country.Name = resultSet.getString("Name");
                country.Population = resultSet.getInt("Population");
                System.out.println("");

                while(resultSet.next()) {
                    System.out.println( ", Capital- " + resultSet.getInt("Capital")
                            + ", Population- " + resultSet.getInt("Population"));
                }
                System.out.println("Query18 -finished\n");
            }
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
            System.out.println("Failed to get city district details");

        }


    }
}