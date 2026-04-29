//Chess Project 2
//Queen.java

package ser120.ChessProject3;


class Queen extends Piece {
	
	//variables (add if needed)
	
	//constructors
	public Queen(Player initPlayer){
		super(initPlayer);
	}
	
	//methods
	public boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board){
		//check move validity for this piece, how does this piece move?
		//queen is combo of rook and bishop
		
		if ((Math.abs(endCol - startCol) != Math.abs(endRow - startRow)) && (endRow != startRow && endCol != startCol)){
			return false;
		} else {
			return true;
		}
	}
	
	public int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board){
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
		
		//variables determining where to place values in the array, starting at the "start" position
		int currentCol = startCol;
		int currentRow = startRow;
		
		//if it's a bishop type movement
		if (endCol != startCol && endRow != startRow){
			numSpacesMoved = Math.abs(endCol-startCol);
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
			
		} else { //if it's a rook type movement
			
			if (endCol != startCol){
			//piece is moving left or right
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
			
		}
		
		return pathArray;
	}
	
	public String getVisual(){
		if (this.team == 0){
			return "BQ ";
		} else {
			return "WQ ";
		}
	}
	
	//returns a string that says what type of piece it is
	public String getType(){
		return "queen";
	}
	
	
}
