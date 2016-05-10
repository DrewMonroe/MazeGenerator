import javax.swing.JFrame;
import javax.swing.JOptionPane;

class UI extends JFrame {
	/*
	 * This class is used for the user interface, to actually display the maze so the user can see it
	 */
	private static final long serialVersionUID = -6472859011023263538L;

	/*
	 * Prompt the user for the size of the maze
	 * Generate the Maze
	 * Display the Maze
	 */
	public void init() {
		boolean failed = true; // used to determine if the user needs to be prompted for the size again
		int size = 0; // the size of the maze
		while (failed) {
			try {
				String input = JOptionPane.showInputDialog("Please enter a number for the size of the maze:");
				// make sure the user entered something (or didn't press cancel)
				if (input != null && input.length() > 0) {
					size = Integer.parseInt(input); // if the user didn't enter a number, NumberFormatException is thrown
					if (size < 1) {
						// the user entered an invalid number
						throw new NumberFormatException("Number is less than 1");
					}
					failed = false;
				} else {
					failed = false;
					System.exit(1);
				}
			} catch (NumberFormatException e) {
				// nothing needs to be done because of the loop
			}
		}
		add(new Maze(size, size));
		setTitle("Drew's Maze");
		setSize((size + 1) * 32, (size + 2) * 32); // +2 because of title bar on Linux (windows?)
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
}