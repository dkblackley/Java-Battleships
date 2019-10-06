import java.util.Random;

/*
 *@author Daniel Blackley
 *
 *This class is used to handle random number generation and other things that the computer will do
 */

public class Computer {
	
	public Random rn;		//used to generate randomness
	
	public Computer() {
		// TODO Auto-generated constructor stub
		rn = new Random();
	}
	
	/*
	 * places ships randomly on the computers board
	 * @param Board holds the computers board
	 * @param shipSize hold the size of the ship being placed
	 */
	public void compShipLocation(Grid Board, int shipSize) {	
		
		int verticality = rn.nextInt(2);		//randomly decide if vertical
		int[] [] grid = Board.getTheGrid();
		
		
		if (verticality == 1) {
			
			 int x = rn.nextInt(grid[0].length - shipSize + 1);		//randomly decide the values of where the ship is placed on the relean axis
			 int y = rn.nextInt(grid.length);
			
			 for (int i = 0; i < shipSize; ++i) {
				 Board.changeGridInfor(x + i, y, 3);		//change board to hold hidden ship
			 }
			
		} else {
			
			int x = rn.nextInt(grid[0].length);
			 int y = rn.nextInt(grid.length - shipSize + 1);
			
			 for (int i = 0; i < shipSize; ++i) {
				 Board.changeGridInfor(x, y + i, 3);
			 }
		}
		
	}
	/*
	 * Computer fires randomly at the players board
	 * @param Board the players board that the method holds
	 * @return the location the hip fired at
	 */
	public int[] compFiresAShot(Grid Board) {
		
		int [] location = new int[2];
		
		
		location[0] = rn.nextInt(Board.getTheGrid().length);
		
		location[1] = rn.nextInt(Board.getTheGrid().length);
		
		return location;
		
		
	}
	


}


