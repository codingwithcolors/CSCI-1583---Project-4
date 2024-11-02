//Amber and Dom Ketchens
//Project 4: Maze Game - Scene

public class Scene{
	private static int rows;
	private static int cols;
	private static boolean[][] walls;

	 public static void start(int level){
	 	String[][] map = World.getLevel(level);
	 	rows = map.length;
	 	cols = map[0].length;

	 	walls = new boolean[rows][cols];
	 	for (int y = 0; y < rows; y++)
	 		{
	 			for (int x = 0; x < cols; x++)
	 				{
	 					String tile = map[y][x];
	 					setTile(x, y, tile);
	 				}
	 		}
	 }

	 public static void setTile(int x, int y, String tile){
	 	if (tile.equals("#") ){
	 		walls[y][x] = true;
	 	}
	 }

	 public static void draw(){
	 	for (int y = 0; y < rows; y++)
	 		{
	 			for (int x = 0; x < cols; x++)
	 				{
	 					System.out.printf("%Ss ", walls[y][x] );
	 				}
	 			System.out.print("\n");
	 		}
	 }
}