package edu.curtin.transport.states;

import edu.curtin.transport.model.Passenger;
import edu.curtin.transport.model.Vehicle;

/**
 * These are concrete implementations of the PassengerState interface. 
 * They handle swiping on and off as when status is Active
 */

public class ActiveState implements PassengerState
{   
    // Method for swiping on a vehicle in Active account state

    @Override
    public void swipeOn(Passenger passenger, Vehicle vehicle)
    {
        double balance = passenger.getBalance(); // Passenger balance
        double price = vehicle.getTicketPrice(); // Vehicle ticket price
        
        // If balance is sufficient
        if(balance >= price)
        {
            balance -= price;               // Deduct balance
            passenger.setBalance(balance);  // Set new balance
            passenger.board(vehicle);       // Passenger board the vehicle - link vehicle to passenger
            vehicle.board(passenger);       // Vehicle boards passenger    - add passenger to vehicle's list of passengers
        }
        // If balance is insufficient
        else
        {
            passenger.setAccountStatus(new InactiveState()); // Set passenger's account state to Inactive
            passenger.swipeOn(vehicle);                      // Call swipeOn method again to perform relevent actions to its new state
        }
    }

    // Swipe off method
    @Override
    public void swipeOff(Passenger passenger, Vehicle vehicle)
    {
        passenger.deboard(vehicle);
        vehicle.deboard(passenger);
    }
}