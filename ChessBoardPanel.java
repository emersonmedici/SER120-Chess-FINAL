package ser120.ChessProject4;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

/**
 * Swing component that draws an 8x8 chess board, handles click-to-select
 * and click-to-move, and delegates move legality to ChessRules.
 *
 * Interaction state machine:
 *   IDLE        -> click occupied square  -> SELECTED(from)
 *   SELECTED    -> click same square      -> IDLE
 *   SELECTED    -> click other square     -> rules.isLegalMove ?
 *                                              move + IDLE
 *                                            : reselect or stay
 */
public class ChessBoardPanel extends JPanel {
	
	public VisualOutput printer;

    private static final int N = 8;
    private static final int SQUARE = 72;

    private static final Color LIGHT   = new Color(0xE8C08C);
    private static final Color DARK    = new Color(0x8B5A2B);
    private static final Color SELECT  = new Color(0x00E5FF);      // neon cyan
    private static final Color HOVER   = new Color(0xFF, 0xEB, 0x3B, 160); // translucent amber

	private final Board board = new Board();
	//private final ChessPlayer chessPlayer = new ChessPlayer();

    private final PixelSpriteRenderer sprites = new PixelSpriteRenderer();

    // board[row][col]: row 0 = top (black's back rank).
    private int selRow = -1, selCol = -1;
    private int hoverRow = -1, hoverCol = -1;

	private Piece.Color turn = Piece.Color.WHITE;  
	private int team = 1;

    private StatusPanel statusPanel;
    private CapturePanel whitePanel;//white capture panel
    private OtherCaptureStatusPanel blackPanel; //black capture panel  
    public ChessBoardPanel(StatusPanel stat) {
		this.printer = new VisualOutput();
        setPreferredSize(new Dimension(N * SQUARE, N * SQUARE));
        setBackground(new Color(0x1A1A24));
        setupStartingPosition();
        statusPanel = stat;
        MouseAdapter m = new MouseAdapter() {
            @Override public void mouseClicked(MouseEvent e) { handleClick(e.getX(), e.getY()); }
            @Override public void mouseMoved(MouseEvent e)   { handleHover(e.getX(), e.getY()); }
            @Override public void mouseExited(MouseEvent e)  { hoverRow = hoverCol = -1; repaint(); }
        };
        addMouseListener(m);
        addMouseMotionListener(m);
    }

    private void setupStartingPosition(StatusPanel stat,CapturePanel whitePanel, OtherCaptureStatusPanel blackPanel) {
        // i don't think anything actually has to go here, its handled in the board class i thinkkkkkkk
        printer.printBoard(board);
    }

    private void handleHover(int x, int y) {
        int col = x / SQUARE, row = y / SQUARE;
        if (row < 0 || row >= N || col < 0 || col >= N) { hoverRow = hoverCol = -1; }
        else { hoverRow = row; hoverCol = col; }
        repaint();
    }

