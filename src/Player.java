/*
 * @Burak Korkmaz -  16.04.2023
 * This file contains all the things about Player of the game
 */

import java.awt.event.KeyEvent;

public class Player {
    private double x_pos;
    private final double y_pos;
    private final double x_velocity = 0.1 ;
    private final double x_length = 23.0 * 1.25 / 37.0;
    private final double y_length = 1.25 ;
    Player(){
        this.x_pos = 8.0;
        this.y_pos = 1.25/2.0;
    }

    /*
     *This function checks if the user press the rigth or left to move
     * If so, it moves
     */
    public void isPressedToMove(){
        if ((StdDraw.isKeyPressed(KeyEvent.VK_RIGHT)) && (x_pos + x_velocity < 16.0 - (x_length /2.0))){
            this.x_pos += x_velocity;
        }else if ((StdDraw.isKeyPressed(KeyEvent.VK_LEFT)) && (x_pos - x_velocity > x_length/2.0 )){
            this.x_pos -= x_velocity;
        }
    }
    public double getX_pos(){
        return x_pos;
    }
    public double getY_pos(){
        return y_pos;
    }
    public double getX_length(){
        return x_length;
    }
    public double getY_length(){
        return y_length;
    }
}
