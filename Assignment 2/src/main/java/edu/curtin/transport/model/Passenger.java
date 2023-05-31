package edu.curtin.transport.model;

import java.time.LocalTime;
import edu.curtin.transport.states.*;

/**
 * Represents a single passenger in the transportation system. 
 */

public class Passenger
{
    // Passenger class fields
    private PassengerState status = new ActiveState();
    private String name;
    private double balance;
    private Vehicle onboardVehicle;
    private LocalTime boardTime;
    private LocalTime deboardTime;

    // Basic constructor with parameters
    public Passenger(String name, double balance)
    {
        this.name = name;
        this.balance = balance;
    }

    /* Getters and Setters */

    public String getName()             { return name; }
    public double getBalance()           { return balance; }
    public LocalTime getBoardTime()     { return boardTime; }
    public LocalTime getDeboardTime()   { return deboardTime; } 
    public boolean getOnboard()         { return onboardVehicle != null; }
    
    public void setAccountStatus(PassengerState status) { this.status = status; }
    public void setBalance(double amount) { balance = amount; }

    /* State dependent methods */

    // Swipe on to a vehicle
    public void swipeOn(Vehicle vehicle)
    {
        status.swipeOn(this, vehicle);
    }
    
    // Swipe off a vehicle
    public void swipeOff(Vehicle vehicle)
    {
        status.swipeOff(this, vehicle);
    }

    // Board a particular vehicle
    public void board(Vehicle vehicle)
    {
        onboardVehicle = vehicle;       // Link a vehicle to the passenger, the passenger is now onboard this vehicle
        boardTime = LocalTime.now();    // Retrieve the time boarded
    }

    // Deboard vehicle
    public void deboard(Vehicle vehicle)
    {
        onboardVehicle = null;          // Unlink the vehicle from passenger, the passenger has deboarded this vehicle
        deboardTime = LocalTime.now();  // Retrieve the time deboarded
    }
}