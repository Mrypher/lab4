import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerListener implements ActionListener {
    CarController controller;
    Model model;
    CarView frame;


    public TimerListener(Model model, CarView frame) {
        this.model = model; // Initialize model
        this.frame = frame; // Initialize frame
    }
    public void actionPerformed(ActionEvent e) {
        for (VehicleFramework vehicle : model.getVehicles() ) {
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
                    model.getVolvoWorkshop().load((Volvo240) vehicle);
                }
            }

            frame.drawPanel.moveit(model.getVehicles() .indexOf(vehicle), x, y); // Pass the specific vehicle
            if (y >= 501 || y <= -1 || x <= -1 || x >= 901) {
                vehicle.turnRight();
                /*vehicle.startEngine();*/

                if (y <= -1){
                    vehicle.setPositionY(0);

                } else if (y >= 501) {
                    vehicle.setPositionY(500);

                }else if (x <=-1) {
                    vehicle.setPositionX(0);

                }else if (x >= 901) {
                    vehicle.setPositionX(900);

                }
                frame.drawPanel.moveit(model.getVehicles() .indexOf(vehicle),x, y);

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
                frame.drawPanel.moveit(model.getVehicles() .indexOf(vehicle),x, y);
            }
            frame.drawPanel.repaint();
        }
    }
}