//Amber and Dom Ketchens
//Project 4: Maze Game - World

import java.util.Scanner;

public class World{

	public static void start(){
		//Get all the map data and save it for later
		Scanner input = new Scanner(System.in);
		int count = input.nextInt();
		for (int lvl = 0; lvl < count; lvl++)
			{
				int rows = input.nextInt();
				int cols = input.nextInt();
				setLevel(rows, cols, input);
			}
	}

	public static void setLevel(int rows, int cols, Scanner input){
		for (int y = 0; y < rows; y++)
			{
				for (int x = 0; x < cols; x++)
					{
						String tile = input.next();
						System.out.print(tile);
					}
				System.out.print("\n");
			}
	}

}