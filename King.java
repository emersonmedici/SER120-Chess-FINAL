//Chess Project 2
//King.java

package ser120.ChessProject3;

class King extends Piece {
	
	//variables (add if needed)
	
	//constructors
	public King(Player initPlayer){
		super(initPlayer);
	}
	
	//methods
	public boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board){
		//check move validity for this piece, how does this piece move?
		
		if ((endCol > startCol+1) || (endCol < startCol-1) || (endRow > startRow+1) || (endRow < endRow-1)){
			return false;
		} else {
			return true;
		}	
	}
	
	public int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board){
		int [][] pathArray = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		//king moves only one space in any direction, so there's not a "path," just a landing spot
		//fill up array with zeros to start
		for (int row = 0; row < board.getBoardNumRows(); row++){
			//we are in [row] row!
			for (int col = 0; col < board.getBoardNumCols(); col++){
				//we are in [col] column or [row] row
				pathArray[col][row] = 0;
			}
		}
		
		//puts a 1 in the landing spot
		pathArray[endCol][endRow] = 1;
		
		return pathArray;
	}
	
	//returns a string to print, represents the piece on the board, helper method for the printer, 
	public String getVisual(){
		if (this.team == 0){
			return "BKI";
		} else {
			return "WKI";
		}
	}
	
	//returns a string that says what type of piece it is
	public String getType(){
		return "king";
	}
	
}
