//Chess Project 2
//Knight.java

package ser120.ChessProject3;


class Knight extends Piece {
	
	
	//variables (add if needed)
	
	//constructors
	public Knight(Player initPlayer){
		super(initPlayer);
	}
	
	//methods
	public boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board){
		//check move validity for this piece, how does this piece move?
		//L
		if( Math.abs(endRow - startRow) != 2 || Math.abs(endCol - startCol) !=1 ){
			return false;
		} else {
			return true;
		}
	}
	
	public int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board){
		// instead of filling this array with 0's and mapping the path of 1's, 
		// just fill with zeros
		// check if the spot where it lands holds a piece on the same team
		// if it does, then put a 1 on the spot where it lands
		// otherwise don't put any 1's
		//this should work with the pathIsClear method you write in ChessPlayer
		
		int [][] pathArray = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		//putting the boardData array here to reference
		Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
		
		//fill up array with zeros to start
		for (int row = 0; row < board.getBoardNumRows(); row++){
			//we are in [row] row!
			for (int col = 0; col < board.getBoardNumCols(); col++){
				//we are in [col] column or [row] row
				pathArray[col][row] = 0;
			}
		}
		
		if (boardData[endCol][endRow] != null){ //if there is a piece in the landing spot 
			if (boardData[endCol][endRow].getTeam() == this.team){ //if that piece is on the same team
				pathArray[endCol][endRow] = 1;
			} else { //if that piece is on the opposite team
				//placeholder code, it was already zero but this just verifies
				pathArray[endCol][endRow] = 0;
			}
		} else { //if the landing spot is empty
			//placeholder code, it was already zero but this just verifies
			pathArray[endCol][endRow] = 0;
		}
		
		return pathArray;
	}
	
	//returns a string to print, represents the piece on the board, helper method for the printer, 
	public String getVisual(){
		if (this.team == 0){
			return "BKN";
		} else {
			return "WKN";
		}
	}
	
	//returns a string that says what type of piece it is
	public String getType(){
		return "knight";
	}
	
}
