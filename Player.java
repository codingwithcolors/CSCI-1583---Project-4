//Amber and Dom Ketchens
//Project 4: Maze Game - Player

public class Player {
    public static final int TILE_SIZE = 32;
    
    // player starting position and image
    private static int x;
    private static int y;
    private static String image;

    //players food/health counter
    private static int foodCounter;
    private static int foodX;
    private static int foodY;
    private static boolean playerMoved = false; // tracks if player moved

    // players starting posistion & image
    public static void start(int x, int y) {
        Player.x = x;
        Player.y = y;
        image = "Assets/player-front-idle.png";
    }

    // draws players position on screen
    public static void draw() {
        int tileX = x * TILE_SIZE + TILE_SIZE / 2;
        int tileY = y * TILE_SIZE + TILE_SIZE / 2;
        StdDraw.picture(tileX, tileY, image);
    }

    // updates players position/image based on input
    public static void update() {
        foodCounter = 100; 
        boolean moved = false; // checks if player moved
        if (StdDraw.hasNextKeyTyped() == true) {
            char key = StdDraw.nextKeyTyped();

            // player movement based on key pressed
            if (key == 'w' && Scene.canMove(x, y - 1)) {
                y--; moved = true;// 'w' Move up
                foodCounter--;
                image = "Assets/player-back-idle.png";
            } else if (key == 's' && Scene.canMove(x, y + 1)) {
                y++; moved = true;// 's' Move down
                foodCounter--;
                image = "Assets/player-front-idle.png";
            } else if (key == 'a' && Scene.canMove(x - 1, y)) {
                x--; moved = true;// 'a' Move left
                foodCounter--;
                image = "Assets/player-left-1.png";
            } else if (key == 'd' && Scene.canMove(x + 1, y)) {
                x++; moved = true;// 'd' Move right
                foodCounter--;
                image = "Assets/player-right-idle.png";
            }
        }
        // sets playerMoved to true if the player has moved
        playerMoved = moved;
    }
    // get method returns players current X coordinate
    public static int getX() {
        return x;
    }
    // get method returns players current Y coordinate
    public static int getY() {
        return y;
    }
    // get method checks if player has moved in the current update
    public static boolean hasMoved() {
        return playerMoved;
    }
}