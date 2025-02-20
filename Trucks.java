import java.awt.*;

public abstract class Trucks extends VehicleFramework{

    private int platform;

    public Trucks(int nrDoors, double enginePower, int weight, Color color, String modelname) {
        super(nrDoors, enginePower, weight, color, modelname);
        this.platform = 0;
    }

    public int getPlatform(){
        return this.platform;
    }

    protected void lowerPlatform(){
        if (getCurrentSpeed()==0){
            if(this.getPlatform() == 0){
                this.platform = 70;
            }
        }
    }

    protected void liftPlatform(){
        if (getCurrentSpeed()==0){
            if (this.getPlatform()==70){
                this.platform = 0;
            }
        }
    }

    @Override
    public void move(){
        if(this.getPlatform() == 0){
            super.move();
        }
        else{
            throw new IllegalArgumentException("Platform must be of angle 0 to drive");
        }
    }
}
