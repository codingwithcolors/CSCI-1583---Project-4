//Amber and Dom Ketchens
//Project 4: Maze Game - Scene

public class Scene {
    private static final int TILE_SIZE = 32;

    private static int rows;
    private static int cols;
    private static boolean[][] walls;
    private static int width;
    private static int height;
    private static String floorImage;
    private static String wallImage;
    private static int keyX = -1;
    private static int keyY = -1; //Add key off grid
    private static String keyImage;
    private static boolean keyPickup = false; //Set key pickup to be false to prepare for later player interaction

    public static void keyLogic(int level)
    	{
	    	StdDraw.setPenColor(StdDraw.WHITE); //Setup text color for all message pop ups for keyLogic
			//Check Key Pickup
			if (Player.getX() == keyX && Player.getY() == keyY && keyPickup == false) //Checking if keyPickup is set to false and moving the key off the grid completely prevents anymore interactions once the key has been picked up by the player
			{
				keyPickup = true;
				keyX = cols + 1; //Put the key off the grid
				keyY = rows + 1; //Put the key off the grid
				StdDraw.textLeft(10.0, 20.0, "Picked up the key. Hurry to the exit!");
			}


		//Lock the door if the player doesn't have a key
		if (Player.getX() == Exit.getX() && Player.getY() == Exit.getY()) //note exit naming may not be accurate due to having to change for the file
			{
				if (keyPickup == false) //Prevent the player from advancing until keyPickup is true
					{
						StdDraw.textLeft(10.0, 20.0, "The door is locked. Find a key.");
					}
				else //Player moves onto the next level
					{
						StdDraw.textLeft(10.0, 20.0, "You made it to the next level. Keep going!");
	  				}
	  		}
	  	}

    public static void start(int level) {
        floorImage = "Spooky Assets/tile-passage.png";
        wallImage = "Spooky Assets/tile-brickwall.png";
        keyImage = "Spooky Assets/key.png";
        keyPickup = false; //Reset key pickup for each new level

        String[][] map = World.getLevel(level);
        rows = map.length;
        cols = map[0].length;

        width = cols * TILE_SIZE;
        height = rows * TILE_SIZE;

        walls = new boolean[rows][cols];
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                String tile = map[y][x];
                setTile(x, y, tile);
            }
        }

        // Setup canvas data (tile & scale)
        StdDraw.setCanvasSize(width, height);
        StdDraw.setXscale(0.0, width);
        StdDraw.setYscale(height, 0.0);

        //Setup booleans for key logic
        boolean withinBounds;
        boolean overWall;
        boolean overExit;

        //Place key in a random location
        do
        	{
        		keyX = (int) (Math.random() * cols);
        		keyY = (int) (Math.random() * rows);
 
        	//Check if the key is within bounds of the game popup
        	withinBounds = (keyX >= 0 && keyX < cols) && (keyY >= 0 && keyY < rows);

        	//Check if the key is overlapping a brick wall tile
        	overWall = withinBounds && walls[keyY][keyX];

        	//Check if the key is overlapping the exit tile
        	overExit = withinBounds && (keyX == Exit.getX() && keyY == Exit.getY());


        	//Debug Script that shows how the do while loop keeps running until the correct conditions are met
        	if (withinBounds == false)
        		{
        			System.out.println("Key is out of bounds: " + keyX + ", " + keyY);
        		}
        	if (overWall)
        		{
        			System.out.println("Key is on a wall at: " + keyX + ", " + keyY);
        		}
        	if (overExit)
        		{
        			System.out.println("Key is on the exit at: " + keyX + ", " + keyY);
        		}
    }	while (withinBounds == false || overWall || overExit);
	}

	//Check if the player has the key for MazeGame.java to check
	public static boolean hasKey()
		{
			return keyPickup;
		}

    public static void setTile(int x, int y, String tile) {
        if (tile.equals("#")) {
            walls[y][x] = true;
        } else if (tile.equals("@")) {
            Player.start(x, y);
        } else if (tile.equals("!")) {
            Exit.start(x,y);
        }
    }

    public static void draw() {
        for (int y = 0; y < rows; y++) {
            for (int x = 0; x < cols; x++) {
                int tileX = x * TILE_SIZE + TILE_SIZE / 2;
                int tileY = y * TILE_SIZE + TILE_SIZE / 2;

                if (walls[y][x] == true) {
                    StdDraw.picture(tileX, tileY, wallImage);
                } else {
                    StdDraw.picture(tileX, tileY, floorImage);
                }
            }
        }

    //Draw the key in the level
    if (keyPickup == false)
    	{
    		int keyTileX = keyX * TILE_SIZE + TILE_SIZE/2;
    		int keyTileY = keyY * TILE_SIZE + TILE_SIZE/2;
    		StdDraw.picture(keyTileX, keyTileY, keyImage);
    	}
    }

    public static boolean canMove(int x, int y) {
        return !walls[y][x];
    }

    //Random generation coordinates logic for several objects
	public static int randomCoordinates(int size)
		{
			return (int) (Math.random() * (size - 1)); //Move 1 less than the total declared size for colSize and rowSize to avoid the edges, and take a random value
		}
}
