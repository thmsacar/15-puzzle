package model;


import util.AbstractListenableModel;

import java.util.ArrayList;
import java.util.Random;

/**
 * Model of the grill
 */
public class ModelGrill extends AbstractListenableModel {

	/* Two dimensional array representring grid */
	private int[][] grid;

	/* Cardinal directions */
	public static final int[] NORTH = {-1, 0};
	public static final int[] EAST = {0, 1};
	public static final int[] SOUTH = {1, 0};
	public static final int[] WEST = {0, -1};

	public static final int[][] DIRECTIONS = {NORTH, EAST, SOUTH, WEST};


	/* Position of the empty cell */
	private int emptyCell;

	/* Number of lines and columns */
	private final int lines, columns;

	/* Number of moves already played. Initialize to zero on start. */
	private int nbMoves;

	/* Tells if grid has been already shuffled. If the grid has never been shuffled then the game is not finished */
	private boolean isShuffled;

	private static final Random random = new Random();

	public ModelGrill(int lines, int columns){
		
		this.lines = lines;
		this.columns = columns;
		this.initGrid();
		this.isShuffled = false;
		this.nbMoves = 0;

	} 

	/* Initialize a sorted grid */
	private void initGrid(){
		this.grid = new int[this.lines][this.columns];
		for(int i = 0; i < this.lines; i++){
			for(int j = 0; j < this.columns; j++){
				this.grid[i][j] = i*this.columns + j;
			}
		}
		this.grid[this.lines - 1][this.columns -1 ] = -1 ;
		this.emptyCell = this.columns *this.lines -1;
	}

	/**
	 *
	 * @return List of positions of playable numbers
	 */
	public ArrayList<Integer> getPlayable(){
		return getNeighbours(emptyCell);
	}

	/**
	 * @return list of positions of neighbours
	 */
	public ArrayList<Integer> getNeighbours(int pos){
		ArrayList<Integer> neighbours = new ArrayList<>();
		for(int[] direction : DIRECTIONS){
			int neighbour = transform(pos, direction);
			neighbours.add(neighbour);
		}
		return neighbours;
	}

	/**
	 *
	 * @param pos position of case
	 * @param direction int array representing direction of transformation
	 * @return returns transformed position if possible, -1 if not
	 */
	public int transform(int pos, int[] direction){
		int line = pos / this.columns;
		int column = pos % this.columns;
		line += direction[0];
		column += direction[1];
		if (line>=0 && line<this.lines && column>=0 && column<this.columns){
			return line*this.columns + column;
		}
		return -1;
	}


	public int getNbColumns(){
		return this.columns;
	}
	
	public int getNbLines(){
		return this.lines;
	}
	
	public int getNbMoves(){
		return this.nbMoves;
	}

	public void resetNbMoves(){
		this.nbMoves = 0;
		fireChange();
	}
		

	/**
	 *
	 * @param pos Position
	 * @return Line of the given position
	 */
	public int getLineFromPos(int pos){
		return pos / this.columns;
	}

	/**
	 *
	 * @param pos Position
	 * @return Column of the given position
	 */
	public int getColumnFromPos(int pos){
		return pos % this.columns;
	}

	/**
	 * Get value of a case from its position
	 * @param pos position of case
	 * @return value at given position
	 */
	public int getCase(int pos){
		int line = pos / this.columns;
		int column = pos % this.columns;
		return this.grid[line][column];
	}

	/**
	 * Get position of the empty case
	 * @return position of empty case
	 */
	public int getEmptyCell(){
		return this.emptyCell;
	}

	/**
	 * Swaps values in the given positions
	 * @param pos1 Position 1
	 * @param pos2 Position 2
	 */
	public void swap(int pos1, int pos2){
		//Check if params have good values, if not, do nothing
		if (pos1==pos2) return;
		if (pos1 < 0 || pos1 >= this.lines *this.columns) return;
		if (pos2 < 0 || pos2 >= this.lines *this.columns) return;

		//If one value is the empty case, modify empty case's position
		if(pos1 == this.emptyCell){
			this.emptyCell = pos2;
		}
		else if(pos2 == this.emptyCell){
			this.emptyCell = pos1;
		}

		//Swap values
		int temp = getCase(pos1);
		grid[getLineFromPos(pos1)][getColumnFromPos(pos1)] = getCase(pos2);
		grid[getLineFromPos(pos2)][getColumnFromPos(pos2)] = temp;

		//Fire change for updating view
		this.nbMoves++;
		fireChange();
	}


