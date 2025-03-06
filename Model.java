import java.awt.*;
import java.util.ArrayList;

public class Model {
    CarView frame;

    ArrayList<VehicleFramework> vehicles = new ArrayList<>();
    CarWorkshop<Volvo240> VolvoWorkshop = new CarWorkshop<>(new double[]{0,470}, 2);
    public  ArrayList<Point> carPositions = new ArrayList<>();

    public ArrayList<VehicleFramework> getVehicles(){return vehicles;}

    public CarWorkshop<Volvo240> getVolvoWorkshop(){return  VolvoWorkshop;}
    private ArrayList<VehicleObserver> observers = new ArrayList<>();
    public ArrayList<Point> getCarPosition(){return carPositions;}

    public void addObserver(VehicleObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (VehicleObserver observer : observers) {
            observer.update(vehicles);
        }
    }

    public void addVehicle(VehicleFramework vehicle) {
        vehicles.add(vehicle);
        carPositions.add(new Point(0, 0));
        notifyObservers();
    }

    void removeVehicle() {
        try {
            if(checkIfLoaded((Car) getVehicles().getLast())){
                getVolvoWorkshop().unload((Volvo240) getVehicles().getLast());
                getVehicles().removeLast();

            }
            else{
                getVehicles().removeLast();

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



