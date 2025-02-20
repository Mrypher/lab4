import java.awt.*;

public abstract class Trucks extends VehicleFramework implements BedVehicle{

    private int platform;

    public Trucks(int nrDoors, double enginePower, int weight, Color color, String modelname) {
        super(nrDoors, enginePower, weight, color, modelname);
        this.platform = 0;
    }

    public int getPlatform(){
        return this.platform;
    }

    @Override
    public void lowerPlatform(){
        if (getCurrentSpeed()==0){
            this.platform = 70;
        }
        else{
            throw new IllegalArgumentException("You cannot LOWER the truckbed when moving");
        }
    }

    @Override
    public void liftPlatform(){
        if (getCurrentSpeed()==0){
            this.platform = 0;
        }
        else{
            throw new IllegalArgumentException("You cannot LIFT the truckbed when moving");
        }
    }

    @Override
    public void move(){
        if(this.getPlatform() == 0){
            super.move();
        }
        else if (getCurrentSpeed() > 0){
            throw new IllegalArgumentException("Platform must be of angle 0 to drive");
        }
    }
}
