//Amber and Dom Ketchens
//Project 4: Maze Game

public class MazeGame{
	private static boolean gameOver;

		//Start game algorithm
		public static void start(){
			gameOver = false;
			World.start();
		}

		public static void update(){

		}

		public static void render(){

		}

		public static void main(String[] args){
			start();
			while (gameOver == false)
				{
					update();
					render();
				}

		}

}