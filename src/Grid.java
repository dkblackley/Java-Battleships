import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * 
 */

/**
 * This class will hold the grid that the
 * player will play the Battleship game 
 * on, it will also be Responsible 
 * for loading and saving
 * the grid to a file.
 * Compiled and run on Fedora Linux 26 Workstation edition
 * 
 * @author Daniel Kevin Blackley
 *
 */
public class Grid {

	
	private int[][] theGrid;
	private static int gridx, gridy;	//static default grid size at i will be the same for all grids
	private static int numOfShips;		//static number of ships as it will be the same for all grids
	
	/**
	 * 
	 */
	public Grid() {
		// TODO Auto-generated constructor stub
		theGrid = new int [0][0];	//default grid size
		numOfShips = 10;			//default number of ships
		gridx = gridy = 10;        //default grid size
	}
	
	/**
	 * Main Method used to start the program
	 * 
	 * @param args not used
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		boolean playerPlaying = true;	//variable used to quit the menu
		
		while (playerPlaying) {

			System.out.println("Please Choose what you would like to do:");
			System.out.println("Input 0 to practice against the computer");
			System.out.println("Input 1 to play against the computer");
			System.out.println("Input 2 to play 2 player");
			System.out.println("Input 3 to Load Game");
			System.out.println("Input 4 to quit");
		
		
			playerPlaying = menu();
		}
		
		System.out.println("Thanks for playing!!");
		
		
		
	}
	
	/*
	 * Fairly basic menu to decide what the user will want to do
	 * @return boolean to decide whether the player has finished playing or not
	 */
	
	static public boolean menu() {
		boolean gamePlaying = true;
		
		Scanner s1 = new Scanner(System.in); //holds the users input
		
		int input = s1.nextInt();
		
		Grid game = new Grid();  //set a game up to load the other methods in the game
		
		if (input == 0) {
			
			game.setTheGridSize(new int[10][10]);
			game.practiceAgainstComp();
			
		} else if (input == 1) {
			
			Grid compgame, playergame;		//load other grids to be used by the player and the computer
			
			compgame = playergame = new Grid();
			compgame.setTheGridSize(new int [gridx][gridy]);	//sets grid sizes
			playergame.setTheGridSize(new int[gridx][gridy]);
			
			game.playAgainstComp(compgame, playergame);	       //passes grids to the correct method
			
		} else if (input ==2) {
			
			Grid player1game, playergame;          
			
			player1game = playergame = new Grid();
			player1game.setTheGridSize(new int [gridx][gridy]);
			playergame.setTheGridSize(new int[gridx][gridy]);
			
			game.playAgainstPlayer(player1game, playergame);
			
		} else if (input ==3) {
			
			System.out.println("press 0 to load practice against comp, 1 to load game against comp");
			System.out.print("2, to load game against player");
			load();
			
		} else if (input ==4) {
			
		gamePlaying = false;
		
		}
		
		return gamePlaying;
		
	}
	
	/*
	 * asks the player for the filename to be passed to the loadgame method so it knows which file to open
	 * then sets the gridsizes to the loaded file and calls the method using the loaded files
	 */
	public static void load() {
		
		Scanner s2 = new Scanner(System.in);
		
		int input = s2.nextInt();
		
		if (input == 0) {
			Scanner s1 = new Scanner(System.in);
			
			Grid game = new Grid();					//sets the grid to be used by the player
			game.setTheGridSize(new int[gridx][gridy]);

			
			System.out.println("What would save would you like to load?");
			String filename = s1.nextLine();
			
			
			game.theGrid = game.loadTheBoard(filename + "c"); //adds "c" as that is what is added when saving a practice file
			
			game.practiceAgainstComp();		//calls the method using game object as there is only one board required here.
			
		} else if (input == 1) {
			
			Scanner s1 = new Scanner(System.in);
			Grid playergame = new Grid();		
			Grid compgame = new Grid();
			compgame.setTheGridSize(new int[gridx][gridy]);
			int[][] temp = new int[gridx][gridy];
			
			System.out.println("What would save would you like to load?");
			String filename = s1.nextLine();
			
			
			compgame.theGrid = compgame.loadTheBoard(filename + "c1");
			playergame.theGrid = playergame.loadTheBoard(filename + "p");
			
			compgame.playAgainstComp(compgame, playergame);		//passes the correct games to the correct methods
			
		} else if (input == 2) {
			
			Scanner s1 = new Scanner(System.in);
			Grid playergame = new Grid();
			Grid player2game = new Grid();
			player2game.setTheGridSize(new int[10][10]);
			int[][] temp = new int[10][10];
			
			System.out.println("What would save would you like to load?");
			String filename = s1.nextLine();
			
			
			player2game.theGrid = player2game.loadTheBoard(filename + "p2");
			playergame.theGrid = playergame.loadTheBoard(filename + "p1");
			
			player2game.playAgainstPlayer(player2game, playergame);
		}
		
	}
	
