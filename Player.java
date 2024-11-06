//Amber and Dom Ketchens
//Project 4: Maze Game - Player

public class Player {
    public static final int TILE_SIZE = 32;
    private static int x;
    private static int y;
    private static String image;

    // Start player data
    public static void start(int x, int y) {
        Player.x = x;
        Player.y = y;
        image = "Spooky Assets/player-front-idle.png";
    }

    public static void draw() {
        int tileX = x * TILE_SIZE + TILE_SIZE / 2;
        int tileY = y * TILE_SIZE + TILE_SIZE / 2;
        StdDraw.picture(tileX, tileY, image);
    }

    public static void update() {
        if (StdDraw.hasNextKeyTyped() == true) {
            char key = StdDraw.nextKeyTyped();
            if (key == 'w' && Scene.canMove(x, y - 1)) {
                y--; // Move up
            } else if (key == 's' && Scene.canMove(x, y + 1)) {
                y++; // Move down
            } else if (key == 'a' && Scene.canMove(x - 1, y)) {
                x--; // Move left
            } else if (key == 'd' && Scene.canMove(x + 1, y)) {
                x++; // Move right
            }
        }
    }

    public static int getX() {
        return x;
    }

    public static int getY() {
        return y;
    }
}
