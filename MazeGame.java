//Amber and Dom Ketchens
//Project 4: MazeGame

public class MazeGame{
    private static boolean gameOver;

        //Start game algorithm
        public static void start(){
            gameOver = false;
            level = 0;
            World.start();
            Scene.start(level);
            Player.start(1,1);
            Ghost.start(5,5);
        }

        public static void update(){
            Player.update();
            Ghost.update();
            Scene.keyLogic(level); // Pass keyLogic
            if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() && Scene.hasKey()) {
                System.out.println("Current level: " + level);
                level++;
                System.out.println("Player advanced to level: " + level);
                if (level == World.getLength()) {
                    gameOver = true;
                }
                else {
                    Scene.start(level);
                }
            }
            // checks if ghost catches player
            if (Player.getX() == Ghost.getX() && Player.getY() == Ghost.getY()) {
                gameOver = true; // ends the game if true
            }
        }

        public static void render(){
            Scene.draw(); // draw scene
            Exit.draw(); // draw exit
            Player.draw(); // draw player
            Ghost.draw(); // draw ghost
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
