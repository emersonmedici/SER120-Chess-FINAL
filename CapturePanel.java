package SER120.ChessProject3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class CapturePanel extends JPanel {
	
	
    private String blackCaptures = "BC ";

    public CapturePanel() {
		//if we want another panel I could just add another panel class for either team 
        setPreferredSize(new Dimension(32,0));
        setBackground(new Color(0x0C0C14));
    }
    
    //this method prints out captured for string(but words get but out if added);
	public void checkCapture() { blackCaptures = "bc: "; repaint(); }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setFont(new Font("Monospaced", Font.BOLD, 14));

        g.setColor(new Color(0xE8C08C));
        //means black captures
        g.drawString(blackCaptures, 12, 21);
		 //going to need to find a way to print the pieces vertically 

        g.setColor(new Color(0xFF3860));
        java.awt.FontMetrics fm = g.getFontMetrics();
        g.dispose();
    }
}
