import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/* Each step the TimerListener moves all the cars in the list and tells the
 * view to update its images. Change this method to your needs.
 * */
public class TimerListener extends CarController implements ActionListener {
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
