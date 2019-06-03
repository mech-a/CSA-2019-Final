package MethodSpecifications;

import java.util.Arrays;

public class MethodData {
    public String className;
    public String functionName;
    public Object[] functionParams;

    public MethodData(String cName, String fName, Object... args) {
        functionName = fName;
        className = cName;
        functionParams = args;
    }

    //Only used in AuxiliaryTests
    public String toString() {
        return "name of class: " + className + " || name of function: " + functionName + " || params: " + Arrays.toString(functionParams);
    }

    public Class<?>[] getParamTypes() {
        Class<?>[] paramTypes = new Class[functionParams.length];
        for(int i = 0; i<functionParams.length; i++) {
            paramTypes[i] = functionParams[i].getClass();
        }
        return paramTypes;
    }

    public Class<?> getClassRef() {
        //output: package name testing class ReflectionTesting.MethodData
        //System.out.println("package name testing " + this.getClass());

        //TODO try to auto-find a package (perhaps by putting MethodData in the same Package and then retrieving?)
        try{
            Class<?> c = Class.forName(//"Demo"
                            "Invoked"
                            + "." + className);
            return c;
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    //Deprecated from testing
    public Object getClassObj() {
        return getClassRef().getClass();
    }
}
