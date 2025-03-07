import javax.swing.*;

public class  Main {
    
    public static void main(String[] args) {
        // Instance of this class
        CarController ccp = new CarController();
        
        ccp.model.addVehicle(VehicleFactory.createVehicle("Scania"));
        ccp.model.addVehicle(VehicleFactory.createVehicle("Volvo"));
        ccp.model.addVehicle(VehicleFactory.createVehicle("Saab"));

        // Start a new view and send a reference of self
        CarView frame = new CarView("CarSim 1.0", ccp);

        // Start the timer
        TimerListener timerListener = new TimerListener(ccp.model, frame);
        ccp.timer = new Timer(ccp.delay, timerListener);
        ccp.timer.start();
    }
}