import java.util.ArrayList;
import java.util.List;

public class LoadCar {
    private List<Car> cargo = new ArrayList<Car>();
    private int maxcars;
    protected double[] position;

    public LoadCar(int maxcars, double[] position){
        this.maxcars = maxcars;
        this.position = position;
    }

    protected void load(Car car){
        if (cargo.size() < this.maxcars){
            if (car.weight < 2000){
                if(car.getLoaded() instanceof NotLoadedState){
                    cargo.add(car);
                    car.position = this.position;
                    car.stopEngine();
                    car.setLoaded(true);
                }
                else{
                    throw new IllegalArgumentException("Car cannot be loaded if loaded");
                }
            }
            else{
                throw new IllegalArgumentException("Car weight exceeds 2 tons");
            }
        }


    }

    protected void unload(Car car){
        cargo.remove(car);
        car.setLoaded(false);
    }

    public List<Car> getCargo(){
        return cargo;
    }
}
