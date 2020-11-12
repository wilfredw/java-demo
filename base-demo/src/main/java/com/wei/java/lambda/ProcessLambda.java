package com.wei.java.lambda;

public class ProcessLambda {
    public void printProcess(ProcessFunction processFunction) {
        System.out.println(processFunction.process());
    }


    public static void main(String[] args) {
        String message = "hi, ";
        ProcessLambda processLambda = new ProcessLambda();
        processLambda.printProcess(() -> message + "you!");
    }
}