	/*
	 * allows the number of ships to be modified
	 * @param newShips amount of new ships
	 * 
	 */
	public void setNumOfShips(int newShips) {
		this.numOfShips = newShips;
	}
	
	
	/*
	 * allows the user to play against the computer,
	 * sets up each board to have nine ships through use of
	 * a for loop then asks the computer and the player
	 * to fire in turn at each others boards while checking to see if ships are still
	 * present on either board
	 * 
	 * @param compgame passes in the computer grif
	 * @param playergame passes in the players game
	 */
	public void playAgainstComp(Grid compgame, Grid playergame) {
		
		
		System.out.println("Input -1 Save your game");
		

		userInput missile = new userInput();	//missile class deals with firing shots at each others boards
		Computer c = new Computer();			//compute to deal with random number generation
		int[] playerShot;						//holds players shot at the board
		int i = 0;
		
		for(i = 0; i < compgame.numOfShips; ++i) {		//sets up 9 ships at random locations across the board of varying sizes between 1 and 4
			if (i < 4) {
				c.compShipLocation(compgame, 1);
			}
			else if (i < 7) {
				c.compShipLocation(compgame, 2);
			}
			else if (i < 9) {
				c.compShipLocation(compgame, 3);
			}
			else if (i == 9) {
				c.compShipLocation(compgame, 4);
			} else {
				c.compShipLocation(compgame, 1);
			}
		}
		
		for(i = 0; i < playergame.numOfShips; ++i) { 		//allows the player to place nine ships on their board
			
			playergame.displayGrid(playergame.theGrid);
			
			if (i < 4) {
				missile.playerShipLocation(playergame, 1);
			}
			else if (i < 7) {
				missile.playerShipLocation(playergame, 2);
			}
			else if (i < 9) {
				missile.playerShipLocation(playergame, 3);
			}
			else if (i == 9) {
				missile.playerShipLocation(playergame, 4);
			} else {
				missile.playerShipLocation(playergame, 1);
			}
		}
		
		
		while (playergame.shipsNotSunk() || compgame.shipsNotSunk()) {		//check if each board still has ships present
			
			compgame.displayGrid(compgame.theGrid);		//display computer grid to allow player to shoot at
			
			playerShot = missile.setMissile(playergame);	//hold player shot
				
			if (playerShot[0] == -1 || playerShot[1] == -1) {	//if player wants to save the game
				
				
				Scanner s1 = new Scanner(System.in);
				
				System.out.println("What would you like to call your savegame?");
				String filename = s1.nextLine();
				
				compgame.saveTheBoard(theGrid, filename + "c1");		//calls save game for both boards with correct filenames
				playergame.saveTheBoard(theGrid, filename + "p");
			}
			playergame.checkPlayerShot(playerShot);		//check if player has hit a ship
			
			
			playerShot = c.compFiresAShot(compgame);		//let the computer take a shot
			
			compgame.checkPlayerShot(playerShot);		//check if computer has hit
		}
		
		System.out.println("Winner found, returning to menu"); //if there is a winner return to menu
		
	}
	
	
	/*
	 * Set up a player vs player game using similar strategies as seen in playervsvomp
	 * but using 2 player boards that shoot at each other instead
	 * @param playergame rid to hold first player game
	 * @param player2game Gird to hold player 2's game
	 */
	public void playAgainstPlayer(Grid playergame, Grid player2game) {
		
		
		System.out.println("Input -1 Save your game");
		

		userInput missile = new userInput();

		int[] playerShot;
		int i = 0;
		
		for(i = 0; i < player2game.numOfShips; ++i) {		//ask player to to set 9 ships between size 1-4
			
			player2game.displayGrid(player2game.theGrid);
			
			if (i < 4) {
				missile.playerShipLocation(player2game, 1);
			}
			else if (i < 7) {
				missile.playerShipLocation(player2game, 2);
			}
			else if (i < 9) {
				missile.playerShipLocation(player2game, 3);
			}
			else if (i == 9) {
				missile.playerShipLocation(player2game, 4);
			} else {
				missile.playerShipLocation(player2game, 1);
			}
		}

		
		for(i = 0; i < playergame.numOfShips; ++i) {		//ask player 1 to set up 9 ships between size 1-4
			
			playergame.displayGrid(playergame.theGrid);
			
			if (i < 4) {
				missile.playerShipLocation(playergame, 1);
			}
			else if (i < 7) {
				missile.playerShipLocation(playergame, 2);
			}
			else if (i < 9) {
				missile.playerShipLocation(playergame, 3);
			}
			else if (i == 9) {
				missile.playerShipLocation(playergame, 4);
			} else {
				missile.playerShipLocation(playergame, 1);
			}
		}
		
		
		while (playergame.shipsNotSunk() || player2game.shipsNotSunk()) {	//while each board still has ships on it
			
			player2game.displayGrid(player2game.theGrid);		//display player2's grid
			
			playerShot = missile.setMissile(playergame);		//player 1 takes a shot
				
			if (playerShot[0] == -1 || playerShot[1] == -1) {		//if player wants to save game
				
				
				Scanner s1 = new Scanner(System.in);
				
				System.out.println("What would you like to call your savegame?");
				String filename = s1.nextLine();
				
				player2game.saveTheBoard(theGrid, filename + "p2");		//attach the correct filename
				playergame.saveTheBoard(theGrid, filename + "p1");
			}
			
			playergame.checkPlayerShot(playerShot);		//check if player1 hit
			
			playergame.displayGrid(playergame.theGrid);		//display player1's board for player 2
			
			playerShot = missile.setMissile(player2game);		//let player 2 shoot
				
			if (playerShot[0] == -1 || playerShot[1] == -1) {		//if player 2 wants to save
				
				Scanner s1 = new Scanner(System.in);
				
				System.out.println("What would you like to call your savegame?");
				String filename = s1.nextLine();
				
				player2game.saveTheBoard(theGrid, filename + "p2");
				playergame.saveTheBoard(theGrid, filename + "p1");
			}
			
			player2game.checkPlayerShot(playerShot);		//check if player 2 has hit
		}
		
		System.out.println("Winner found, returning to menu");
		
	}
		
	
	/*
	 * basic method where the computer places some ships
	 * and the player will attempt to destroy them
	 * it is impossible to lose this
	 */
	public void practiceAgainstComp() {
		
		
		System.out.println("Input -1 Save your game");
		
		userInput missile = new userInput();
		Computer c = new Computer();
		int[] playerShot;
		
		
		for(int i = 0; i < this.numOfShips; ++i) {		//computer randomly places 9 ships
			if (i < 4) {
				c.compShipLocation(this, 1);
			}
			else if (i < 7) {
				c.compShipLocation(this, 2);
			}
			else if (i < 9) {
				c.compShipLocation(this, 3);
			}
			else if (i == 9) {
				c.compShipLocation(this, 4);
			} else {
				c.compShipLocation(this, 1);
			}
		
		
		}
		
		while (this.shipsNotSunk()) {		//while computer still has ships on the board
			
			this.displayGrid(this.theGrid);
			
			playerShot = missile.setMissile(this);		//let player shoot
			
			if (playerShot[0] == -1 || playerShot[1] == -1) {	//if player wants to save
				
				Scanner s1 = new Scanner(System.in);
				
				System.out.println("What would you like to call your savegame?");
				String filename = s1.nextLine();
				
				this.saveTheBoard(theGrid, filename);
			}
			this.checkPlayerShot(playerShot);		//check if player has hit the board
			
		}
	}
	
	

