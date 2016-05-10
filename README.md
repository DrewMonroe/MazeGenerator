# MazeGenerator
##### Description
This program randomly generates a maze based upon user parameters. 

##### Overview
The main class for this project is the `MazeCreator.java` class. This class creates a new window, a GUI for the user to interact with (`UI.java`). The user is then prompted for a number for the size of the maze; the number provided will be the height and width of the maze. It then add a new maze to the screen. The maze (contained in the `Maze.java` class) uses a Depth First Search graph traversal algorithm (DFS) and a random number generator to randomly generate a maze. Due to the behavior of DFS, there will be a guaranteed path from start to end of the maze.

##### Installation
In order to run this program, the user needs to have Java 8 installed on their machine (earlier versions of Java may work but are untested). Simply download this repository, compile all of the files, and run the `MazeCreator.java` file.
