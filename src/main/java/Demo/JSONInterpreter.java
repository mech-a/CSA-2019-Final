package Demo;

import IO.DemoIOConstants;
import Invoked.ExampleAuton;
import IO.FTPGetter;
import MethodSpecifications.MethodData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;

public class JSONInterpreter implements DemoIOConstants {
    public static void main(String[] args) throws IOException {
        boolean isServerUp = true;

        Scanner userIn = new Scanner(System.in);
        String input;

        System.out.println("Please enter the name of the file.");
        input = userIn.nextLine();

        while(!Arrays.asList(LEGAL_NAMES).contains(input)) {
            System.out.println("Error! Illegal file.");
            input = userIn.nextLine();
        }

        if(isServerUp) {
            String username;
            System.out.println("Enter FTP Username");
            username = userIn.nextLine();

            String inputPassword;
            System.out.println("Enter FTP password");
            inputPassword = userIn.nextLine();

            FTPGetter wget = new FTPGetter(SERVER, PORT, username, inputPassword);
            wget.connect();
            wget.downloadFile(input, LOCAL_PATH+input);
            wget.disconnect();
        }

        String file = FileUtils.readFileToString(new File(input), Charset.defaultCharset());
        Gson gson = new GsonBuilder().setLenient().create();
        //Gson gson = new Gson();

        ExampleAuton auton = gson.fromJson(file, ExampleAuton.class);
        System.out.println();

        //Fragment of when MethodData didn't use class
        //ExampleRobot r = new ExampleRobot();


        //TODO make this be able to run LinearOpMode functions as well, not just robot. Could be achieved by implementing robot functionality into DejaVuLinearOpMode or robot.sleep point to caller.sleep
        //TODO make it not instantiate a new object every time
        for(MethodData data : auton.methods) {
            try{
                if(data.functionParams != null) {
                    Method method = data.getClassRef().getMethod(data.functionName, data.getParamTypes());
                    //Method method = ExampleRobot.class.getMethod(data.functionName, data.getParamTypes());
                    method.invoke(data.getClassRef().newInstance(), data.functionParams);
                }
                else {
                    Method method = data.getClassRef().getMethod(data.functionName);
                    //Method method = ExampleRobot.class.getMethod(data.functionName);
                    method.invoke(data.getClassRef().newInstance());
                }
            }
            catch(Exception e) {
                e.printStackTrace();
            }
        }
    }
}
