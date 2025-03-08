public class NotLoadedState implements CarState {
    private Car car;

    public NotLoadedState(Car car) {
        this.car = car;
    }

    @Override
    public void move() {
        car.superMove();
    }

    @Override
    public void turnLeft() {
        car.superTurnLeft();
    }

    @Override
    public void turnRight() {
        car.superTurnRight();
    }

    @Override
    public void gas(double amount) {
        car.superGas(amount);
    }

    @Override
    public void brake(double amount) {
        car.superBrake(amount);
    }
}
