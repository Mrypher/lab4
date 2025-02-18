import java.awt.*;

public class Saab95 extends Car{
    private boolean turboOn=false;

    public Saab95(int nrDoors, double enginePower, double currentSpeed, Color color, String modelname){
        super(nrDoors, enginePower, currentSpeed, color, modelname);

    }

    public void setTurboOn(){
	    turboOn = true;
    }

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

