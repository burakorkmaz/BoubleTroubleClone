/*
 * @Burak Korkmaz -  16.04.2023
 * This file contains all the things about Bar of the game
 */


public class Bar {
    private double xPos;
    private double yPos;
    private static double lengthOfBar; // this variable is the length of the time bar

    Bar() {
        this.xPos = 8.0;
        this.yPos = -0.5;
    }

    public double getxPos() {
        return xPos;
    }

    public double getyPos() {
        return yPos;
    }

    public double getLengthOfBar() {
        return lengthOfBar;
    }
    public void setLengthOfBar(double lengthOfBar){
        this.lengthOfBar = lengthOfBar;
    }
}