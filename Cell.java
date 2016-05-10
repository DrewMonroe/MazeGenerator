class Cell {
	/*
	 * This class contains information about a cell in the maze.
	 * Essentially, the maze itself is made up of a grid of cells, each of which has between 0 and 4 walls. 
	 * These walls are referenced by the boolean variables below. If a variable is true then there is a wall at the specified location
	 */
	// enum to make referencing of walls easier
	public enum Walls {
		LEFT, RIGHT, TOP, BOTTOM
	};

	// boolean for is a cell has been visited
	private boolean visited;
	// booleans for if these walls exist (for drawing)
	private boolean left, right, top, bottom;
	private int x, y;

	public Cell(int x, int y) {
		this.visited = false;
		this.left = true;
		this.right = true;
		this.top = true;
		this.bottom = true;
		this.x = x;
		this.y = y;
	}

	// remove a wall from the cell
	public void removeWall(Walls w) {
		switch (w) {
		case LEFT:
			this.left = false;
			break;
		case RIGHT:
			this.right = false;
			break;
		case TOP:
			this.top = false;
			break;
		case BOTTOM:
			this.bottom = false;
			break;
		}
		visited = true;
	}

	// mark a wall as visited
	public void mark() {
		this.visited = true;
	}

	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}

	public boolean isLeft() {
		return left;
	}

	public boolean isVisited() {
		return visited;
	}

	public boolean isRight() {
		return right;
	}

	public boolean isBottom() {
		return bottom;
	}

	public boolean isTop() {
		return top;
	}
}