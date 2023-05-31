package edu.curtin.transport.factories;

import java.util.*;
import edu.curtin.transport.model.*;

/**
* A factory class that provides static methods to create instances of Passenger and Vehicle
*/

public class TransportFactory
{
    public static Passenger makePassenger(String name, double balance)
    {
        Passenger passenger = new Passenger(name, balance);
        return passenger;
    }

    public static Vehicle makeVehicle(String name, List<String> stops)
    {
        Vehicle vehicle = null;
        if(name.endsWith("Line"))
        {
            vehicle = new Train(name, stops);
        }
        else if(name.endsWith("Bus"))
        {
            vehicle = new Bus(name, stops);
        }
        return vehicle;
    }
}
