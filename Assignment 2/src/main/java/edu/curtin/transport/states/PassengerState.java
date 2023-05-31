package edu.curtin.transport.states;

import edu.curtin.transport.model.Passenger;
import edu.curtin.transport.model.Vehicle;

/**
 * Defines a passenger's account state, with state dependent methods
 */

public interface PassengerState 
{
    void swipeOn(Passenger passenger, Vehicle vehicle);
    void swipeOff(Passenger passenger, Vehicle vehicle);
}