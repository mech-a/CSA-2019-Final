package Invoked;

public abstract class Robot {
    abstract void translate(String dir, Double counts, Double speed);
    abstract void rotate(String dir, Double angle, Double speed);
    abstract void getGoldPosition();
}
