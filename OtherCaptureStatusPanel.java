package SER120.ChessProject3;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import java.util.ArrayList;
import java.util.List;
import java.awt.RenderingHints;

public class OtherCaptureStatusPanel extends JPanel {
	
	
    private List<Piece> capturedpieces = new ArrayList <>();

    public OtherCaptureStatusPanel() {
		//if we want another panel I could just add another panel class for either team 
        setPreferredSize(new Dimension(32,0));
        setBackground(new Color(0x0C0C14));
    }
    
    //this method prints out captured for string(but words get but out if added);
			public void checkCapture(Piece piece) { 
			capturedpieces.add(piece);
			repaint();
			}

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
         //going to need to find a way to print the pieces vertically 
			g.setColor(new Color(0xE8C08C));
        //means black captures
        java.awt.FontMetrics fm = g.getFontMetrics();
        g.translate(getWidth()/2.0,getHeight()/2.0);
        g.rotate(-Math.PI/2);
        //uses the array list that stores captured peices and should print the peices 
        //to the side bar
        for(int i = 0; i < capturedpieces.size();i++){
		g.drawString(capturedpieces.get(i).getVisual(),4,fm.getHeight() + i * fm.getHeight());
		
        g.dispose();
		}
    }
}
