//Chess Project 2
//Piece.java
//ABSTRACT class

package ser120.ChessProject3;

//a class to hold the things pieces share in common
abstract class Piece {
  
  //variables
	public Player player;
	public int team;
	public String type;
  
  //constructors
	public Piece(Player initPlayer){
		this.player = initPlayer;
		this.team = player.getTeam();
	}
  
  //methods
	
	//getters
	public Player getPlayer(){
		return this.player;
	}
	
	public int getTeam(){
		return this.team;
	}
	
	//each piece will return a string corresponding to its type, ex: a King would return "king", this is a useful method for detecting checkmate
	public abstract String getType();
	
	//method to check the validity of a move based on what kind of piece it is trying to move, need to define in each different piece class
	public abstract boolean checkMoveValidity(int startCol, int startRow, int endCol, int endRow, Board board);
  
	//helper method of for the printing of the board, this will return a String of text to print to represent a piece, need to define in each different piece class
	// example a King piece might return a "WK" if it is a white King piece or "BK" if it is a black king piece
	public abstract String getVisual();
	
	public abstract int[][] drawPath(int startCol, int startRow, int endCol, int endRow, Board board);

}








