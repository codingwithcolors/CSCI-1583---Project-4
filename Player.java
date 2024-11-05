//Amber and Dom
//Project 4: Maze Game - Player

public class Player{
	public static final int TILE_SIZE = 32;
	private static int x;
	private static int y;
	private static String image;

	//Start player data
	public static void start(int x, int y)
		{
			Player.x = x;
			Player.y = y;
			image = "Assets/character-chicken.png";
		}

	public static void draw()
		{
			int tileX = x * TILE_SIZE + TILE_SIZE/2;
			int tileY = y * TILE_SIZE + TILE_SIZE/2;
			StdDraw.picture(tileX, tileY, image);
		}
}