	/**
	 * method gets the grid
	 * @return the theGrid get the grid
	 */
	public int[][] getTheGrid() {
		return theGrid;
	}
	/*
	 * changes the values of the integers on the grid
	 *@param x x-coordinate
	 *@param y y-coordinate
	 *@param input the value that is to be set
	 */
	public void changeGridInfor(int x, int y, int input) {
		this.theGrid[x][y] = input;
	}
	
	public void setPlayerGrid() {
		for (int i = 0; i < theGrid.length; ++i) {
			for (int c = 0; c < theGrid[i].length; ++c) {
				theGrid[i][c] = 0;
			}
		}
	}

	/**
	 * @param theGrid the value of the grid that you want to set
	 */
	public void setTheGridSize(int[][] theGrid) {
		
		this.theGrid = theGrid;
	}
	
	/*
	 * A method that compares the area that the user has
	 * shot with what is being stored, if a 3 is being stored
	 * then a hidden ship has been found and should be displayed to the user
	 * @param playerShot the value that the player has shot at
	 */
	
	public boolean checkPlayerShot(int[] playerShot) {
		boolean hit = false;		//value that holds wether the user has hit
		
		if (theGrid[playerShot[0]][playerShot[1]] == 3) {		//if player has guessed the location of a hidden ship
			playerHit(playerShot);	
			System.out.println("Hit!!");
			hit = true;
			
		}
		else {
			playerMiss(playerShot);
			System.out.println("Miss!!");
		}
		return hit;
	}
	
