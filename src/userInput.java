import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * @author Daniel Blackley
 *This class is used to handle the user input, such as
 * the placement of ships or the firing of shots
 */
public class userInput {
	
	/**
	 * 
	 */
	public userInput() {
		// TODO Auto-generated constructor stub
	}
	
	
	/*
	 * prompts the user for the location to fire at the board
	 * @param Board the board that the player will fire at
	 * @return the location of the shot
	 */
	public int[] setMissile(Grid Board) {
		
		int [] location = new int[2];
		
		
		System.out.println("Where would you like to fire?");
		
		System.out.println("Input Y-Coordinate");
		
		location[0] = validateInput(Board);
		
		System.out.println("Input X-Coordinate");
		
		location[1] = validateInput(Board);
		
		return location;
	}
	
	/*
	 * Validates that te user entered a integer correctly
	 * @param Board used to find the size of the board so that the method knows the size
	 * @return returns the value if valid
	 */
	private int validateInput(Grid Board) {
		int value = 0;
		boolean valid = false;
		int[][] gameBoard = Board.getTheGrid();
		
		while (!valid) {		//until the user enters something valid
			
			Scanner s1 = new Scanner(System.in);
			
			try {
				value = s1.nextInt();
				
				if (value == -1) {
					return value;
				}
				
				else if (value < 0 || value > gameBoard.length - 1) {		//mke sure the value is within the limits of the board
					System.out.println("Value too big/small please retry");
					
				} else {
					
					valid = true;
				}
				
			} catch (Exception e) {
				
				if (e instanceof InputMismatchException) {
					System.out.println("Please enter a valid Integer");
				}
				else {
					System.out.println("Error validating input: " + e);
				}
			}
			
		}
		return value;
	}
	
	/*
	 * method for placing ships on the board
	 * asks the player to choose the verticallity and then places the ship
	 * @param Board the board the player will be placing ships on
	 * @param shipSize the size of the ship
	 */
	public void playerShipLocation(Grid Board, int shipSize) {
		
		System.out.println("Please input 0 to place your ship horizontally and 1 to place vertically");
		System.out.println("Input -1 to save the game");
		
		int verticallity = -3;	//set verticallity to an originally impossible value

		
		Scanner s1 = new Scanner(System.in);
			
		verticallity = s1.nextInt();
		if (verticallity == 1) {		//if player wants ship horizontal
			
			System.out.println("Please input x value");
			int x = validateInput(Board);
			
			System.out.println("Please input y value");
			int y = validateInput(Board);
			
			System.out.println("Ship placed");
				
			 for (int i = 0; i < shipSize; ++i) {		//place ship fully horizontally on the board
				 Board.changeGridInfor(x + i, y, 3);
			 }
				
		} else if (verticallity == 0) {
			System.out.println("Please input x value");
			
			int x = validateInput(Board);
			System.out.println("Please input y value");
			
			int y = validateInput(Board);
			System.out.println("Ship placed");
				
			for (int i = 0; i < shipSize; ++i) {		//place ship fully vertically on the board
				Board.changeGridInfor(x, y + i, 3);
			}
		}
		
		
	}

}
