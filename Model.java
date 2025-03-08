import java.awt.*;
import java.util.ArrayList;

public class Model {
    CarView frame;

    ArrayList<VehicleFramework> vehicles = new ArrayList<>();
    CarWorkshop<Volvo240> VolvoWorkshop = new CarWorkshop<>(new double[]{0,470}, 2);
    public  ArrayList<Point> carPositions = new ArrayList<>();
    private ArrayList<VehicleObserver> observers = new ArrayList<>();

    public ArrayList<VehicleFramework> getVehicles(){
        return vehicles;
    }

    public CarWorkshop<Volvo240> getVolvoWorkshop(){
        return  VolvoWorkshop;
    }
    
    public ArrayList<Point> getCarPosition(){
        return carPositions;
    }

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

    public void removeVehicle() {
        try {
            if (checkIfLoaded((Car) getVehicles().get(vehicles.size() - 1))) {
                getVolvoWorkshop().unload((Volvo240) getVehicles().get(vehicles.size() - 1));
                getVehicles().remove(vehicles.size() - 1);
            } 
            else{
                getVehicles().remove(vehicles.size() - 1);
            }
            frame.drawPanel.removeLastCarPosition();
            notifyObservers();
        } 
        catch (Exception exc) {
        }
    }

    public void moveVehicle(int index, int x, int y) {
        if (index >= 0 && index < carPositions.size()) {
            carPositions.set(index, new Point(x, y));
            notifyObservers();
        }
    }

    boolean checkIfLoaded(Car car){
        if (car.getLoaded() instanceof LoadedState){
            return true;
        }else return false;
    }

}