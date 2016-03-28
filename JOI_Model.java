import java.util.*;

public class JOI_Model {
	// newProjectile function
	// newCoin
	// coinCollected (controller needs to make timer delay smaller)
	// keep track of score?
	// nextFrame function where the projectiles move linearly
	// moveUp...etc function
	JOI_Controller c;
	public final static char PLAYER = '*';
	public final static char GROUND = '-';
	public final static char UNWALKABLE = 'x';
	public final static char BOULDER = 'o';
	public final static char NOTHING = ' ';
	public final int numRows = 20;
	public final int numCols = 20;

	public char[][] background;
	public char[][] sprites;
	public int time = 0;

	public JOI_Model(JOI_Controller c) {

		this.c = c;
		startOver();
	
	}


	public JOI_Model(JOI_Controller c) {
		this.c = c;
		startOver();
	}

	public void startOver() {
		// get combined view
		Random r = new Random();

		background = new char[numRows][numCols];
		for (int i = 0; i < 3; i++) {
			int row = r.nextInt(numRows);
			int col = r.nextInt(numCols);

			if (background[row][col] != UNWALKABLE) {
				background[row][col] = UNWALKABLE;
			} else {
				while (background[row][col] == UNWALKABLE) {
					row = r.nextInt(numRows);
					col = r.nextInt(numCols);
				}
				background[row][col] = UNWALKABLE;
			}
		}

		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (background[i][j] != UNWALKABLE) {
					background[i][j] = GROUND;
				}
			}
		}

		sprites = new char[numRows][numCols];
		sprites[numRows / 2][numCols / 2] = PLAYER;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (sprites[i][j] != PLAYER) {
					sprites[i][j] = NOTHING;
				}
			}
		}
	}

	public char[][] getCombinedView() {
		
		char[][] test = new char[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (sprites[i][j] == NOTHING) {
					test[i][j] = background[i][j];
				} else {
					test[i][j] = sprites[i][j];
				}
			}
		}
		return test;
	}

	public void moveUp() {
		int row = getRow();
		int col = getCol();
		if(ifWalkable(row -1, col)){
			sprites[row - 1][col] = PLAYER;
			sprites[row][col] = NOTHING;
		}
	}

	public void moveDown() {
		int row = getRow();
		int col = getCol();
		if(ifWalkable(row +1, col)){
			sprites[row + 1][col] = PLAYER;
			sprites[row][col] = NOTHING;
		}
	}

	public void moveRight() {
		int row = getRow();
		int col = getCol();
		if(ifWalkable(row, col + 1)){
			sprites[row][col + 1] = PLAYER;
			sprites[row][col] = NOTHING;
		}
	}

	public void moveLeft() {
		int row = getRow();
		int col = getCol();
		if(ifWalkable(row, col - 1)){
			sprites[row][col - 1] = PLAYER;
			sprites[row][col] = NOTHING;
		}
	}
	
	public int getRow(){
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (sprites[i][j] == PLAYER) {
					return i;
				}
			}
		}
		return -1;
	}
	
	public int getCol(){
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				if (sprites[i][j] == PLAYER) {
					return j;
				}
			}
		}
		return -1;
	}
	
	public boolean ifWalkable(int row, int col){
		return row >= 0 && row < numRows && col >= 0 && col < numCols && background[row][col] != UNWALKABLE;
	}

	public void timeElapsed(int t){
		time = t;
		if(time % 2 == 0){
			addObject(UNWALKABLE);
		}
	}
	
	public void addObject(char obj){
		
		Random r = new Random();
		int row = r.nextInt(numRows);
		int col = r.nextInt(numCols);
		System.out.println(row + " " + col);

		if (background[row][col] != obj) {
			background[row][col] = obj;
		} else {
			while (background[row][col] == obj) {
				row = r.nextInt(numRows);
				col = r.nextInt(numCols);
			}
			background[row][col] = obj;
		}
	}

	public void printBoard(char[][] c){
		for(int row = 0; row < numRows; row++){
			for(int col = 0; col < numCols; col++){
				System.out.print(" " + c[row][col]);
			}
			System.out.println();
		}
		System.out.println("\n\n\n");
	}
}