    private void handleClick(int x, int y) {
		System.out.println("click");
        int col = x / SQUARE, row = y / SQUARE;
        
        //check its a coord on the board
        if (row < 0 || row >= N || col < 0 || col >= N) return;
        //System.out.println("coord is on board");
        
        Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
        
        //no piece currently selected
        if (selRow < 0){
			System.out.println("no piece selected right now, so proceed");
        //check there's a piece there 
			if (boardData[col][row] != null){
				System.out.println("there is a piece there");
				//checks that the selected piece belongs to the player whose turn it currently is
				System.out.println("piece team: " + boardData[col][row].getTeam() + "your team: " + team);
				if (boardData[col][row].getTeam() == team){
					System.out.println("the piece belongs to you");
					selRow = row; 
					selCol = col;
				} else {
					System.out.println("the piece does not belong to you");
				}
			
			} else {
				System.out.println("there is no piece there");
			}
			
			//something is already selected, now selecting a place to move
			//if this place is the same exact spot
			
		} else if(row == selRow && col == selCol) {
			System.out.println("same spot, deselect");
			//deselect
			selRow = selCol = -1;
			//checks if this piece can move that way
		} else if (boardData[selCol][selRow].checkMoveValidity(selCol,selRow,col,row,board) && pathIsClear(selCol,selRow,col,row,board)){
			//checks for clear path
			System.out.println("move is valid and path is clear");
			statusPanel.moveLegalMessage("legal");
			System.out.println("status panel indicates the move is legal");
			//handles display of what pieces got captured
				if(boardData[selCol][selRow].getTeam() == 1){
						whitePanel.checkCapture(boardData[col][row]);
						System.out.println("added to the white side panel of pieces captured");
						}else{
							blackPanel.checkCapture(boardData[col][row]);
							System.out.println("added to the black side panel of peice captured");
						}
				//move
				board.movePiece(selCol,selRow,col,row);
		
				selRow = selCol = -1;
				
				if(team == 0){
					team = 1;
					turn = Piece.Color.WHITE;
				} else {
					team = 0;
					turn = Piece.Color.BLACK;
				}
				
				statusPanel.setTurn(turn);
				statusPanel.setMessage("messy marvin");
			
				
			//idk i got this from the example
		} else if (boardData[col][row] != null
                && boardData[col][row].getColor() == boardData[selCol][selRow].getColor()) {
            // switch selection to another own piece
            selRow = row; selCol = col;
        }else{
			statusPanel.moveLegalMessage("illegal");
			System.out.println("status panel indicates the move is not legal");
        
        printer.printBoard(board);
        repaint();
        
        if (team == 0){
			if (board.checkForCheckmate(1)){
				System.out.println("Black loses!");
			}
		} else {
			if (board.checkForCheckmate(0)){
				System.out.println("White loses!");
			}
		}
		
       
    }

    @Override
    protected void paintComponent(Graphics g0) {
        super.paintComponent(g0);
        Graphics2D g = (Graphics2D) g0.create();
        // crisp pixels, no smoothing — arcade look.
        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        g.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
        
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                int x = c * SQUARE, y = r * SQUARE;
                g.setColor(((r + c) & 1) == 0 ? LIGHT : DARK);
                g.fillRect(x, y, SQUARE, SQUARE);
                if (r == hoverRow && c == hoverCol && !(r == selRow && c == selCol)) {
                    g.setColor(HOVER);
                    g.fillRect(x, y, SQUARE, SQUARE);
                }
                //fix up this part right here
                if (boardData[c][r] != null) {
                    sprites.draw(g, boardData[c][r], x, y, SQUARE);
                }
            }
        }
        if (selRow >= 0) {
            g.setColor(SELECT);
            g.setStroke(new BasicStroke(4f));
            g.drawRect(selCol * SQUARE + 2, selRow * SQUARE + 2, SQUARE - 4, SQUARE - 4);
        }
        g.dispose();
    }
        
    public boolean pathIsClear(int startCol, int startRow, int endCol, int endRow, Board board){
		//create an array of integers (width and height same as board)
		//set the array equal to board[startCol][startRow].drawPath(startCol,startRow,endCol,endRow,board)
		
		//putting the boardData array here to reference
		Piece [][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
		
		//making the path array with drawPath
		int [][] pathArr = new int[board.getBoardNumCols()][board.getBoardNumRows()];
		pathArr = boardData[startCol][startRow].drawPath(startCol,startRow,endCol,endRow,board);
		
		//start with this boolean
		boolean clearPath = true;
		
		//now use nested for loops to go through the whole path array
		
		for (int col = 0; col < board.getBoardNumCols(); col++){
			//we are in [col] column!
			for (int row = 0; row < board.getBoardNumRows(); row++){
				//we are in [row] row of [col] column
				if (pathArr[col][row] == 1 ){ //if this space is part of the path
					if(boardData[col][row] != null){ //if there is a piece on this space
						if(col == endCol && row == endRow){ //if this space is the landing space
							if(boardData[endCol][endRow].getTeam() == boardData[startCol][startRow].getTeam()){ //if the piece on the landing space is the same team as the piece that is moving
								clearPath = false; //not allowed
							} else { //if the piece occupying the landing space is on the opposite team as the piece that is moving
								clearPath = true; //this IS allowed, it captures
								if(boardData[startCol][startRow].getTeam() == 1){
									whitePanel.checkCapture(boardData[endCol][endRow]);
									System.out.println("added to the white side panel of pieces captured");
								}else{
									blackPanel.checkCapture(boardData[endCol][endRow]);
									System.out.println("added to the black side panel of peice captured");
								}
							}
						} else { //if this space is NOT the landing space
							clearPath = false; //return false! something is in the way of the path, this move cannot happen!
						}
					} else {//if there is NO PIECE on this space
						//do nothing. the space is empty, so there is nothing in the way yet.
					}
				} else { // if this space is NOT part of the path
					//do nothing. this space is not part of the path, so there is no reason to check it
				}
			}
		}
		
		//whereever pathArray[x][y] == 1, check if there board[x][y] != null
		//if it's not null, 
			// check if boardData[endCol][endRow].getTeam() == boardData[startCol][startRow].getTeam()
			// if this is true, then the piece is trying to land on a friendly piece, which is not allowed
				// return false
			//otherwise, this is not true, so it's capturing an enemy, which is allowed
			//return true
		//otherwise, it is null, empty spot 
		//return true
		
		//up in take turn, have an if statement
		// if (pathIsClear())
		// carry on with further checks
		// else... 
		//moveIsValid = false
		return clearPath;
	}
}