	/*
	 * method that check the board to see if there are any hidden
	 * ships left and if there are then return
	 * true so that the game continues
	 * 
	 * @return shipsStillAlive the value that will be changed if ships are found
	 */
	public boolean shipsNotSunk() {		
		
		boolean shipsStillAlive = false;		//sets the return value
		
		for (int i = 0; i < theGrid.length; ++i) {			//Cycle through the grid
			for (int c = 0; c < theGrid[i].length; ++c) {
				if (theGrid[i][c] == 3) {		//if there are still ships
					shipsStillAlive = true;
				}
			}
		}
		return shipsStillAlive;
		
	}
	
	/*
	 * change grid to represent that the player has found a ship
	 * @param location the location that the user had found the ship
	 */
	public void playerHit(int[] location) {
		this.changeGridInfor(location[0], location[1], 1);
	}
	
	/*
	 * player has guessed incorrectly, so the grid should change to inform them
	 * 
	 * @param location the location the player incorrectly guessed
	 */
	public void playerMiss(int[] location) {
		this.changeGridInfor(location[0], location[1], 2);
	}
	
	/*
	 * Cycles through the grid and prints out each value that isn't
	 * a hidden ship
	 */
	public void displayGrid(int[][] board) {
		System.out.print("  ");
		
		for (int i = 0;i < board[0].length; ++i ) {
			System.out.print(i + " ");
		}
		
		System.out.println();
		
		for(int row = 0; row < board.length; row++) {
			System.out.print(row + " ");
			
			
			for(int column = 0; column < board[row].length; column++) {
				
				if(board[row][column] == 3) {		//if the value is a hidden ship, keep it hidden
					System.out.print(0 + " ");
				}
				
				else {
					System.out.print(board[row][column] + " ");
				}
			}
			System.out.print("\n");
		}
		
		
	}
	
	
	
	/*
	 * method used to save the board to a .txt file
	 * 
	 * @param board the board to be saved
	 * @param filename the name of the saved file
	 */
	public void saveTheBoard(int [][] board, String filename) {
		
		//initialise objects and declare variables
		
		PrintWriter writer1 = null;
		FileOutputStream output1 = null;
		
			
		try {		//try to use printwriter and fileoutputstream
			output1 = new FileOutputStream(filename + ".txt");		//set correct filetype
			writer1 = new PrintWriter(output1);
				
			for (int row = 0; row < board.length; ++row) { 	//cycle through the drid
				for (int column = 0; column < board[row].length; ++column) {
					
					writer1.print(board[row][column]);		//save each line to the file
				}	
				writer1.print("\n");
			}
			writer1.close();    //close writer
			output1.close();    //close output stream
			System.out.println("Save Successful");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error making a file: " + e);			
		}
	}
	
	/*
	 * loads the grid from a file and returns it to the user
	 * 
	 * @param filename the correct filename for the user to use
	 * @return returns the grid
	 */
	public int[][] loadTheBoard(String filename) {
		
		//initliase all objects and variables
		FileReader fileReader;
		BufferedReader bufferedReader;
		int [][] board;
		String[] SplitLine;
		
		try { 
			
			
			fileReader = new FileReader(filename + ".txt"); //adds te correct filetype
			bufferedReader = new BufferedReader(fileReader); 
			
			String lineReading = bufferedReader.readLine();		//gets the next line
			
			board = new int[10] [10];
					
			for (int i = 0; lineReading != null; i++) {		//while file has content
				
				SplitLine = lineReading.split("");
				board[i] = convertToIntArray(SplitLine);
				lineReading = bufferedReader.readLine();   //gets next line in file
			}
			
		fileReader.close();      //close file reader
		bufferedReader.close();  //closes the file stream
		
		
		} catch (Exception e) {
			System.out.println("Error reading from a file in method openAFile; " + e);
			board = null;
		}
		return board;
	}
	
	
	/*
	 * converts a text file into integers
	 */
	public int[] convertToIntArray(String [] stringLine) {
		
		int arraySize = stringLine.length;
		int[] convertedArray = new int[arraySize];
		
		for (int i = 0; i < arraySize; ++i) {	//for each line in the file
			convertedArray[i] = Integer.parseInt(stringLine[i]);	//convert it to a int
		}
		return convertedArray;
	}
}

