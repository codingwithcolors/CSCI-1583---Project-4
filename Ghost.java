import java.util.Random;

public class Ghost {
    public static final int TILE_SIZE = 32;

    // Ghost position and image
    private int x;
    private int y;
    private String image;
    private boolean isHexed = false;
    private int damage = 10;

    // Speed of the ghost (how many tiles it moves per update)
    private static final int SPEED = 1;

    // Random object for direction changes
    private static Random rand = new Random(); // creates a random number generator

    // Start the ghost at a specific position
    public void start(int startX, int startY) {
        x = 8; // Default x position (restored to original value)
        y = 6; // Default y position (restored to original value)
        image = "Assets/Ghost.png"; // Set the image of the ghost
    }

    // Hex the ghost (turn it into a hexed state)
    public void hex() {
        isHexed = true;
        this.image = "Assets/ghost-hexxed.png"; // Set hexed image
    }
    //reset position of the ghost
    public void resetPosition() {
        start(8,6); // Reset to initial position
    }

    // getter for damage
    public int getDamage() {
        return damage;
    }

    public void setHexed(boolean hexed) {
        this.isHexed = hexed;
    }

    // Check if the ghost is hexed
    public boolean isHexed() {
        return isHexed;
    }

    // Draw the ghost at its current position
    public void draw() {
        int tileX = x * TILE_SIZE + TILE_SIZE / 2;
        int tileY = y * TILE_SIZE + TILE_SIZE / 2;
        StdDraw.picture(tileX, tileY, image); // Draw the ghost at its position
    }

    // Update the ghost's position to chase or flee from the player
    public void update() {
        if (!Player.hasMoved()) {
            return; // Return if the player hasn't moved
        }

        // Get the player's current position
        int playerX = Player.getX();
        int playerY = Player.getY();

        // If hexed, move without harming the player
        if (isHexed) {
            fleeFromPlayer(playerX, playerY);  // Just move away, don't harm
        } else {
            // Regular ghost behavior: Move towards the player (may cause damage)
            moveTowardsPlayer(playerX, playerY);
        }
    }

    // Flee from the player (move away from the player)
    public void fleeFromPlayer(int playerX, int playerY) {
        boolean moved = false;

        // First, try to move horizontally (left-right)
        if (x < playerX && Scene.canMove(x - SPEED, y)) { // Move left
            x--;
            moved = true;
        } else if (x > playerX && Scene.canMove(x + SPEED, y)) { // Move right
            x++;
            moved = true;
        }

        // If not moved horizontally, try to move vertically (up-down)
        if (!moved) {
            if (y < playerY && Scene.canMove(x, y - SPEED)) { // Move up
                y--;
            } else if (y > playerY && Scene.canMove(x, y + SPEED)) { // Move down
                y++;
            }
        }
    }

    // Move towards the player (standard ghost behavior)
    public void moveTowardsPlayer(int playerX, int playerY) {
        boolean moved = false;

        // Try to move horizontally (left-right)
        if (x < playerX && Scene.canMove(x + SPEED, y)) { // Move right
            x++;
            moved = true;
        } else if (x > playerX && Scene.canMove(x - SPEED, y)) { // Move left
            x--;
            moved = true;
        }

        // If not moved horizontally, try to move vertically (up-down)
        if (!moved) {
            if (y < playerY && Scene.canMove(x, y + SPEED)) { // Move down
                y++;
            } else if (y > playerY && Scene.canMove(x, y - SPEED)) { // Move up
                y--;
            }
        }

        // If the ghost couldn't move towards the player, move randomly
        if (!moved) {
            randomDirection();
        }
    }

    // Randomly move the ghost if it is stuck. Scene.canMove checks whether
    // it can move to a new tile.
    private void randomDirection() {
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

    // Getter methods for the ghost's position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
