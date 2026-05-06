//Chess Project 2
//App.java

package ser120.ChessProject4;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;

public class App {
	//has the main method
	
    public static void main(String[] args) {
        System.out.println("~~~ Chess App Open ~~~");
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Demo for Chess");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setResizable(false);
            //frame.setContentPane(new ChessBoardPanel());
            frame.setLayout(new BorderLayout());
            StatusPanel sp = new StatusPanel();
            frame.add(new ChessBoardPanel(sp),  BorderLayout.CENTER);

            frame.add(sp, BorderLayout.SOUTH);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        
        System.out.println("~~~ Chess App Close ~~~");
    }
}
