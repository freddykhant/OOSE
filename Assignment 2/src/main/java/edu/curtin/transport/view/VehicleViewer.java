package edu.curtin.transport.view;

import edu.curtin.transport.model.Passenger;
import edu.curtin.transport.model.TransportObserver;
import edu.curtin.transport.model.Vehicle;

/**
 * A class responsible for observing and displaying information about the vehicle and its passengers. 
 * It implements the TransportObserver interface and attaches itself to the vehicle.
 */

public class VehicleViewer 
{
    public VehicleViewer(Vehicle vehicle)
    {  
        // Local class observer that updates and displays passenger swipe activity 
        class SwipeMachine implements TransportObserver 
        {
            @Override
            public void update()
            {
                for(Passenger passenger : vehicle.getPassengers())
                {
                    System.out.println(passenger.getName() + " has swiped" + " at " + passenger.getBoardTime());
                }
                System.out.println();
            }
        }

        // Local class observer that updates and displays when a vehicle is running
        class VehicleTracker implements TransportObserver
        {
            @Override
            public void update()
            {
                System.out.println("Now leaving: " + vehicle.getCurrentStop());
            }
        }

        // Attach instances of the observers to the vehicle
        vehicle.attach(new SwipeMachine());
        vehicle.attach(new VehicleTracker());
    }
}