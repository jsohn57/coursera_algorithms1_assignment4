
import java.lang.*;
import java.util.*;

public class Board {
	
	private final int[][] blocks;
	private final int dimension;
	private int spaceRow;
	private int spaceCol;
	
	// construct a board from an N-by-N array of blocks where blocks[i][j] = block in row i, column j
	public Board(int[][] blocks){
		dimension = blocks.length;
		this.blocks = new int[dimension][dimension];
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				this.blocks[i][j] = blocks[i][j];
				if(blocks[i][j] == 0){
					spaceRow = i;
					spaceCol = j;
				}
			}
		}
	}
	
	// board dimension N
	public int dimension(){
		return dimension;
	}
	
	// number of blocks out of place
	public int hamming(){
		int diff = 0;
		int refPoint = 1;
		
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				if((refPoint < dimension*dimension) && (blocks[i][j] != refPoint++)){
					diff++;
				}
			}
		}
		return diff;
	}
	
	// sum of Manhattan distance between blocks and goal
	public int manhattan(){
		int goalColumn = 0, goalRow = 0, refPoint = 0, diff = 0;
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				refPoint = blocks[i][j];
				if(refPoint > 0){
					goalRow = (refPoint-1)/dimension;
					goalColumn = (refPoint-1)%dimension;
					diff = diff + (Math.abs(goalRow-i) + Math.abs(goalColumn-j));
				}
			}
		}
		return diff;
	}
	
	// is this board the goal board?
	public boolean isGoal(){
		return (hamming() == 0);
	}
	
	// a board obtained by exchanging two adjacent blocks in the same row
	public Board twin(){
		
	}
	
	// does this board equal y?
	public boolean equals(Object y){
		for(int i = 0; i < dimension; i++){
			for(int j = 0; j < dimension; j++){
				if(blocks[i][j] != ((Board)y).blocks[i][j]){
					return false;
				}
			}
		}
		return true;
	}
	
	// all neighboring boards
	@SuppressWarnings("unchecked")
	public Iterable<Board> neighbors(){
		ArrayList<Board> neighbors = new ArrayList<Board>();
		int row = spaceRow, col = spaceCol;
		Board neighbor = null;
		// upper neighbor
		if(row-1 >= 0){
			int temp = blocks[row-1][col];
			blocks[row-1][col] = blocks[row][col];
			blocks[row][col] = temp;
			neighbor = new Board(blocks);
			neighbors.add(neighbor);
			blocks[row][col] = blocks[row-1][col];
			blocks[row-1][col] = temp;
		}
		
		// below neighbor
		if(row+1 < dimension){
			int temp = blocks[row+1][col];
			blocks[row+1][col] = blocks[row][col];
			blocks[row][col] = temp;
			neighbor = new Board(blocks);
			neighbors.add(neighbor);
			blocks[row][col] = blocks[row+1][col];
			blocks[row+1][col] = temp;
		}
				
		// left neighbor
		if(col-1 >= 0){
			int temp = blocks[row][col-1];
			blocks[row][col-1] = blocks[row][col];
			blocks[row][col] = temp;
			neighbor = new Board(blocks);
			neighbors.add(neighbor);
			blocks[row][col] = blocks[row][col-1];
			blocks[row][col-1] = temp;
		}
		
		//right neighbor
		if(col+1 < dimension){
			int temp = blocks[row][col+1];
			blocks[row][col+1] = blocks[row][col];
			blocks[row][col] = temp;
			neighbor = new Board(blocks);
			neighbors.add(neighbor);
			blocks[row][col] = blocks[row][col+1];
			blocks[row][col+1] = temp;
		}

		
		return (Iterable<Board>) neighbors;
	}
	
	// string representation of the board (in the output format specified below)
	public String toString(){
		String result = "";
		result += dimension + "\r\n";
		for(int i = 0; i < dimension; i++){
			result += " ";
			for(int j = 0; j < dimension; j++){
				result += blocks[i][j] + " ";
			}
			result += "\r\n";
		}
		return result;
	}
}
