//ll

package ser120.ChessProject4;
//import ser120.ChessProject2.Piece;
import java.util.Scanner;

public class Board {
	//stores and handles the board data
  
	//variables
	private int numRows;
	private int numCols;
	private Piece[][] boardData;
	
	//default constructor
	public Board(){
	Player player1 = new Player(0);
	Player player2 = new Player(1);
    this.numRows = 8;
    this.numCols = 8;
    this.boardData = new Piece[numCols][numRows]; //it's coordinates: columns = x, rows = y
	
	boardData[0][0] = new Rook(player1);
	boardData[1][0] = new Knight(player1);
	boardData[2][0] = new Bishop(player1);
	boardData[3][0] = new Queen(player1);
	boardData[4][0] = new King(player1);
	boardData[5][0] = new Bishop(player1);
	boardData[6][0] = new Knight(player1);
	boardData[7][0] = new Rook(player1);
	
	boardData[0][1] = new Pawn(player1);
	boardData[1][1] = new Pawn(player1);
	boardData[2][1] = new Pawn(player1);
	boardData[3][1] = new Pawn(player1);
	boardData[4][1] = new Pawn(player1);
	boardData[5][1] = new Pawn(player1);
	boardData[6][1] = new Pawn(player1);
	boardData[7][1] = new Pawn(player1);
	
	
    boardData[0][6] = new Pawn(player2);
	boardData[1][6] = new Pawn(player2);
	boardData[2][6] = new Pawn(player2);
	boardData[3][6] = new Pawn(player2);
	boardData[4][6] = new Pawn(player2);
	boardData[5][6] = new Pawn(player2);
	boardData[6][6] = new Pawn(player2);
	boardData[7][6] = new Pawn(player2);
	
	boardData[0][7] = new Rook(player2);
	boardData[1][7] = new Knight(player2);
	boardData[2][7] = new Bishop(player2);
	boardData[3][7] = new Queen(player2);
	boardData[4][7] = new King(player2);
	boardData[5][7] = new Bishop(player2);
	boardData[6][7] = new Knight(player2);
	boardData[7][7] = new Rook(player2);
  }

  //constructors w parameters
  //we could make rows and cols variables in the future, but for now the columns and rows will always be 8
  //passing through players as a parameter here because the pieces need to be instantiated with them, the players will be made by the ChessPlayer object and passed through as parameters upon the board's creation
  public Board(/*int initRows, int initCols*/ Player player1, Player player2){
    this.numRows = 8;
    this.numCols = 8;
    this.boardData = new Piece[numCols][numRows]; //it's coordinates: columns = x, rows = y
    
    //potentially more efficient way to fill the board, try again later
    //int col = 0;
    //int row = 0;
	//board[col][row] = new Rook();
	//row++
	
	//fill the board to start with
	boardData[0][0] = new Rook(player1);
	boardData[1][0] = new Knight(player1);
	boardData[2][0] = new Bishop(player1);
	boardData[3][0] = new Queen(player1);
	boardData[4][0] = new King(player1);
	boardData[5][0] = new Bishop(player1);
	boardData[6][0] = new Knight(player1);
	boardData[7][0] = new Rook(player1);
	
	boardData[0][1] = new Pawn(player1);
	boardData[1][1] = new Pawn(player1);
	boardData[2][1] = new Pawn(player1);
	boardData[3][1] = new Pawn(player1);
	boardData[4][1] = new Pawn(player1);
	boardData[5][1] = new Pawn(player1);
	boardData[6][1] = new Pawn(player1);
	boardData[7][1] = new Pawn(player1);
	
	
    boardData[0][6] = new Pawn(player2);
	boardData[1][6] = new Pawn(player2);
	boardData[2][6] = new Pawn(player2);
	boardData[3][6] = new Pawn(player2);
	boardData[4][6] = new Pawn(player2);
	boardData[5][6] = new Pawn(player2);
	boardData[6][6] = new Pawn(player2);
	boardData[7][6] = new Pawn(player2);
	
	boardData[0][7] = new Rook(player2);
	boardData[1][7] = new Knight(player2);
	boardData[2][7] = new Bishop(player2);
	boardData[3][7] = new Queen(player2);
	boardData[4][7] = new King(player2);
	boardData[5][7] = new Bishop(player2);
	boardData[6][7] = new Knight(player2);
	boardData[7][7] = new Rook(player2);
  }

