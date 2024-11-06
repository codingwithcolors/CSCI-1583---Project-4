//Amber and Dom Ketchens
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
            if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY()) {
                level++;
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
