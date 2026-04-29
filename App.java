//Chess Project 2
//App.java

package ser120.ChessProject3;

import ser120.ChessProject3.GameManager;

public class App {
	//has the main method
	
    public static void main(String[] args) {
        System.out.println("~~~ Chess App Open ~~~");
        GameManager gm = new GameManager();
        gm.runGame();
        
        System.out.println("~~~ Chess App Close ~~~");
    }
}
