import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

class Maze extends JPanel {
	/*
	 * This class contains the information about the maze. The maze itself is made up of a 2-D array of cells
	 * This class creates the array of cells, and then generates the maze
	 */
	private static final long serialVersionUID = -2783800433675807447L;
	private Cell[][] maze;
	private int scale = 32; // size of a "square" of a maze
	private int offset = 10; // offset from edges of screen

	public Maze(int x, int y) {
		// initialize everywhere in the maze with an empty cell
		maze = new Cell[x][y];
		for (int i = 0; i < x; i++)
			for (int j = 0; j < y; j++)
				maze[i][j] = new Cell(i, j);

		generateMaze();
	}

	/*
	 * This next function generates the maze using a depth first search algorithm
	 * Definition: valid cell - a cell that is inside of the maze and has yet to be visited
	 * 1) mark the current cell as visited
	 * 2) create an array of all valid cells adjacent to the current cell
	 * 3) pick a random cell and move to that cell
	 * 4) mark that cell as being visited
	 * 5) add the cell to the stack
	 * 
	 * if there are no valid cells, get the most recently visited cell (from the stack) and backtrack to it
	 */
	public void generateMaze() {
		Stack<Cell> stack = new Stack<Cell>(); // stack to keep track of backtracking

		// start in the upper right corner
		int x = length() - 1;
		int y = 0;
		Cell current = maze[x][y];
		current.mark(); // mark the current cell as visited
		stack.add(current); // add the cell to the stack
		Cell next;

		// while there are cells in the stack
		while (!stack.isEmpty()) {
			// pick a random neighboring cell
			Cell cell = randomCell(current);
			// if we picked a cell (will be null if no viable options)
			if (cell != null) {
				// get the x and y coordinates of the next cell
				int tempx = cell.getX();
				int tempy = cell.getY();
				next = maze[tempx][tempy];

				// remove the appropriate walls based on the direction we will be traveling in
				if (x < tempx) {
					next.removeWall(Cell.Walls.LEFT);
					current.removeWall(Cell.Walls.RIGHT);
				} else if (y < tempy) {
					next.removeWall(Cell.Walls.TOP);
					current.removeWall(Cell.Walls.BOTTOM);
				} else if (x > tempx) {
					next.removeWall(Cell.Walls.RIGHT);
					current.removeWall(Cell.Walls.LEFT);
				} else if (y > tempy) {
					next.removeWall(Cell.Walls.BOTTOM);
					current.removeWall(Cell.Walls.TOP);
				}

				// add the next cell to the stack
				stack.add(next);
				next.mark(); // mark the next cell as visited
				// make the next cell the current cell (effectively moving to the cell)
				x = tempx;
				y = tempy;
				current = maze[x][y];
			} else {
				// if there are no possible moves, get the next cell from the stack
				next = stack.pop();
				x = next.getX();
				y = next.getY();
				current = maze[x][y];
			}
		}
		// have the start be in the upper left
		maze[0][0].removeWall(Cell.Walls.LEFT);
		// have the end be in the lower right
		maze[length() - 1][length() - 1].removeWall(Cell.Walls.RIGHT);
	}

	// call paint
	@Override
	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		draw(g);
	}

	// pick a random cell that neighbors the cell with the given coordinates
	private Cell randomCell(Cell c) {
		int x = c.getX();
		int y = c.getY();
		ArrayList<Cell> viableOptions = new ArrayList<Cell>();

		// determine if the cell to the right of the current cell is viable
		if (x + 1 != length() && !maze[x + 1][y].isVisited()) {
			viableOptions.add(maze[x + 1][y]);
		}

		// determine if the cell to the left of the current cell is viable
		if (x - 1 != -1 && !maze[x - 1][y].isVisited()) {
			viableOptions.add(maze[x - 1][y]);
		}

		// determine if the cell below the current cell is viable
		if (y + 1 != length() && !maze[x][y + 1].isVisited()) {
			viableOptions.add(maze[x][y + 1]);
		}

		// determine if the cell above the current cell is viable
		if (y - 1 != -1 && !maze[x][y - 1].isVisited()) {
			viableOptions.add(maze[x][y - 1]);
		}

		Cell cell = null;
		// pick a random cell from the current set of cells (null if there aren't any)
		if (!viableOptions.isEmpty()) {
			Random randomizer = new Random();
			cell = viableOptions.get(randomizer.nextInt(viableOptions.size()));
		}

		return cell;
	}

	// this is called length because size() is a method in JPanel
	private int length() {
		return this.maze[0].length;
	}

	// iterate through the maze and draw the necessary walls
	private void draw(Graphics g) {

		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < this.length(); i++) {
			for (int j = 0; j < this.length(); j++) {
				if (maze[i][j].isLeft()) {
					g2d.drawLine(i * scale + offset, j * scale + offset, i * scale + offset, (j + 1) * scale + offset);
				}
				if (maze[i][j].isRight()) {
					g2d.drawLine((i + 1) * scale + offset, j * scale + offset, (i + 1) * scale + offset, (j + 1) * scale + offset);
				}
				if (maze[i][j].isBottom()) {
					g2d.drawLine(i * scale + offset, (j + 1) * scale + offset, (i + 1) * scale + offset, (j + 1) * scale + offset);
				}
				if (maze[i][j].isTop()) {
					g2d.drawLine(i * scale + offset, j * scale + offset, (i + 1) * scale + offset, j * scale + offset);
				}
			}
		}
	}
}