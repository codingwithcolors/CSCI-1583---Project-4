import java.util.Random; // import random class

public class Ghost {
    public static final int TILE_SIZE = 32;

    // Ghost position and image
    private static int x;
    private static int y;
    private static String image;
    private static boolean isHexed = false;

    // Speed of the ghost (how many tiles it moves per update)
    private static final int SPEED = 1;
    private static Random rand = new Random(); // Creates a random number generator

    public static void hex() {
        isHexed = true;
        image = "Assets/ghost-hexxed.png";
    }

    // Setter for hexed state
    public static void setHexed(boolean hexed) {
        isHexed = hexed;
    }

    public static boolean isHexed() {
        return isHexed;
    }

    // Start the ghost at a specific position
    public static void startRandom() {
        image = "Assets/Ghost.png"; // Set the image of the ghost

        // Get the maze dimensions for each level and pick a random coordinate
        int cols = Scene.getCols();
        int rows = Scene.getRows();

        // Find a random position that doesn't overlap the walls
        do {
            x = rand.nextInt(cols); // Random x position
            y = rand.nextInt(rows); // Random y position
        } while (x == Player.getX() && y == Player.getY() || !Scene.canMove(x, y));
    }

    // Draw the ghost at its current position
    public static void draw() {
        int tileX = x * TILE_SIZE + TILE_SIZE / 2;
        int tileY = y * TILE_SIZE + TILE_SIZE / 2;
        StdDraw.picture(tileX, tileY, image); // Draw the ghost at its position
    }

    // Update the ghost's position to chase the player
    public static void update() {
        if (!Player.hasMoved()) {
            return; // Return if the player hasn't moved
        }

        // Get the player's current position
        int playerX = Player.getX();
        int playerY = Player.getY();

        if (isHexed) {
            fleeFromPlayer(playerX, playerY); // Just move away, don't harm
        } else {
            moveTowardsPlayer(playerX, playerY);
        }
    }

    // Flee from the player (move away from the player)
    public static void fleeFromPlayer(int playerX, int playerY) {
        boolean moved = false;

        // Try moving away horizontally (left-right)
        if (x < playerX && Scene.canMove(x + SPEED, y)) {
            x++; // Move right away from the player
            moved = true;
        } else if (x > playerX && Scene.canMove(x - SPEED, y)) {
            x--; // Move left away from the player
            moved = true;
        }

        // Try moving away vertically (up-down)
        if (!moved) {
            if (y < playerY && Scene.canMove(x, y + SPEED)) {
                y++; // Move down away from the player
                moved = true;
            } else if (y > playerY && Scene.canMove(x, y - SPEED)) {
                y--; // Move up away from the player
                moved = true;
            }
        }

        // If the ghost couldn't move away, move randomly
        if (!moved) {
            randomDirection();
        }
    }

    // Move towards the player (move towards the player)
    public static void moveTowardsPlayer(int playerX, int playerY) {
        boolean moved = false;

        // Try to move horizontally (left-right)
        if (x < playerX && Scene.canMove(x + SPEED, y)) { // Move right
            x++;
            moved = true;
        } else if (x > playerX && Scene.canMove(x - SPEED, y)) { // Move left
            x--;
            moved = true;
        }

        // Try moving towards the player vertically (up-down)
        if (!moved) {
            if (y < playerY && Scene.canMove(x, y + SPEED)) {
                y++; // Move down towards the player
                moved = true;
            } else if (y > playerY && Scene.canMove(x, y - SPEED)) {
                y--; // Move up towards the player
                moved = true;
            }
        }

        // If the ghost couldn't move towards the player, move randomly
        if (!moved) {
            randomDirection();
        }
    }

    // Randomly move the ghost if it is stuck. Scene.canMove checks whether
    // it can move to a new tile.
    private static void randomDirection() {
        // Try a random direction (up, down, left, right)
        int direction = rand.nextInt(4); // 0 = left, 1 = right, 2 = up, 3 = down

        switch (direction) {
            case 0: // Try moving left
                if (Scene.canMove(x - SPEED, y)) {
                    x--;
                }
                break;
            case 1: // Try moving right
                if (Scene.canMove(x + SPEED, y)) {
                    x++;
                }
                break;
            case 2: // Try moving up
                if (Scene.canMove(x, y - SPEED)) {
                    y--;
                }
                break;
            case 3: // Try moving down
                if (Scene.canMove(x, y + SPEED)) {
                    y++;
                }
                break;
        }
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
}
