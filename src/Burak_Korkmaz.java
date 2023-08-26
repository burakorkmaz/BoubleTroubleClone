/*
 * @Burak Korkmaz - 2021400189 - 16.04.2023
 * The program runs in this file by calling the environment class.
 */
import java.awt.Font;
import java.awt.event.KeyEvent;


/*
 *First of all, it calls the environment object to run once game
 * And then, it gets into while loop to continue the game
 * If "Y" pressed, the environment object is rebuilt and calls the environment class to run the game again
 * If "N" pressed, it exits the program.
 */
public class Burak_Korkmaz {
    public static void main(String args[]){

        Environment env = new Environment(new Player());
        env.run();

        while(true) {
            //I draw the endscreen in this file
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.picture(8.0, 10 / 2.18, "game_screen.png", 16 / 3.8, 10 / 4.0);
            if (!env.getCollusionCondition() || !env.getTimeCondition()) {
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(8.0, 5.0, "GAME OVER!");
            } else {
                StdDraw.setFont(new Font("Helvetica", Font.BOLD, 30));
                StdDraw.text(8.0, 5.0, "YOU WON!");
            }
            StdDraw.setFont(new Font("Helvetica", Font.BOLD, 15));
            StdDraw.text(8.0, 10 / 2.3, "To Replay Click \"Y\"");
            StdDraw.text(8.0, 10 / 2.6, "To Quit Click \"N\"");
            //If "Y" is pressed, rerun the game
            if (StdDraw.isKeyPressed(KeyEvent.VK_Y)) {
                env= new Environment(new Player());
                env.setCollisionCondition();
                env.setTimeCondition();
                env.run();
            } else if (StdDraw.isKeyPressed(KeyEvent.VK_N)) {
                System.exit(0);
            }
            StdDraw.show();
        }
    }
}
