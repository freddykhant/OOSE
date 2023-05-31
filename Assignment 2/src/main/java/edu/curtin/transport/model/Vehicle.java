package edu.curtin.transport.model;
import java.util.*;

/* *
* Defines the common methods and attributes for Transport Vehicles 
*/

public interface Vehicle
{
    String getName();
    double getTicketPrice();
    List<Passenger> getPassengers();
    String getCurrentStop();
    List<String> getStops();
    void board(Passenger passenger);
    void deboard(Passenger passenger); 
    void boardAll(List<Passenger> passengers);
    void deboardAll(List<Passenger> passengers);
    void run();
    void attach(TransportObserver observer);
}