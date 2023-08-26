/*
 * @Burak Korkmaz - 16.04.2023
 * This file contains all the things about Arrow of the game
 */

public class Arrow {
    private double xPosOfArrow ;
    private static double yPosOfArrow;
    private static double yHeight ;
    private final double yVelocity = 0.2;

    Arrow(Player player){
        this.xPosOfArrow = player.getX_pos();
        this.yPosOfArrow = player.getY_pos();
        this.yHeight = player.getY_length();
    }

    public double getyHeight(){
        return yHeight;
    }
    public void setYHeight(){
        yHeight += yVelocity;
    }
    public double getXPosOfArrow(){
        return xPosOfArrow;
    }
    public double getYPosOfArrow(){
        return yPosOfArrow;
    }


    /*
     *This function checks whether the arrow exceeds the canvas
     * If so , it returns true
     * else, false.
     */
    public boolean isMaxHeight(){
        if (yHeight > 9){
            return true;
        }
        return false;
    }
    public void setyPosOfArrow(){
        yPosOfArrow = yHeight/2.0;
    }
}
