# CSCI-1583---Project-4
Group project for Project 4 - Maze Game

11.12.24
Code updates needed
1. Make hexxed ghost work
2. Make the bullet in the right direction
3. Update text orientation

Record videos
1. All scenarios

Major points
1. Features and general overview


11.8.24 Summary:
1. Timer added to top left.
2. Popup text relocated towards bottom of the screen.
3. Gems / jack-o-lanterns can be picked up. The player now must have both the key and the jack-o-lantern to advance in the game.
4. foodCounter is partially implemented. Tiles are changed and player movement reduces the foodCounter. However, this feature is still unfinished. Will work on it more over the weekend to wrap up foodcounter timer, game losing condition, and adding food to the levels.

11.6.24 Summary:

New feature key added
1. Player is unable to advance to the next level on the exit tile without the key
2. Key will reconfigurate until it is not overlapping any other object (see debug terminal on line 93 - 106 for logic)
3. Pop up messages occur for key pickup, level advancement, and locked door logic. Majority of the changes are within Scene.java.

11.5.24 Summary (Dom):

Page 15 out of 22 of initial code finished.
Fixed class issues with pages 11-13. Now added Player.java.
Code should run a fully imaged pop up with all the old assets with wasd control.
Added some of the new assets for review:
Jack-O-Lantern = Gem
Candy = Food
Ghost & Ghost Hexxed = Enemy
Key = Key (lol)
Exit, tilebrickwall, and tile passage same name but updated.

Will work on the player and more code later today.

11.1.24 Summary (Dom):

Page 10 out of 22 of initial code finished.
MazeGame.java, Scene.java, and World.java are fully compiled and able to run.

Right now running "java MazeGame < Assets/world.txt" just returns a bunch of TRUEs and FALSEs and opens a blank standard draw file lol.

Stuck on page 11. I think it has to do with the StdDraw file? Not sure. I uploaded the prof StdDraw.java file to take a look later.
