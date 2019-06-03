package Invoked;

/**
 *
 */


public class ExampleRobot {

    //Constructor must be public so that it can be in a different package from the Interpreter
    public ExampleRobot() {
        //Blank Constructor
        System.out.println("Blank constructor ran!");
    }

    public void translate(String dir, Double counts, Double speed) {
        System.out.println("Translate function! Direction: " + dir + " counts " + counts + " speed " + speed);
    }
    public void rotate(String dir, Double angle, Double speed) {
        System.out.println("Rotate function! Direction: " + dir + " angle " + angle + " speed " + speed);
    }

    public void getGoldPosition() {
        System.out.println("GetGoldPosition ran with NO PARAMS!");
    }
}
