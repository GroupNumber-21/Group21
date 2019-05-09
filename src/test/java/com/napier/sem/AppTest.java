/**package com.napier.sem;

import com.sun.org.apache.bcel.internal.classfile.Code;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class AppTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void getCountries()
    {
        app.printCountries(null);
    }

    @Test
    void getCities()
    {
        app.printCities(null);
    }

    @Test
    void getCountrylanguages()
    {
        app.printCountrylanguage(null);
    }

    @Test
    void displayCountriesTestNull()
    {
        app.printCountries(null);
    }

    @Test
    void printCountriesTestEmpty()
    {
        ArrayList<Country> countries  = new ArrayList<Country>();
        app.printCountries(countries);
    }

    @Test
    void printCountriesTestContainsNull()
    {
        ArrayList<Country> countries  = new ArrayList<Country>();
        countries.add(null);
        app.printCountries(countries);
    }

    @Test
    void printCountryTestNonNull()
    {
        ArrayList<Country> countries  = new ArrayList<Country>();
        Country myCountry = new Country();
        myCountry.Code = "ABW";
        myCountry.Name = "Aruba";
        myCountry.Continent = "North America";
        myCountry.Region = "Caribbean";
        app.printCountries(countries);
    }

    @Test
    void displaycityTestNull()
    {
        app.printCities(null);
    }

    @Test
    void printCitiesTestEmpty()
    {
        ArrayList<city> cities  = new ArrayList<>();
        app.printCities(cities);
    }

    @Test
    void printCitiesTestContainsNull()
    {
        ArrayList<city> cities  = new ArrayList<>();
        cities.add(null);
        app.printCities(cities);
    }

    @Test
    void printCitiesTestNonNull()
    {
        ArrayList<city> cities  = new ArrayList<city>();
        city myCity = new city();
        myCity.Name = "Kabol";
        app.printCities(cities);
    }

    @Test
    void displaycountrylanguageTestNull()
    {
        app.printCountrylanguage(null);
    }

    @Test
    void printCountrylanguageTestEmpty()
    {
        ArrayList<countrylanguage> countrylanguages  = new ArrayList<>();
        app.printCountrylanguage(countrylanguages);
    }

    @Test
    void printCountrylanguageTestContainsNull()
    {
        ArrayList<countrylanguage> countrylanguages  = new ArrayList<>();
        countrylanguages.add(null);
        app.printCountrylanguage(countrylanguages);
    }

    @Test
    void printCountrylanguageTestNonNull()
    {
        ArrayList<countrylanguage> countrylanguages  = new ArrayList<countrylanguage>();
        countrylanguage myCountrylanguage = new countrylanguage();
        myCountrylanguage.Language = "English";
        app.printCountrylanguage(countrylanguages);
    }

} */