import java.awt.*;

public class Volvo240 extends Car{

    private final static double trimFactor = 1.25;
    
    public Volvo240(int nrDoors, double enginePower, double currentSpeed, Color color, String modelname){
        super(nrDoors, enginePower, currentSpeed, color, modelname);
    }
    
    @Override
    public double speedFactor(){
        return enginePower * 0.01 * trimFactor;
    }
}
