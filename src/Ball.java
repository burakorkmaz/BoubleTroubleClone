/*
 * @Burak Korkmaz - 2021400189 - 16.04.2023
 * This file contains all the things about Ball of the game
 */

public class Ball {

    private double xPos;
    private double yPos;
    private double xVelocity;
    private double yVelocity ;
    private int levelOfBall;
    private double radius;
    private final double GRAVITY = 0.00003 * 200;

    Ball(double xPos, double yPos, int levelOfBall, double xVelocity){
        this.xPos = xPos;
        this.yPos = yPos;
        this.levelOfBall = levelOfBall;
        this.xVelocity = xVelocity ;// 16.0/1000
        double maxHeight = 1.25 * 1.4 * Math.pow(1.75 , levelOfBall) - 1;
        this.yVelocity = Math.sqrt(2 * GRAVITY * maxHeight);
        this.radius = 0.175 * Math.pow(2.0, levelOfBall);
    }

    public double getxPos() {
        return xPos;
    }
    public double getyPos() {
        return yPos;
    }
    public double getxVelocity() {
        return xVelocity;
    }
    public int getLevelOfBall() {
        return levelOfBall;
    }
    public double getGRAVITY() {
        return GRAVITY;
    }
    public double getyVelocity() {
        return yVelocity;
    }
    public double getRadius(){
        return radius;
    }
    public void setXVelocity(){
        this.xVelocity = -xVelocity;
    }
    public void setYVelocity(){
        this.yVelocity -= GRAVITY;
    }
    public void setyPos(double yVelocity){
        this.yPos += yVelocity;
    }
    public void setxPos(double xVelocity){
        this.xPos += xVelocity;
    }
    public void changeDirection(int levelOfBall){
        if(xPos + xVelocity+ radius > 16.0 || xPos + xVelocity - radius < 0.0){
            this.xVelocity = -xVelocity;
        }
        if(yPos + yVelocity - radius < 0){
            this.yVelocity =  Math.sqrt(2*  GRAVITY*1.25 * 1.4 * (Math.pow(1.75 , levelOfBall)));
        }
    }
}


