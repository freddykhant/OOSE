package edu.curtin.transport.model;

import java.util.*;
import java.io.*;

/**
 * Provides methods for reading passenger and vehicle data from files. 
 */

import edu.curtin.transport.factories.TransportFactory;

public class FileManager
{   
    /* Reads an input file and returns a list of passenger objects */

    public List<Passenger> readFile(String filename) throws IOException, FileFormatException
    {
        List<Passenger> passengers = new ArrayList<>();
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                if(!line.trim().isEmpty())
                {
                    String[] parts = line.split(",");

                    if(parts.length != 2)
                    {
                        throw new FileFormatException(String.format("Invalid passenger record: '%s'", line));
                    }

                    Passenger passenger = TransportFactory.makePassenger(parts[0], Float.parseFloat(parts[1]));
                    passengers.add(passenger);
                }
                line = reader.readLine();
            }
        }
        return passengers;
    }

    /* Reads an input file and returns a singular vehicle object */

    public Vehicle readVehicle(String filename) throws IOException
    {
        Vehicle vehicle = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(filename)))
        {
            String line = reader.readLine();
            while(line != null)
            {
                if(!line.trim().isEmpty())
                {
                    String[] parts = line.split(",");
                    String vehicleName = parts[0];
                    List<String> stops = new ArrayList<>();
                    for(int i = 1; i < parts.length; i++)
                    {
                        stops.add(parts[i]);
                    }
                    vehicle = TransportFactory.makeVehicle(vehicleName, stops);
                }
                line = reader.readLine();
            }
        }
        return vehicle;
    }
}