import java.awt.*;

public abstract class TurboVehicle extends Car implements TurboLogic{
    private boolean turboOn;

    public TurboVehicle(int nrDoors, double enginePower, double weight, Color color, String modelName) {
        super(nrDoors, enginePower, (int) weight, color, modelName);
        this.turboOn = false;
    }

    @Override
    public void setTurboOn(){
        turboOn = true;
    }

    @Override
    public boolean getLoaded() {
        return super.getLoaded();
    }

    @Override
    public void setTurboOff(){
        turboOn = false;
    }

    @Override
    public double speedFactor(){
        double turbo = 1;
        if(turboOn) turbo = 1.3;
        return enginePower * 0.01 * turbo;
    }

}