//Chess Project 2
//ChessPlayer.java


//import ser120.ChessProject2.Board;

package ser120.ChessProject3;
import java.util.Scanner;
import java.util.Arrays;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class ChessPlayer {
	//goes through the steps of playing the chess game, takes and translates user input, taking turns, playing rounds, checking win condition
	
	//variables
	public Board board;
	public VisualOutput printer;
	public Player player1;
	public Player player2;
	public boolean stillPlaying;
	public int turn;
	//public ChessReplayer myChessReplayer;
	public Path path;
	
	//constructors
	public ChessPlayer(){
		this.stillPlaying = true;
		this.player1 = new Player(0);
		this.player2 = new Player(1);
		this.printer = new VisualOutput();
		this.board = new Board(player1,player2);
		this.turn = 0;
		//this.myChessReplayer = initChessPlayer;
		this.path = null;
	}
	
	//methods
	
	//runs the game, is a loop that plays rounds over and over as long as the game is still going (no one has won)
	//pass in a scanner from the game manager to take input later
	public void playChess(Scanner myScanner){
		System.out.println("Starting a chess game...");
		System.out.println("INPUT 'exit' AT ANY TIME TO SAVE AND QUIT");
		stillPlaying = true;
		
			while(stillPlaying){
				playRound(myScanner);
			}
			System.out.println("Closing chess game...");
		
	}
	
	//each round, player 1 takes a turn, then player 2 takes a turn. if someone wins, the game ends
	//this is the method that calls check for checkmate. If that returns true, some kind of "end message" method shoudl run
	//which would determine and announce the winner, or maybe this could be divided into more separate methods
	public void playRound(Scanner myScanner){
		printer.printBoard(board);
		takeTurn(player2, myScanner);
		//System.out.println("checkForCheckmate(player1) called by playRound");
		if (board.checkForCheckmate(player1)){
			printer.printBoard(board);
			stillPlaying = false;
			if (player1.getTeam()==0){
				System.out.println("Black loses!");
			} else {
				System.out.println("Whites loses!");
			}
		} else {
			if(stillPlaying){
				printer.printBoard(board);
				takeTurn(player1, myScanner);
				//System.out.println("checkForCheckmate(player2) called by playRound");
				if (board.checkForCheckmate(player2)){
					stillPlaying = false;
					printer.printBoard(board);
					if (player2.getTeam()==0){
						System.out.println("Black loses!");
					} else {
						System.out.println("Whites loses!");
					}
				}
			}
		}
	}
	
	//a piece of playing a round is players taking turns
	public void takeTurn(Player player, Scanner myScanner){
		//determines which team is going rn
		int team = player.getTeam();
		if (team == 0){
			System.out.println("Black's Turn!");
		} else {
			System.out.println("Whites's Turn!");
		}
		
		//some variabels to store user input
		int startCol = 0;
		int startRow = 0;
		int endCol = 0;
		int endRow = 0;
		Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
		
		//loops until a valid move is aquired
		boolean moveIsValid = false;
		while (!moveIsValid){
			
			System.out.println("Which piece would you like to move?");
			startCol = promptUserCol(myScanner);
			
			//add exit~~
			if(startCol == -1){
				stillPlaying = false;
				moveIsValid = true;
			} else {
				startRow = promptUserRow(myScanner);
				if(startRow == -1){
					stillPlaying = false;
					moveIsValid = true;
				}
			}
			
			if(stillPlaying){
			
			//checks that there is a piece in the chosen tile
			if (boardData[startCol][startRow] != null){
				//checks that the selected piece belongs to the player whose turn it currently is
				if (boardData[startCol][startRow].getTeam() == player.getTeam()){
					System.out.println("Where would you like to move it?");
					endCol = promptUserCol(myScanner);
					endRow = promptUserRow(myScanner);
				
				//checks that the selected end location is different than the start location
					if (startCol != endCol || startRow != endRow){
				
						//checks that this kind of piece can make this move
						if (boardData[startCol][startRow].checkMoveValidity(startCol,startRow,endCol,endRow,board)){
							
							if(pathIsClear(startCol,startRow,endCol,endRow,board)){
								//if the path is clear (no pieces in its path and if it lands on a piece it is capturable) the move is valid
								moveIsValid = true;
							} else {
								//if this piece cannot make that move, the loop must repeat
								//this code is tehcnically unecessary, but it verifies that the loop must repeat
								moveIsValid = false;
								System.out.println("That was not a valid move. (there is a piece in the way)");
							}
						} else {
							//if this piece cannot make that move, the loop must repeat
							//this code is tehcnically unecessary, but it verifies that the loop must repeat
							moveIsValid = false;
							System.out.println("That was not a valid move. (this piece cannot move that way)");
						}
						
					} else {
						moveIsValid = false;
						System.out.println("That was not a valid move. (you cannot stay put)");
					}
					
				} else {
					//if this piece doesn't belong to the player, the loop must repeat
					//this code is tehcnically unecessary, but it verifies that the loop must repeat
					moveIsValid = false;
					System.out.println("That was not a valid move.(this is not your piece)");
				}
				
			} else {
				//if there is no piece there to begin with, the loop must repeat
				//this code is tehcnically unecessary, but it verifies that the loop must repeat
				moveIsValid = false;
				System.out.println("That was not a valid move.(there is no piece there)");
			}
			
		}
			
		}
		
		if (stillPlaying){
		
		board.movePiece(startCol,startRow,endCol,endRow);
		
		//here, other things might happen when the piece moves, such as capturing, but I don't know what that is yet
		//actually, here I am going to check if the pawn must be promoted... don't ask why idk
		if (boardData[endCol][endRow].getType().equals("pawn")){
			board.handlePromotion(endCol,endRow,myScanner, player);
		}
		//iterate what turn it is for the purpose of...
		turn++;
		}
		
	}
	
	//these two methods (promptUserCol and promptUserRow) should be combined into one at some point, they are similar enough that it is doable with w=some parameters and/or conditional statements
	public int promptUserCol(Scanner myScanner){
		//integer to return
		int chosenCol = 0;
		//a string to hold user input
		String userInput = "";
		// a boolean to make a loop
		boolean isRealCoord = false;
		
		
		//loops until a real coordinate that exists on the board is given
		while (!isRealCoord){
			System.out.println("Enter a column: ");
			userInput = myScanner.nextLine();
			
			//adding exit~~
			
			if(userInput.equals("exit")){
				return -1;
			}
			
			//end exit~~
			
			chosenCol = convertCol(userInput); 
			if (chosenCol == -1){
			//if it was not a valid entry, convertCol should return null
			//loop must repeat
				System.out.println("That is not a coordinate on the board, please enter something else.");
				isRealCoord = false;
			} else {
			//if chosenCol does get given a value by convertCol, the loop can end
				isRealCoord = true;
			}
		}
		return chosenCol;
		
	}
	
	public int promptUserRow(Scanner myScanner){
		//integer to return
		int chosenRow = 0;
		//a string to hold user input
		String userInput = "";
		// a boolean to make a loop
		boolean isRealCoord = false;
		
		//loops until a real coordinate that exists on the board is given
		while (!isRealCoord){
			System.out.println("Enter a row: ");
			userInput = myScanner.nextLine();
			
			if(userInput.equals("exit")){
				return -1;
			}
			
			chosenRow = convertRow(userInput); 
			if (chosenRow == -1){
			//if it was not a valid entry, convertCol should return null
			//loop must repeat
				System.out.println("That is not a coordinate on the board, please enter something else.");
				isRealCoord = false;
			} else {
			//if chosenCol does get given a value by convertCol, the loop can end
				isRealCoord = true;
			}
		}
		return chosenRow;
		
	}
	
	public boolean pathIsClear(int startCol, int startRow, int endCol, int endRow, Board board){
		//create an array of integers (width and height same as board)
		//set the array equal to board[startCol][startRow].drawPath(startCol,startRow,endCol,endRow,board)
		
		//putting the boardData array here to reference
		Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
		
		//making the path array with drawPath
		int [][] pathArr = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		pathArr = boardData[startCol][startRow].drawPath(startCol,startRow,endCol,endRow,board);
		
		//start with this boolean
		boolean clearPath = true;
		
		//now use nested for loops to go through the whole path array
		
		for (int col = 0; col < board.getBoardNumCols(); col++){
			//we are in [col] column!
			for (int row = 0; row < board.getBoardNumRows(); row++){
				//we are in [row] row of [col] column
				if (pathArr[col][row] == 1 ){ //if this space is part of the path
					if(boardData[col][row] != null){ //if there is a piece on this space
						if(col == endCol && row == endRow){ //if this space is the landing space
							if(boardData[endCol][endRow].getTeam() == boardData[startCol][startRow].getTeam()){ //if the piece on the landing space is the same team as the piece that is moving
								clearPath = false; //not allowed
							} else { //if the piece occupying the landing space is on the opposite team as the piece that is moving
								clearPath = true; //this IS allowed, it captures
							}
						} else { //if this space is NOT the landing space
							clearPath = false; //return false! something is in the way of the path, this move cannot happen!
						}
					} else {//if there is NO PIECE on this space
						//do nothing. the space is empty, so there is nothing in the way yet.
					}
				} else { // if this space is NOT part of the path
					//do nothing. this space is not part of the path, so there is no reason to check it
				}
			}
		}
		
		//whereever pathArray[x][y] == 1, check if there board[x][y] != null
		//if it's not null, 
			// check if boardData[endCol][endRow].getTeam() == boardData[startCol][startRow].getTeam()
			// if this is true, then the piece is trying to land on a friendly piece, which is not allowed
				// return false
			//otherwise, this is not true, so it's capturing an enemy, which is allowed
			//return true
		//otherwise, it is null, empty spot 
		//return true
		
		//up in take turn, have an if statement
		// if (pathIsClear())
		// carry on with further checks
		// else... 
		//moveIsValid = false
		return clearPath;
	}
	
	/*
	//checkmate method got moved to the board class	
	public boolean checkForCheckmate(){
		return false;
	}*/

	//simple method to convert user input into usable coordinates, should make more reliable and flexible in the future but this works for now
	public int convertCol(String string){
		switch (string) {
		case "a":
			return 0;
		case "b":
			return 1;
		case "c":
			return 2;
		case "d":
			return 3;
		case "e":
			return 4;
		case "f":
			return 5;
		case "g":
			return 6;
		case "h":
			return 7;
		default:
			return -1;
		}
	}
	
	//simple method to convert user input into usable coordinates, should make more reliable and flexible in the future but this works for now
	public int convertRow(String string){
		switch (string) {
		case "8":
			return 0;
		case "7":
			return 1;
		case "6":
			return 2;
		case "5":
			return 3;
		case "4":
			return 4;
		case "3":
			return 5;
		case "2":
			return 6;
		case "1":
			return 7;
		default:
			return -1;
		}
  }
	
	
}