	/**
	 * Plays north if possible
	 * This method is here to be used by keyboard or some other logic. For button click use swap
	 */
	public void playNorth(){
		int north = transform(this.emptyCell, NORTH);
		if (north == -1) return;
		swap(this.emptyCell, north);
		this.emptyCell = north;
	}

	/**
	 * Plays east if possible
	 * This method is here to be used by keyboard or some other logic. For button click use swap
	 */
	public void playEast(){
		int east = transform(this.emptyCell, EAST);
		if (east == -1) return;
		swap(this.emptyCell, east);
		this.emptyCell = east;
	}

	/**
	 * Plays south if possible
	 * This method is here to be used by keyboard or some other logic. For button click use swap
	 */
	public void playSouth(){
		int south = transform(this.emptyCell, SOUTH);
		if (south == -1) return;
		swap(this.emptyCell, south);
		this.emptyCell = south;
	}

	/**
	 * Plays west if possible
	 * This method is here to be used by keyboard or some other logic. For button click use swap
	 */
	public void playWest(){
		int west = transform(this.emptyCell, WEST);
		if (west == -1) return;
		swap(this.emptyCell, west);
		this.emptyCell = west;
	}

	/**
	 * @return Two dimensional array representring grid
	 */
	public int[][] getGrid(){
		return this.grid;
	}
	
	/**
	 * @return true si la grille est bonne, false sinon
	 */
	public boolean isSorted(){
		int expectedValue = 0;
		for(int i = 0; i<this.lines; i++){
			for (int j = 0; j < this.columns; j++){
				if (grid[i][j] != expectedValue ){
					if( i != this.lines -1 || j != this.columns -1){
						
						return false;
					}
				}
				expectedValue++;
				
			}

		}
		return true;
	}

	/**
	 * Method to determine whether game is won. A game is won only if it is sorted, and it has already been shuffled once.
	 * @return Whether game is won or not
	 */
	public boolean isWin(){
		if(!isShuffled) return false;
		return isSorted();
	}

	/**
	 * Make a random move once
	 */
	private void playRandom(){
		ArrayList<Integer> playable = getPlayable();
		int randomIndex = random.nextInt(playable.size());
		int randomPos = playable.get(randomIndex);
		swap(randomPos, this.emptyCell);
	}

	/**
	 * Make a random move n times
	 * @param n number of times to play randomly
	 */
	private void playRandom(int n){
		for(int i = 0; i < n; i++){
			playRandom();
		}
	}

	/**
	 * Shuffle the game
	 */
	public void shuffle(){
		do{
			isShuffled = true;
			int n = lines * columns * 50;
			playRandom(n);
			resetNbMoves();
		}while(this.isWin());

	}

	/**
	 * This to strings use special character borders to make a more readable grill
	 * @return
	 */
	@Override
	public String toString(){
		StringBuilder grilleStr = new StringBuilder();
		//We use special chars to make grille look better on CLI.
		String topBorder 	= "╔" + "═".repeat(5 * this.columns - 1) + "╗\n";
		String midBorder 	= "╟" + "─".repeat(5 * this.columns - 1) + "╢\n";
		String bottomBorder = "╚" + "═".repeat(5 * this.columns - 1) + "╝";

		//Start with top border
		grilleStr.append(topBorder);
		for(int i = 0; i < this.lines; i++){
			grilleStr.append("║");
			for(int j = 0; j < this.columns; j++){
				//This to print at least 2 characters, no matter number of digits. It works similar to C lang. - is to add spaces after, not before.
				String nb = String.format(" %-2d ", this.grid[i][j]);
				grilleStr.append(nb);
				//We are seperating cells only if we are not at the last column
				if (j < this.columns - 1) {
					grilleStr.append("│");
				}
			}
			grilleStr.append("║\n");
			//Adding middle lines if we are not at last line
			if (i < this.lines - 1) {
				grilleStr.append(midBorder);
			}
		}
		//Close the bottom
		grilleStr.append(bottomBorder);
		return grilleStr.toString();
	}
	
	
	
}
