package org.example;

public class MyTestUtilities {
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";

    private final String testTitle;
    private int counter = 0;

    public MyTestUtilities(String testTitle) {
        this.testTitle = testTitle;
        System.out.println("\nUnit Test start " + testTitle);
    }

    public void printSuccessMessage(
            String message,
            String expected
    ) {
        counter++;
        System.out.println(ANSI_GREEN +"\tUnit Tes " + counter + " (Success): " + message + " | Expected: " + expected+ ANSI_RESET);
    }

    public void printSuccessMessage(
            String message
    ) {
        counter++;
        System.out.println(ANSI_GREEN +"\tUnit Tes " + counter + " (Success): " + message + ANSI_RESET);
    }

    public void endTest() {
        System.out.println("Unit Test completed " + testTitle);
    }


}
