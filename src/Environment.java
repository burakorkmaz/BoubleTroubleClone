/*
 * @Burak Korkmaz - 2021400189 - 16.04.2023
 * Environment class contains the run function and condition functions
 */

import java.awt.event.KeyEvent;
import java.awt.Color;
import java.util.ArrayList;
public class Environment {
    // I keep some data as static because they must be global
    public static Player player;
    public static Color col;
    public static double xVelocityOfAll =  2 * 16.0/1000;
    public static boolean collisionCondition = true; // ball-player collusion
    public static boolean timeCondition = true; // time left condition
    public static  Bar bar = new Bar();
    Environment(Player player){ //The Player parameter is needed to rerun the game.(You can use usage of this in the Burak_Korkmaz.java)
        this.player =  new Player();
    }

    public static void run(){

        ArrayList<Ball> BallList1 = new ArrayList<>(); // this arraylist keeps level1 balls
        ArrayList<Ball> BallList2 = new ArrayList<>(); // this arraylist level0 balls
        int arrowCounter = 0; // count the arrow number
        Arrow arrow = null;

        int ballCounter = 3; // total ball count

        double initialTime = System.currentTimeMillis();

       // boolean runCondition = true;

        /*
         *These three balls are initial balls.
         */
        Ball level2Ball = new Ball(16.0/4,1.5,2, xVelocityOfAll);
        BallList1.add(new Ball(16.0/3, 1.5, 1, -xVelocityOfAll));
        BallList2.add(new Ball(16.0/4, 1.5, 0, xVelocityOfAll));


        StdDraw.setCanvasSize(800,500);
        StdDraw.setXscale(0.0, 16.0);
        StdDraw.setYscale(-1.0, 9.0);
        StdDraw.enableDoubleBuffering();

        float greenValue = 255; //the green value in RGB

        //If the time is enough, there exist ball and there are no collusion between player and balls, continue to the loop
        while(collisionCondition && ballCounter > 0 && timeCondition){
            double currentTime = System.currentTimeMillis();

            bar.setLengthOfBar((40000.0- (currentTime-initialTime)) * 16 /40000.0);

            if (bar.getLengthOfBar() <= 0){
                timeCondition = false; //if remaining time is smaller than zero the program get outs of the loop
                break;
            }

            StdDraw.clear();
            StdDraw.picture(8.0, 5.0, "background.png", 16, 10);
            StdDraw.picture(bar.getxPos(), bar.getyPos(), "bar.png", 16, 1);

            //If "SPACE" is pressed, the arrow will be created
            if (StdDraw.isKeyPressed(KeyEvent.VK_SPACE) && arrowCounter == 0){
                arrow = new Arrow(player);
                arrowCounter++;
            }
            //If arrow exist and arrow doesn't exceed the canvas, go up.
            if (arrow != null && !arrow.isMaxHeight()) {
                StdDraw.picture(arrow.getXPosOfArrow(), arrow.getYPosOfArrow(),"arrow.png", 0.15, arrow.getyHeight());
                arrow.setYHeight();
                arrow.setyPosOfArrow();
            }else{
                arrow = null;
                arrowCounter = 0;
            }

            //If level2 ball exists, draw it and move
            if (level2Ball != null){
                StdDraw.picture(level2Ball.getxPos(), level2Ball.getyPos(), "ball.png", level2Ball.getRadius()*2, level2Ball.getRadius()*2);
                level2Ball.setyPos(level2Ball.getyVelocity());
                level2Ball.setxPos(level2Ball.getxVelocity());
                level2Ball.setYVelocity();
                level2Ball.changeDirection(2);
                //check if ball and player are collised
                if (checkPlayerCollision(level2Ball, player)){
                    collisionCondition = false;
                }
                //if arrow hits the ball, two balls will be created and arrow will disappear
                if (arrowCounter > 0 && checkCollusion(level2Ball,arrow)){
                    BallList1.add(new Ball(level2Ball.getxPos(), level2Ball.getyPos(), 1, xVelocityOfAll));
                    BallList1.add(new Ball(level2Ball.getxPos(), level2Ball.getyPos(), 1, -xVelocityOfAll));
                    level2Ball = null;
                    arrow = null;
                    arrowCounter = 0;
                    ballCounter +=1;
                }
            }
            // draw the level1 balls and move them
            for (Ball top: BallList1){
                if (top != null) {
                    StdDraw.picture(top.getxPos(), top.getyPos(), "ball.png", top.getRadius() * 2, top.getRadius() * 2);
                    top.setyPos(top.getyVelocity());
                    top.setxPos(top.getxVelocity());
                    top.setYVelocity();
                    top.changeDirection(top.getLevelOfBall());

                    if (checkPlayerCollision(top, player)){
                        collisionCondition= false;
                    }

                    if (arrowCounter > 0 && checkCollusion(top, arrow)) {
                        BallList2.add(new Ball(top.getxPos(), top.getyPos(), 0, xVelocityOfAll));
                        BallList2.add(new Ball(top.getxPos(), top.getyPos(), 0, -xVelocityOfAll));
                        int index = BallList1.indexOf(top);
                        BallList1.set(index, null);
                        arrow = null;
                        arrowCounter = 0;
                        ballCounter += 1;
                    }
                }
            }
            // the same as level2 and level1 process
            for (Ball top: BallList2){
                if (top != null) {
                    StdDraw.picture(top.getxPos(), top.getyPos(), "ball.png", top.getRadius() * 2, top.getRadius() * 2);
                    top.setyPos(top.getyVelocity());
                    top.setxPos(top.getxVelocity());
                    top.setYVelocity();
                    top.changeDirection(top.getLevelOfBall());
                    if (checkPlayerCollision(top, player)){
                        collisionCondition = false;
                    }
                    if (arrowCounter > 0 && checkCollusion(top, arrow)) {
                        int index = BallList2.indexOf(top);
                        BallList2.set(index, null);
                        arrow = null;
                        arrowCounter = 0;
                        ballCounter -= 1;
                    }
                }
            }

            //the color of the time bar
            col = new Color(1F, greenValue/255, 0F);
            StdDraw.setPenColor(col);
            // drawing the time bar
            StdDraw.filledRectangle(bar.getLengthOfBar()/2.0, -0.5, bar.getLengthOfBar()/2.0,0.25);
            float d = (float)(255 * 15.0 *2.2/40000);
            greenValue -= d;



            StdDraw.picture(player.getX_pos(), player.getY_pos(), "player_back.png", player.getX_length(), player.getY_length());
            player.isPressedToMove();
            StdDraw.show();
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.pause(15);
        }

        //I am drawing again because in the example video, all balls disappear after the game
        StdDraw.picture(8.0, 5.0, "background.png", 16, 10);
        StdDraw.picture(bar.getxPos(), bar.getyPos(), "bar.png", 16, 1);
        StdDraw.picture(player.getX_pos(), player.getY_pos(), "player_back.png", player.getX_length(), player.getY_length());
        StdDraw.setPenColor(col);
        StdDraw.filledRectangle(bar.getLengthOfBar()/2.0, -0.5, Math.abs(bar.getLengthOfBar())/2.0,0.25);

    }

