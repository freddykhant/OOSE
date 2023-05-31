package edu.curtin.transport.view;

import java.util.*;

import edu.curtin.transport.model.*;

/**
 * Transportation System Simulation. 
 * It takes a list of passengers and a vehicle as input and runs the simulation by boarding passengers, running the vehicle, and deboarding passengers.
 */

public class Simulation
{
    private List<Passenger> passengers;
    private Vehicle vehicle;

    // Constructor takes a list of passenger and one singular vehicle
    public Simulation(List<Passenger> passengers, Vehicle vehicle)
    {
        this.passengers = passengers;
        this.vehicle = vehicle;
        new VehicleViewer(vehicle);
    }

    // Runs the simulation
    public void runSimulation()
    {
        vehicle.boardAll(passengers);                                       // Board all passengers in list
        for(int i = 1; i < vehicle.getStops().size(); i++)                  // For all stops in the route
        {
            vehicle.run();                                                  // Run commute
            System.out.println("Arrived at: " + vehicle.getStops().get(i)); // Display destination stop
        }

        vehicle.deboardAll(passengers);                                     // Deboard all passengers at the end of route
        System.out.println("");
    }
}
