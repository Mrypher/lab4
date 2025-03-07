import javax.swing.*;

/*
 * This class represents the Controller part in the MVC pattern.
 * It's responsibilities is to listen to the View and responds in a appropriate manner by
 * modifying the model state and the updating the view.
 */

public class CarController {
    public Timer timer;
    public int delay = 1;

    Model model;

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
            model.addVehicle(VehicleFactory.createVehicle("Volvo"));
        }
    }
    void removeCar() {
        model.removeVehicle();
    }


}