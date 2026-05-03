//Chess Project 2
//App.java

package ser120.ChessProject3;

//import ser120.ChessProject3.GameManager;
import javax.swing.*;
import java.awt.*;
import SER120.ChessProject3.VisualOutput;
public class App {
	//has the main method
	
   public static void main(String[] args) {
		//prints the board
		SwingUtilities.invokeLater(() -> {
			VisualOutput window = new VisualOutput();
			window.printBoard();
		});
		
		/*System.out.println("~~~ Chess App Open ~~~");
        GameManager gm = new GameManager();
        gm.runGame();
        
        System.out.println("~~~ Chess App Close ~~~");*/
	}
}
