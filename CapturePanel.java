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

public class CapturePanel extends JPanel {
	
	private List<Piece> capturedpieces = new ArrayList <>();
    private final PixelSpriteRenderer sprites = new PixelSpriteRenderer();

    public CapturePanel() {
		//if we want another panel I could just add another panel class for either team 
        setPreferredSize(new Dimension(40,400));
        setBackground(new Color(0x0C0C14));
    }
    
    //this method prints out captured for string(but words get but out if added);
	public void checkCapture(Piece piece) { 
		if(piece == null) return;
		System.out.println("before capture: " + capturedpieces.size());
			capturedpieces.add(piece);
		System.out.println("white Captured: " + capturedpieces.size());
			repaint();
		}
	//need something for prameters to transfer information to here to display info

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        System.out.println("checkCapture called, list size before: " + capturedpieces.size());
        Graphics2D g = (Graphics2D) g0.create();
        g.setFont(new Font("Monospaced", Font.BOLD, 14));
		 g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
		 g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        g.setColor(new Color(0xE8C08C));
        //means black captures
        java.awt.FontMetrics fm = g.getFontMetrics();
        //g.translate(getWidth()/2.0,getHeight()/2.0);
        //g.rotate(-Math.PI/2);
        //uses the array list that stores captured peices and should print the peices 
        //to the side bar
        for(int i = 0; i < capturedpieces.size();i++){
			g.drawString(capturedpieces.get(i).getVisual(),4,fm.getAscent() + i * fm.getHeight());
		}

        //going to need to find a way to print the pieces vertically 
        
        //working on print the visuals on the side bar 
       
		revalidate();
        g.dispose();
    }
}
