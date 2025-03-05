import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Time;
import java.util.ArrayList;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:
    // The delay (ms) corresponds to 20 updates a sec (hz)
    Model model;
    private Timer timer;
    private final int delay = 50;

    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed



    public CarController() {
        this.model = new Model();
    }


    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController ccp = new CarController();


        ccp.model.getVehicles() .add(new Scania());
        ccp.model.getVehicles() .add(new Volvo240());
        ccp.model.getVehicles() .add(new Saab95());



        // Start a new view and send a reference of self
        ccp.frame = new CarView("CarSim 1.0", ccp);

        // Start the timer

        TimerListener timerListener = new TimerListener(ccp.model, ccp.frame);
        ccp.timer = new Timer(ccp.delay, timerListener);
        ccp.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */


    // Calls the gas method for each car once
    void gas(int amount) {
        double gas = ((double) amount) / 100;
        for (VehicleFramework vehicle : model.getVehicles() 
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
        for (VehicleFramework vehicle : model.getVehicles() 
        ) try{
            vehicle.brake(brake);
            }
            catch(Exception e){
                continue;
            }
        }
    
    void stopAllCars() {
        for (VehicleFramework vehicle : model.getVehicles() 
        ) vehicle.stopEngine();
    }
    void startAllCars() {

        for (VehicleFramework vehicle : model.getVehicles() 
        ) {
            vehicle.startEngine();
        }
    }
    void turboOn() {
        for (VehicleFramework vehicle : model.getVehicles() 
        ) {
            if (vehicle instanceof TurboVehicle) {
                ((TurboVehicle) vehicle).setTurboOn();
            }
        }
    }
    void turboOff() {
        for (VehicleFramework vehicle : model.getVehicles() ) {
            if (vehicle instanceof TurboVehicle){
                ((TurboVehicle) vehicle).setTurboOff();
            }
        }
    }
    void lowerBed() {
        for (VehicleFramework vehicle : model.getVehicles() ) {
            if (vehicle instanceof Trucks){
                ((Trucks) vehicle).lowerPlatform();
            }
        }
    }
    void liftBed() {
        for (VehicleFramework vehicle : model.getVehicles() ) {
            if (vehicle instanceof Trucks){
                ((Trucks) vehicle).liftPlatform();
            }
        }
    }
    void addCar() {
        if (model.getVehicles().size() <= 10) {
            model.getVehicles().add(new Volvo240());
        }
    }
    void removeCar() {
        try {
            if(checkIfLoaded((Car) model.getVehicles().getLast())){
                model.getVolvoWorkshop().unload((Volvo240) model.getVehicles().getLast());
                model.getVehicles().removeLast();
                frame.drawPanel.removeLastCarPosition();

            }
            else{
                model.getVehicles().removeLast();
                frame.drawPanel.removeLastCarPosition();
            }

        }
        catch(Exception exc){

        }
    }
    boolean checkIfLoaded(Car car){
        if (car.getLoaded()){
            return true;
        }else return false;
    }
}
