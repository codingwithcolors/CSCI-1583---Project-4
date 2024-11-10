//Amber and Dom Ketchens
//Project 4: Maze Game

public class MazeGame{
    private static boolean gameOver; // checks if game's over

        //Start game algorithm
        public static void start(){
            gameOver = false;
            level = 0;
            World.start();
            Scene.start(level);
            // starting player positions
            Player.start(1,1);
            Ghost.start(5,5);
        }

        // update game logic
        public static void update(){
            Player.update(); // update player position
            Ghost.update(); // update ghost position
            Scene.keyLogic(level); // Pass keyLogic

            // check if player reaches exit and has the key
            if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() && Scene.hasKey()) {
                System.out.println("Current level: " + level);
                level++; // increment level
                System.out.println("Player advanced to level: " + level);

                // if player reached the last last, end the game
                if (level == World.getLength()) {
                    gameOver = true;
                }
                else {
                    Scene.start(level); // start new level scene
                }
            }
            // checks if ghost catches player
            if (Player.getX() == Ghost.getX() && Player.getY() == Ghost.getY()) {
                gameOver = true; // ends the game if true
            }
        }

        // renders (draw)
        public static void render(){
            Scene.draw(); // draw scene
            Exit.draw(); // draw exit
            Player.draw(); // draw player
            Ghost.draw(); // draw ghost
            StdDraw.show(100);
        }

        // main game loop
        public static void main(String[] args){
            start(); // starts game
            while (gameOver == false) // loops while game isnt over
                {
                    update(); // updates logic
                    render(); // renders updated game
                }

        }

        public static int level; // current game level
}
