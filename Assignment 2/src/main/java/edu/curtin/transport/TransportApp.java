package edu.curtin.transport;

import java.io.*;
import edu.curtin.transport.model.*;
import edu.curtin.transport.view.*;
import java.util.logging.Logger;
import java.util.logging.Level;

/** 
 * Entry point of the application. 
 * It reads passenger and vehicle data from files using the FileManager and initializes the Simulation with the obtained data.
 */

public class TransportApp
{
    // File paths

    private static final String PASSENGER_FILE = "passengers.txt";
    private static final String VEHICLE_FILE = "mandurah.txt";
    private static final Logger LOGGER = Logger.getLogger(TransportApp.class.getName());

    // Main program
    public static void main(String[] args)
    {
        FileManager fm = new FileManager();
        try 
        {
            Simulation transperth = new Simulation(fm.readFile(PASSENGER_FILE), fm.readVehicle(VEHICLE_FILE));
            transperth.runSimulation();
        } 
        catch (IOException e) 
        {
            if (LOGGER.isLoggable(Level.WARNING)) 
            { 
                LOGGER.log(Level.WARNING, "WARNING: error reading the passengers file - " + e.getMessage());
            }
        } 
        catch (FileFormatException e) 
        {
            if (LOGGER.isLoggable(Level.WARNING)) 
            {  
                LOGGER.log(Level.WARNING, "Warning: ignoring passengers file due to invalid format - " + e.getMessage());
            }
        }
        
    }
}
