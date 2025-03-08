public class LoadedState implements CarState {
    @Override
    public void move() {
        throw new IllegalArgumentException("Car cannot move while loaded");
    }

    @Override
    public void turnLeft() {
        throw new IllegalArgumentException("Car cannot turn left while loaded");
    }

    @Override
    public void turnRight() {
        throw new IllegalArgumentException("Car cannot turn right while loaded");
    }

    @Override
    public void gas(double amount) {
        throw new IllegalArgumentException("Car cannot gas while loaded");
    }

    @Override
    public void brake(double amount) {
        throw new IllegalArgumentException("Car cannot brake while loaded");
    }
}