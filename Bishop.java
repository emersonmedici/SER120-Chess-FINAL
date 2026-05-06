//Chess Project 2
//Bishop.java

package ser120.ChessProject4;


class Bishop extends Piece {
	
	
	//variables (add if needed)
	
	//constructors
	public Bishop(Player initPlayer){
		super(initPlayer);
	}
	
	//methods
	public boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board){
		//check move validity for this piece, how does this piece move?
		//diagonal movements only
		//System.out.println(endCol + " - " + startCol + " = " + (endCol - startCol) + " and " + endRow + " - " + startRow + " = " + (endRow - startRow));
		if (Math.abs(endCol - startCol) != Math.abs(endRow - startRow)){
			return false;
		} else {
			return true;
		}
	}
	
	public int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board){
		int [][] pathArray = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		
		//num spaces the piece moves
		int numSpacesMoved = Math.abs(endCol-startCol);
		
		//fill up array with zeros to start
		for (int row = 0; row < board.getBoardNumRows(); row++){
			//we are in [row] row!
			for (int col = 0; col < board.getBoardNumCols(); col++){
				//we are in [col] column or [row] row
				pathArray[col][row] = 0;
			}
		}
		
		//variables determining where to place values in the array, starting at the "start" position
		int currentCol = startCol;
		int currentRow = startRow;
		
		//figure out direction the piece is moving
		if(endCol < startCol){ //moving LEFT
			if (endRow < startRow) { //moving LEFT and UP 
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol--;
					currentRow--;
					pathArray[currentCol][startRow] = 1;
				}
			} else { //moving LEFT and DOWN
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol--;
					currentRow++;
					pathArray[currentCol][startRow] = 1;
				}
			}
		} else { //moving RIGHT
			if (endRow < startRow) { //moving RIGHT and UP 
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol++;
					currentRow--;
					pathArray[currentCol][startRow] = 1;
				}
			} else { //moving RIGHT and DOWN
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol++;
					currentRow++;
					pathArray[currentCol][startRow] = 1;
				}
			}
		}
		
		return pathArray;
	}
	
	//returns a string to print, represents the piece on the board, helper method for the printer, 
	public String getVisual(){
		if (this.team == 0){
			return "BB ";
		} else {
			return "WB ";
		}
	}
	
	//returns a string that says what type of piece it is
	/*public String getType(){
		return "bishop";
	}*/
	public Type getType(){
		return Type.BISHOP;
	}
	
}
