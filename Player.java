//Chess Project 2
//Player.java

package ser120.ChessProject3;

//there will be two players in a game of chess 
//Each turn must be taken by one of the players
//this is useful to keep track of which pieces belong to which player (team black = 0 team white = 1)
//it could also be used in the future for things like scorekeeping over multiple games
public class Player {
	
	//variables
	public int team;
	
	//constructor
	public Player (int initTeam){
		this.team = initTeam;
	}
	
	//methods 
	public int getTeam(){
		return this.team;
	}
	
	
}
