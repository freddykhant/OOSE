package edu.curtin.transport.model;
import java.util.*;

/**
* A concrete implementation of the Vehicle interface, representing a train in the transportation system. 
* It has specific attributes and methods related to trains 
*/

public class Train implements Vehicle
{
    // Class fields for Train
    private List<Passenger> passengers;
    private List<String> stations;
    private List<TransportObserver> observers;

    private String name;
    private int currentStationIdx = 0;
    private final double ticketPrice = 3;
    private final int commuteTime = 4000;

    // Constructor with input parameters - name, and list of stops
    public Train(String name, List<String> stops)
    {
        this.name = name;
        stations = stops;
        passengers = new ArrayList<>();
        observers = new ArrayList<>();
    }

    // Accessors
    @Override
    public double getTicketPrice()          { return ticketPrice; }
    @Override
    public String getName()                 { return name; }
    @Override
    public List<Passenger> getPassengers()  { return Collections.unmodifiableList(passengers); }
    @Override
    public List<String> getStops()          { return Collections.unmodifiableList(stations); }
    @Override
    public String getCurrentStop()          { return stations.get(currentStationIdx); }

    // Board a single passenger
    @Override
    public void board(Passenger passenger)
    {
        passengers.add(passenger);
    }

    // Deboard a single passenger
    @Override
    public void deboard(Passenger passenger)
    {
        passengers.remove(passenger);
    }

    // Board a multiple (list) of passengers
    @Override
    public void boardAll(List<Passenger> passengers)
    {
        for(Passenger p : passengers)
        {
            p.swipeOn(this);
        }
        notifyObservers();
    }

    // Deboard a list of passengers
    @Override
    public void deboardAll(List<Passenger> passengers)
    {
        notifyObservers();
        for(Passenger p : passengers)
        {
            p.swipeOff(this);
        }
    }

    // Run the train
    @Override
    public void run()
    {
        try
        {
            Thread.sleep(commuteTime);  // Simulate commute time
            notifyObservers();          
            currentStationIdx++;        // Move onto next station/stop
        } 
        catch (InterruptedException e) { throw new AssertionError(e); }
    }

    // Attaching or adding observer
    @Override
    public void attach(TransportObserver observer)
    {
        observers.add(observer);
    }

    // Let observers know to make a display update
    public void notifyObservers()
    {
        for(TransportObserver observer : observers)
        {
            observer.update();
        }
    }
}