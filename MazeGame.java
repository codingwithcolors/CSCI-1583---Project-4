//Amber Williams and Dom Ketchens
//Project 4: Maze Game

public class MazeGame{
    private static boolean gameOver;

        //Start game algorithm
        public static void start(){
            gameOver = false;
            level = 0;
            World.start();
            Scene.start(level);
        }

        public static void update(){
            Player.update();
            Scene.keyLogic(level); //Pass keyLogic
            if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() && Scene.hasKey()) {
            	System.out.println("Current level: " + level); //Debug message to ensure player is on the right level
                level++;
                System.out.println("Player advanced to level: " + level); //Debug message for level type
                if (level == World.getLength()) {
                    gameOver = true;
                }
                else {
                    Scene.start(level);
                }
            }
        }

        public static void render(){
            Scene.draw();
            Exit.draw();
            Player.draw();
            StdDraw.show(100);
        }

        public static void main(String[] args){
            start();
            while (gameOver == false)
                {
                    update();
                    render();
                }

        }

        public static int level;
}
