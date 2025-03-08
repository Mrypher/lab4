import java.awt.*;

public abstract class Car extends VehicleFramework {
    private CarState currentState;

    public Car(int nrDoors, double enginePower, int weight, Color color, String modelname) {
        super(nrDoors, enginePower, weight, color, modelname);
        this.currentState = new NotLoadedState(this); // Initial state
        stopEngine();
    }

    public void setLoaded(boolean loaded) {
        if (loaded) {
            this.currentState = new LoadedState();
        } else {
            this.currentState = new NotLoadedState(this);
        }
    }

    public CarState getLoaded() {
        return this.currentState;
    }


    public void move() {
        currentState.move();
    }

    public void turnLeft() {
        currentState.turnLeft();
    }

    public void turnRight() {
        currentState.turnRight();
    }

    public void gas(double amount) {
        currentState.gas(amount);
    }

    public void brake(double amount) {
        currentState.brake(amount);
    }
        // Helper methods to call superclass methods
        protected void superMove() {
            super.move();
        }
    
        protected void superTurnLeft() {
            super.turnLeft();
        }
    
        protected void superTurnRight() {
            super.turnRight();
        }
    
        protected void superGas(double amount) {
            super.gas(amount);
        }
    
        protected void superBrake(double amount) {
            super.brake(amount);
        }
    }
