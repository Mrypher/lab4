import java.awt.*;
import java.util.ArrayList;

public class Model {
    ArrayList<VehicleFramework> vehicles = new ArrayList<>();
    CarWorkshop<Volvo240> VolvoWorkshop = new CarWorkshop<>(new double[]{0,470}, 2);

    public ArrayList<VehicleFramework> getVehicles(){return vehicles;}

    public CarWorkshop<Volvo240> getVolvoWorkshop(){return  VolvoWorkshop;}
}