  //methods

	//method to change the board data by moving a piece
	//should only be called after checking the validity of the move!
	public void movePiece(int startCol, int startRow, int endCol, int endRow){
		//puts the piece in a new spot
		boardData[endCol][endRow] = boardData[startCol][startRow];
		//removes the piece from the old spot
		boardData[startCol][startRow] = null;
	}
	
	public void handlePromotion(int col, int row, Scanner myScanner, Player player){
		//if the pawn in question is WHITE and it reaches row 8 (value 0) OR if the pawn is BLACK and reaches row 1 (value 7)
		// prompt user asking what they want to promote it to
		//swap the piece at x,y for a piece of that type
		int team = boardData[col][row].getTeam();
		if (((team == 1) && (row == 0)) || (team == 0) && (row == 1)){
			String userInput = "";
			System.out.println("Your pawn reached the furthest rank! What do you want to promote it to? Type 'r' for rook, 'b' for bishop, 'k' for knight, or anything else for queen: ");
			userInput = myScanner.nextLine();
			if (userInput.equals("r")){
				boardData[col][row] = new Rook(player);
			} else if (userInput.equals("b")){
				boardData[col][row] = new Bishop(player);
			} else if (userInput.equals("k")){
				boardData[col][row] = new Knight(player);
			} else {
				boardData[col][row] = new Queen(player);
			}
		}
		
	}
	
	public String toString(){
		String string = "";
		for (int col = 0; col < numCols; col++){
			//we are in [col] column!
			for (int row = 0; row < numRows; row++){
				//we are in [row] row of [col] column
				if (boardData[col][row] == null){
					string += "none";
				} else {
					string += boardData[col][row].getType();
					string += boardData[col][row].getTeam();
				}
				string += "\n";
			}
		}
		return string;
	}
	
	
	//getters
	public Piece[][] getBoardData(){
		return boardData;
	}
	
	public int getBoardNumCols(){
		return this.numCols;
	}
	
	public int getBoardNumRows(){
		return this.numRows;
	}
	
	//setter
	public void setBoardData(Piece[][] array){
		this.boardData = array;
	}
	
	//findKing();
	//removed baord parameter because this method is called by the board itself, so it can just reference itself
	public int[] findKingSquare(int team){
		//findung the kings positions through coordinates
		int Team = team;
		//System.out.println("player team " + player.getTeam());
			for(int row = 0; row < this.getBoardNumRows();row++){
				for(int col = 0; col < this.getBoardNumCols();col++){
					if (boardData[col][row] != null){
						//System.out.println("checked spot: not null");
						//Piece piece = boardData[row][col];
						//System.out.println("piece type: " + boardData[row][col].getType() + " piece team: " + boardData[row][col].getTeam() + " player team: " + player.getTeam());
						//cannot reference an abstract object, abstract object arent really supposed to exist, so i removed all references to it and continued to reference the piece in question directly from the board's data array
						if(boardData[col][row].getType() == Piece.Type.KING ){
							//System.out.println("is a king");
							//System.out.println("king team " + boardData[row][col].getTeam());
							if(boardData[col][row].getTeam() == Team){
							//has it where the row is at index 0 and col at index 1
								//System.out.println("king found");
								return new int[]{col,row};
							}
						} 
					}
				}
			}
			//if they can't find the king
			//just put a print statement here for testing purposes
			//System.out.println("king not found");
			return null;
	}
	
public boolean isInCheck(int team){
		//System.out.println("findKingSquare called by isInCheck");
		int[] kingsPosition =(findKingSquare(team));
		
		if (kingsPosition != null){
		
			int Team = team;
			int kingCol = kingsPosition[0];
			int kingRow = kingsPosition[1];
			for(int row = 0; row < this.getBoardNumRows(); row++){
					for(int col = 0; col < this.getBoardNumCols();col++){
						Piece piece = boardData[col][row];
						//cannot reference an abstract object, abstract object arent really supposed to exist, so i removed all references to it and continued to reference the piece in question directly from the board's data array
						if(boardData[col][row] != null && boardData[col][row].getTeam() != Team){
						//to see if the kings square that is currently on is underattack
						//I put "this" as the last parameter because it was missing
							if(boardData[col][row].checkMoveValidity(col,row,kingCol,kingRow, this)){
								//System.out.println("isInCheck returns true");
								return true;
							}
						}
					}		
			}
		
		}
		//System.out.println("isInCheck returns false");
		return false;
	}
	
