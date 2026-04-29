//Chess Project 2
//Rook.java

package ser120.ChessProject3;


class Rook extends Piece {
	
	//variables (add if needed)
	
	//constructors
	public Rook(Player initPlayer){
		super(initPlayer);
	}
	
	//methods
	public boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board){
		//check move validity for this piece, how does this piece move?
		//straight forward or straight back, or to the left or to the right  
		if(endRow != startRow && endCol != startCol){
			return false;
		} else {
			return true;
		}
		
	}
	
	public int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board){
		//create an array to store the path, put in 1's wherever the piece goes
		int [][] pathArray = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		//num spaces the piece moves
		int numSpacesMoved = 0;
		
		//fill up array with zeros to start
		for (int row = 0; row < board.getBoardNumRows(); row++){
			//we are in [row] row!
			for (int col = 0; col < board.getBoardNumCols(); col++){
				//we are in [col] column or [row] row
				pathArray[col][row] = 0;
			}
		}
		
		//figure out direction piece is moving
		if (endCol != startCol){
			//piece is moving left or right
			int currentCol = startCol;
			numSpacesMoved = Math.abs(endCol - startCol);
			if (endCol < startCol){
				//piece is moving LEFT
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol--;
					pathArray[currentCol][startRow] = 1;
				}
			} else {
				//piece is moving RIGHT
				for(int i = 0; i < numSpacesMoved; i++){
					currentCol++;
					pathArray[currentCol][startRow] = 1;
				}
			}
		} else {
			//piece is moving up or down
			int currentRow = startRow;
			numSpacesMoved = Math.abs(endRow - startRow);
			if (endRow < startRow){
				//piece is moving UP
				for(int i = 0; i < numSpacesMoved; i++){
					currentRow--;
					pathArray[startCol][currentRow] = 1;
				}
			} else {
				//piece is moving DOWN
				for(int i = 0; i < numSpacesMoved; i++){
					currentRow++;
					pathArray[startCol][currentRow] = 1;
				}
			}
		}
		
		return pathArray;
	}
	
	//returns a string to print, represents the piece on the board, helper method for the printer, 
	public String getVisual(){
		if (this.team == 0){
			return "BR ";
		} else {
			return "WR ";
		}
	}
	
	//returns a string that says what type of piece it is
	public String getType(){
		return "rook";
	}
	
}
