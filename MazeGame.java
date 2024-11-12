//Amber Williams and Dom Ketchens
//Project 4: Maze Game

public class MazeGame{
    private static boolean gameOver;
    private static Music backgroundMusic; //Play background music
    private static Music levelExit; // Play sfx for exiting a level

        //Start game algorithm
        public static void start(){
            gameOver = false;
            level = 0;
            World.start();
            Scene.start(level);

        //Initialize and play background music
        backgroundMusic = new Music("Assets/background-music.wav");
        backgroundMusic.playLoop();

        //Play sfx with level advancement
        levelExit = new Music("Assets/level-exit-sfx.wav");
        }

        public static void update(){
            Player.update();
            Scene.pickupLogic(level); //Pass logic for key and gem
            if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() && Scene.hasKey() && Scene.hasGem()) {
            	System.out.println("Current level: " + level); //Debug message to ensure player is on the right level
                level++;
                System.out.println("Player advanced to level: " + level); //Debug message for level type
                levelExit.play();
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

        //Stop the background music when the game ends
        if (backgroundMusic != null)
            {
                backgroundMusic.stop();
            }

        }

        public static int level;

    //Allow for more game over methods to be referenced
    public static void setGameOver(boolean status)
    	{
    		gameOver = status;
    	}
}