	//make a shallow copy of the board but should be fine
	//if this does not do what it needs to do then we might need to do 
	// a deep copy, but should not be an issue with how our peice class is set up with int and booleans
	public Piece[][] copyBoard1(Piece[][] board){
		Piece[][] copy = new Piece[8][8];
		for(int row = 0; row < this.getBoardNumRows(); row++){
			for(int col = 0; col < this.getBoardNumCols(); col++){
				copy[col][row] = board[col][row];
			}
		}
		return copy;
	}	
	
		public Board copyBoard2(){
		Player tempPlayer1 = new Player(0);
		Player tempPlayer2 = new Player(1);
		Board copy = new Board(tempPlayer1,tempPlayer2);
		for(int row = 0; row < this.getBoardNumRows(); row++){
			for(int col = 0; col < this.getBoardNumCols(); col++){
				copy.boardData[col][row] = this.boardData[col][row];
			}
		}
		return copy;
	}
	
	//determines if the move they have made is legal or not 
	public boolean getLegalMovement(int team){
		//checking if each piece of move is valid or not
		for(int row = 0; row < this.getBoardNumRows(); row++){
			for(int col = 0; col < this.getBoardNumCols();col++){
				Piece piece = boardData[col][row];
				if(boardData[col][row] != null && boardData[col][row].getTeam() == team){
					//for each square on the the board
					for(int newRow = 0;newRow < this.getBoardNumRows();newRow++){
						for(int newCol = 0; newCol < this.getBoardNumCols(); newCol++){
							//if piece checkmovevalidity
						//Piece target = boardData[newRow][newCol];
							//creates a tempoary board
							//Player tempPlayer1 = new Player(0);
							//Player tempPlayer2 = new Player(1);
							Board tempBoard = copyBoard2();					
						//cehcks if the peice movement is valid
							if (tempBoard.boardData[col][row] != null){
								if(tempBoard.boardData[col][row].checkMoveValidity(col,row,newCol,newRow,tempBoard)){
							//-Simulate Move to see if the move they make is legal//
							/* og code with the array
								tempBoard[newRow][newCol] = tempBoard[row][col];
								tempBoard[row][col] = null;
								*/
								//using the movepiece method
									tempBoard.movePiece(col,row,newCol,newRow);
								
								//if the king is not in check
								//System.out.println("tempBoard.isInCheck called by getLegalMovement");
									if(tempBoard.isInCheck(team) == false){
										return true;
									}
								} 
							}
						} 
					}
				}
			}
		}
		//that means king is in check
		return false;
	}
	

	public boolean checkForCheckmate(int team){
		//System.out.println("isInCheck called by CheckForCheckmate");
		if(!isInCheck(team)){
			//no check
			return false;
		}
		//System.out.println("getLegalMovement called by checkForCheckmate");
		if(getLegalMovement(team) == false){
			return true;
		}
		//found no legal movement to allow you to get out of check
		return false;
	}


}
