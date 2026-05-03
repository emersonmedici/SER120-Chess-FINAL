//Chess Project 2
//VisualOutput.java

package ser120.ChessProject3;

public class VisualOutput extend JFrame {
	//literally just to print out the board and such
	
	//variables
	
	//constructors
	public VisualOutput(){
		//sets the title of the board
		setTitle("The board");
		//allows you to exit the window
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this set the size of the window when you just open it 
		setSize(600,600);
		setLocationRelativeTo(null); //center on the screen
	}
	
	//methods
	public void printBoard(){
		//int totalRows = board.getBoardNumRows()*4;
		//int currentRow = 0;
		//Piece[][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		//boardData = board.getBoardData();
		
		//this sets the title of the window 
	
		
		JPanel boardPanel = new JPanel();
		boardPanel.setLayout(new GridLayout(8,8));
		
		for (int row = 0; row < 8; row++){
			//we are in [(row/4)+1] row!
			//System.out.print("\n"); //new line
			//System.out.print("|"); //start row of text
			for (int col = 0; col < 8; col++){
				printSpace(boardPanel,row,col);
			}
		}
		
		add(boardPanel);
		setVisible(true);
		//System.out.print("\n");
		
	}
	/*public void printBoard(Board board){
		int totalRows = board.getBoardNumRows()*4;
		int currentRow = 0;
		Piece[][] boardData = new Piece[board.getBoardNumCols()][board.getBoardNumRows()];
		boardData = board.getBoardData();
		
		System.out.println("The board:");
		System.out.print("\n");
		
		for (int row = 0; row < totalRows; row++){
			//we are in [(row/4)+1] row!
			System.out.print("\n"); //new line
			System.out.print("|"); //start row of text
			for (int col = 0; col < board.getBoardNumRows(); col++){
				//we are in [col] column of [(row/4)+1] row!
				//calculate the actual row of the board (each actual row is 3 rows of text tall)
				currentRow = ((row/4));
				//if the row of text is even
				if (row % 2 == 0){
				//print filler in space
				printSpace(currentRow,col);
				//if the row of text is odd
				} else {
					//deternimes if this is the bottom of a row, prints a divider if so
					if((row+1)%4 == 0){
						System.out.print("-----|");
					} else {
						//if there is no piece in this tile
						if (boardData[col][currentRow] == null){
							//print filler in the space
							printSpace(currentRow,col);
						//if there is a piece in this tile
						} else {
							//print the piece in the space
							System.out.print(" ");
							System.out.print(boardData[col][currentRow].getVisual());
							System.out.print(" |");
						}
					}
				}
			}
		}
		
		System.out.print("\n");
		
	}*/
	
	//helper method that determines what color space we are in, then prints filler
	//changed it where it will color the square black or white
	public void printSpaces(JPanel boardPanel, int row, int col){
		 JPanel square = new JPanel();
		 square.setPreferredSize(new Dimension(60,60));

		//does the samething as the old code with painting the code square white or black
		//was trying to figure out how to add pieces, that is taking way too long, so i will 
		//be taking a break while doing other finals
		if ((row + col) % 2 == 0){
				square.setBackground(Color.WHITE);
			}else {
				square.setBackground(Color.BLACK);
				//System.out.print("     |");
			}
			boardPanel.add(square);
		}
	/*public void printSpace(int row,int col){
		if (row % 2 == 0){
			if (col % 2 == 0){
				System.out.print("/////|");
			} else {
				System.out.print("     |");
			}
		} else {
			if (col % 2 == 0){
				System.out.print("     |");
			} else {
				System.out.print("/////|");
			}*/
		}
	}
}
