import javax.swing.*;

public class  Main {
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
}