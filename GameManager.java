//Chess Project 2
//GameManager.java

package ser120.ChessProject3;

//import ser120.ChessProject2.ChessPlayer;
//import ser120.ChessProject2.ChessReplayer;
import java.util.Scanner;

public class GameManager {
	//this runs the program, takes user input and then decides which class should handle what comes next
	
	//variables
	public ChessPlayer myChessPlayer;
    boolean running;
  
	//constructors
    public GameManager() {
		this.running = true;
		//this.myChessReplayer = new ChessReplayer();
		this.myChessPlayer = new ChessPlayer();
	}

	//methods
	public void runGame(){
		//instantiate the scanner
		Scanner myScanner = new Scanner(System.in);
		//string to hold user input
		String userInput = "";
		//boolean isNewGame = true;
		
		System.out.println("Welcome to the Chess Program!");
		while (running){
			System.out.println("enter 'n' to start a new game, or anything else to quit: ");
			userInput = myScanner.nextLine();
			//if (userInput.equals("r")){
				//isNewGame = false;
				//reload previous game using the chess replayer
				//String name = myChessReplayer.requestFolderName(myScanner);
				//int turn = myChessReplayer.playback(name);
				//Board reloadedBoard = myChessReplayer.getReloadedBoard(name);
				//myChessPlayer.playChess(myScanner,isNewGame, turn, reloadedBoard, name);
			if (userInput.equals("n")){
				//isNewGame = true;
				//Board dummyBoard = new Board();
				//String dummyName = "";
				myChessPlayer.playChess(myScanner);
			} else {
				running = false;
			}
		
		}
		
		myScanner.close();
	}
    
}




