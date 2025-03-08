public interface CarState {
    void move();
    void turnLeft();
    void turnRight();
    void gas(double amount);
    void brake(double amount);
}