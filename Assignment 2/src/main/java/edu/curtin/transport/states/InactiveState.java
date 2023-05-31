package edu.curtin.transport.states;

import edu.curtin.transport.model.Passenger;
import edu.curtin.transport.model.Vehicle;

/**
 * These are concrete implementations of the PassengerState interface. 
 * They handle swiping on and off as when status is Inactive
 */

public class InactiveState implements PassengerState
{

    // Method for swiping on a vehicle in Inactive account state

    @Override
    public void swipeOn(Passenger passenger, Vehicle vehicle)
    {
        double balance = passenger.getBalance();
        double price = vehicle.getTicketPrice();

        // If balance is sufficient
        if(balance >= price)
        {
            passenger.setAccountStatus(new ActiveState());  // Change account state to Active
            passenger.swipeOn(vehicle);                     // Call swipeOn method again to perform relevent actions to its new state
        }
        // If balance is (still) insufficient
        else
        { 
            // Print error message, passenger does not board vehicle
            System.out.println(passenger.getName() + " has Inactive Status: Unable to swipe on");
        }
    }

    // Swipe off method
    @Override
    public void swipeOff(Passenger passenger, Vehicle vehicle)
    {
        // Do nothing (as the passenger does not board vehicle in the first place)
    }
}