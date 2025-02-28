import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<VehicleFramework> vehicles = new ArrayList<>();
    CarWorkshop<Volvo240> VolvoWorkshop = new CarWorkshop<>(new double[]{0,470}, 2);
    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController ccp = new CarController();

        ccp.vehicles.add(new Volvo240());
        ccp.vehicles.add(new Scania());
        ccp.vehicles.add(new Saab95());
        

        // Start a new view and send a reference of self
        ccp.frame = new CarView("CarSim 1.0", ccp);

        // Start the timer
        ccp.timer.start();

    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    public class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (VehicleFramework vehicle : vehicles) {
                try {
                    vehicle.move();
                }
                catch( Exception exc) {
                    continue;
                }
                int x = (int) Math.round(vehicle.getPosition()[0]);
                int y = (int) Math.round(vehicle.getPosition()[1]);

                if (vehicle instanceof Volvo240 && !((Volvo240) vehicle).getLoaded()){
                    if (y >= 450){
                        VolvoWorkshop.load((Volvo240) vehicle);
                    }
                }

                frame.drawPanel.moveit(vehicles.indexOf(vehicle), x, y); // Pass the specific vehicle
                if (y >= 501 || y <= -1 || x <= -1 || x >= 701) {
                    vehicle.turnRight();
                    /*vehicle.startEngine();*/

                    if (y <= -1){
                        vehicle.setPositionY(0);

                    } else if (y >= 501) {
                        vehicle.setPositionY(500);

                    }else if (x <=-1) {
                        vehicle.setPositionX(0);

                    }else if (x >= 701) {
                        vehicle.setPositionX(700);

                    }
                    frame.drawPanel.moveit(vehicles.indexOf(vehicle),x, y);

                }
                else{
                    try{
                        vehicle.move();
                    }
                    catch(Exception exc1){
                        continue;
                    }
                    x = (int) Math.round(vehicle.getPosition()[0]);

                    y = (int) Math.round(vehicle.getPosition()[1]);
                    frame.drawPanel.moveit(vehicles.indexOf(vehicle),x, y);
                }
                frame.drawPanel.repaint();
            }
        }
    }


    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (VehicleFramework vehicle : vehicles
        ) {
            try{
            vehicle.gas(gas);
            }
            catch(Exception e){
                continue;
            }
        }
    }
    void brake(int amount) {
        double brake = ((double) amount) / 100;
        for (VehicleFramework vehicle : vehicles
        ) try{
            vehicle.brake(brake);
            }
            catch(Exception e){
                continue;
            }
        }
    
    void stopAllCars() {
        for (VehicleFramework vehicle : vehicles
        ) vehicle.stopEngine();
    }
    void startAllCars() {

        for (VehicleFramework vehicle : vehicles
        ) {
            vehicle.startEngine();
        }
    }
    void turboOn() {
        for (VehicleFramework vehicle : vehicles
        ) {
            if (vehicle instanceof TurboVehicle) {
                ((TurboVehicle) vehicle).setTurboOn();
            }
        }
    }
    void turboOff() {
        for (VehicleFramework vehicle : vehicles) {
            if (vehicle instanceof TurboVehicle){
                ((TurboVehicle) vehicle).setTurboOff();
            }
        }
    }
    void lowerBed() {
        for (VehicleFramework vehicle : vehicles) {
            if (vehicle instanceof Trucks){
                ((Trucks) vehicle).lowerPlatform();
            }
        }
    }
    void liftBed() {
        for (VehicleFramework vehicle : vehicles) {
            if (vehicle instanceof Trucks){
                ((Trucks) vehicle).liftPlatform();
            }
        }
    }
}
