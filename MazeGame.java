//Amber Williams and Dom Ketchens
//Project 4: Maze Game
import java.util.ArrayList;

public class MazeGame {
    private static boolean gameOver; // checks if game's over
    private static Music backgroundMusic; // Play background music
    private static Music levelExit; // Play sfx for exiting a level
    private static ArrayList<Bullet> bullets = new ArrayList<>();
    private static ArrayList<Ghost> ghosts = new ArrayList<>();

    // current game level
    public static int level;

    // Start game algorithm
    public static void start() {
        gameOver = false;
        level = 0;
        World.start();
        Scene.start(level);
        // starting player positions
        Player.start(1, 1);

        // Instantiate and start Ghost objects
        Ghost ghost1 = new Ghost();
        ghost1.start(5, 5);
        ghosts.add(ghost1); // Add ghost to the list

        // Initialize and play background music
        backgroundMusic = new Music("Assets/background-music.wav");
        backgroundMusic.playLoop();

        // Play sfx with level advancement
        levelExit = new Music("Assets/level-exit-sfx.wav");
    }

    // Update game logic
    public static void update() {
        Player.update(); // update player position
        Player.updateBullets(ghosts); // update bullets

        // Update each ghost's position by looping through the list of ghosts
        for (Ghost ghost : ghosts) {
            ghost.update(); // Call the non-static update method on each ghost instance
        }

        Scene.pickupLogic(level); // Pass logic for key and gem
        Scene.foodCounterCheck(); // Pass food counter logic

        // Check if player reaches exit and has the key
        if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY() && Scene.hasKey() && Scene.hasGem()) {
            System.out.println("Current level: " + level); // Debug message to ensure player is on the right level
            level++;
            System.out.println("Player advanced to level: " + level); // Debug message for level type
            levelExit.play();

            // Reset the ghosts at the start of the new level
            resetGhosts();

            // If player reached the last level, end the game
            if (level == World.getLength()) {
                gameOver = true;
            } else {
                Scene.start(level); // start new level scene
            }
        }

        // Check if any ghost catches the player
        for (Ghost ghost : ghosts) {
            if (Player.getX() == ghost.getX() && Player.getY() == ghost.getY()) {
                if (!ghost.isHexed()) {
                    gameOver = true; // Ends the game if ghost catches the player
                    StdDraw.setPenColor(StdDraw.WHITE);
                    StdDraw.textLeft(10.0, 20.0, "Game Over! The ghost caught you.");
                }
                break; // Stop checking other ghosts once the game is over
            }
        }

        // Check if food counter is 0
        if (Player.getFoodCounter() <= 0) {
            StdDraw.setPenColor(StdDraw.WHITE);
            StdDraw.textLeft(10.0, 20.0, "Game Over! You ran out of food.");
            gameOver = true;
        }
    }

    // Reset ghosts to normal at the start of each level
    private static void resetGhosts() {
        for (Ghost ghost : ghosts) {
            ghost.setHexed(false);
            ghost.resetPosition();
        }
    }

    // Render (draw) game objects
    public static void render() {
        Scene.draw(); // draw scene
        Exit.draw(); // draw exit
        Player.draw(); // draw player

        // Draw all ghosts
        for (Ghost ghost : ghosts) {
            ghost.draw(); // Call draw() for each ghost in the list
        }

        Player.drawBullets(); // draw bullets
        StdDraw.show(100); // Show the updated screen after all drawing operations
    }

    // Main game loop
    public static void main(String[] args) {
        start(); // start game
        while (!gameOver) { // loop while game isn't over
            update(); // update game logic
            render(); // render updated game
        }

        // Stop the background music when the game ends
        if (backgroundMusic != null) {
            backgroundMusic.stop();
        }
    }

    // Allow for more game over methods to be referenced
    public static void setGameOver(boolean status) {
        gameOver = status;
    }
}
