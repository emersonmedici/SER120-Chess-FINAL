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
			 CapturePanel cp = new CapturePanel();
            OtherCaptureStatusPanel ocp = new OtherCaptureStatusPanel();
            frame.add(new ChessBoardPanel(sp),  BorderLayout.CENTER);

            frame.add(sp, BorderLayout.SOUTH);
			 //these two panels are for captured peices for balck or white
            //we could also just do one side panel or two depends which ones easier
            frame.add(cp,BorderLayout.WEST);
            frame.add(ocp,BorderLayout.EAST);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
        
        System.out.println("~~~ Chess App Close ~~~");
    }
}
