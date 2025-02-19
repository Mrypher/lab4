import java.awt.*;

public class Saab95 extends Car implements TurboVehicle{
    private boolean turboOn=false;

    public Saab95(){
        super(4, 100, 1400, Color.red, "Saab95");
        this.position = new double[]{0,200};
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

