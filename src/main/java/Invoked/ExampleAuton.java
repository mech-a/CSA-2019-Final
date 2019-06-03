package Invoked;

import MethodSpecifications.MethodData;

public class ExampleAuton {
    //MethodData m = new MethodData("translate", 800, 0.2);
    public MethodData[] methods = new MethodData[]{
            new MethodData("ExampleRobot","translate", "BACK", 800, 0.3),
            new MethodData("ExampleRobot","getGoldPosition", null),
            //new MethodData("don'tshowup", "testme out!")
    };
}