    /*
     *@param1 = Ball object
     *@param2 = Arrow object
     * This function checks whether the ball and arrow are collised
     * If so, it returns true
     * else, false
     */
    public static boolean checkCollusion(Ball top, Arrow arr){
        if (Math.abs(top.getxPos() - arr.getXPosOfArrow()) < top.getRadius()){
            if (Math.abs(top.getyPos() - (arr.getYPosOfArrow() + arr.getyHeight()/2.0)) < top.getRadius()) {
                return true;
            }else if ( arr.getYPosOfArrow() + arr.getyHeight()/2.0 > top.getyPos() ){
                return true;
            }
        }
        return false;
    }


    /*
     *@param1 = Ball object
     *@param2 = Player object
     * This function checks whether the player and the ball are collised
     * If so, it returns true
     * else, false
     */
    public static boolean checkPlayerCollision(Ball top, Player play){
        if ( (Math.abs(top.getxPos() - play.getX_pos()) < (top.getRadius() + play.getX_length()/2.0)) && (Math.abs(top.getyPos() - play.getY_pos()) < top.getRadius() + play.getY_length()/2.0)){
            return true;
        }
        return false;
    }
    public boolean getCollusionCondition(){
        return collisionCondition;
    }
    public boolean getTimeCondition(){
        return timeCondition;
    }
    public void setCollisionCondition(){
        this.collisionCondition = true;
    }
    public void setTimeCondition(){
        this.timeCondition = true;
    }
}