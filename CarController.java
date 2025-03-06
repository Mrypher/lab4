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
    public Timer timer;
    public int delay = 20;

    Model model;

    CarView frame;

    public CarController() {
        this.model = new Model();
    }

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

            }
            else{
                model.getVehicles().removeLast();

            }
            frame.drawPanel.removeLastCarPosition();
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
