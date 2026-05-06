//Chess Project 2
//ChessReplayer.java

package ser120.ChessProject2;

import java.io.*; 
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.List;
import java.util.Arrays;
import java.nio.file.StandardOpenOption;

public class ChessReplayer {
	//it's going to be used to the playback and reload feature
	//I don't know how to do that yet
	
	//variables
	public VisualOutput printer;
	
	//constructors
	public ChessReplayer(){
		this.printer = new VisualOutput();
	}
	
	//methods
	
	//first attempt (I googled it)
	/*
	public Path newGameFolder(Scanner scanner){

        // 1. Get folder name from the user
        System.out.println("Enter a name for your new game:");
        String folderName = scanner.nextLine();

        // 2. Define the path for the new folder
        Path folderPath = Paths.get(folderName);

        try {
            // Create the directory. createDirectories() handles cases where parent directories don't exist.
            Files.createDirectories(folderPath);
            System.out.println("Directory created successfully at: " + folderPath.toAbsolutePath());
			return folderPath;
        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
            e.printStackTrace();
            return null;
        } finally {
           // scanner.close();
        }
    }*/
    
    //second attempt (I watched a youtube video)
    /*public Path newGameFolder(Scanner scanner){

        System.out.println("Enter a name for your new game:");
        String folderName = scanner.nextLine();

		try {
			Path directory = Files.createDirectory(Path.of(folderName));
			return directory;
        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
            e.printStackTrace();
            return null;
        }

    }*/
    
    //third attempt (I watched a youtube video)
    
    public Path newGameFolder(Scanner scanner){
		
		System.out.println("Enter a name for your new game:");
		String folderName = scanner.nextLine();
		
		try {
			Path path = Path.of(folderName);
			// Creates directory, ignores if it exists
			return Files.createDirectories(path); 
		} catch (IOException e) {
			System.err.println("Could not create directory: " + e.getMessage());
			return null; // Keep null if you want to know it failed
		}

    }
	
	//first attempt (I googled it)
	/*public void newBoardFile(int turn, Board board){
		
		// 3. Get filenames from the user (e.g., "file1.txt,file2.txt")
            
        String fileName = ("File" + turn);
        String contents = board.toString();

        // 4. Create files inside the folder
          
        Path filePath = folderPath.resolve(fileName); // Combine folder path and filename

		Files.writeString(filePath, contents, StandardOpenOption.CREATE);
            //if (!Files.exists(filePath)) {
            //    Files.createFile(filePath);
                //System.out.println("File created: " + filePath.getFileName());
            //} //else {
                //System.out.println("File already exists: " + filePath.getFileName());
            //}
	}*/
	
	//second attempt (I watched a youtube video)
	public void newBoardFile(int turn, Board board, Path directory){
            
        String fileName = ("File" + turn);
        String contents = board.toString();

		try {
			Path file = Files.createFile(directory.resolve(fileName));
			Files.writeString(file, contents);
        } catch (IOException e) {
            System.err.println("An error occurred during file operations: " + e.getMessage());
            e.printStackTrace();
        }

	}
	
	//numbers hard coded to assume that its a standard chess board, change this later
	public Board readFile(File file){
		Piece [][] boardData = new Piece[8][8];
		Board newBoard = new Board();
		String currentLine = "";
		//Creates a new instance of Scanner to read the file
		Scanner fileInput; 
		
		try{
			
			fileInput = new Scanner(file);
			
			Player player1 = new Player(0);
			Player player2 = new Player(1);
			
			for (int col = 0; col < boardData.length; col++){
			//we are in [col] column!
				for (int row = 0; row < boardData[col].length; row++){
					//we are in [row] row of [col] column
					currentLine = fileInput.nextLine();
					if (currentLine.equals("rook0")){
						boardData[col][row] = new Rook(player1);
					} else if (currentLine.equals("rook1")){
						boardData[col][row] = new Rook(player2);
					} else if (currentLine.equals("bishop0")){
						boardData[col][row] = new Bishop(player1);
					}else if (currentLine.equals("bishop1")){
						boardData[col][row] = new Bishop(player2);
					} else if (currentLine.equals("knight0")){
						boardData[col][row] = new Knight(player1);
					}else if (currentLine.equals("knight1")){
						boardData[col][row] = new Knight(player2);
					} else if (currentLine.equals("queen0")){
						boardData[col][row] = new Queen(player1);
					} else if (currentLine.equals("queen1")){
						boardData[col][row] = new Queen(player2);
					} else if (currentLine.equals("king0")){
						boardData[col][row] = new King(player1);
					} else if (currentLine.equals("king1")){
						boardData[col][row] = new King(player2);
					} else if (currentLine.equals("pawn0")){
						boardData[col][row] = new Pawn(player1);
					} else if (currentLine.equals("pawn1")){
						boardData[col][row] = new Pawn(player2);
					} else {
						boardData[col][row] = null;
					}
				}
			}
			
			fileInput.close();
			
			newBoard.setBoardData(boardData);
		   
		 } catch (FileNotFoundException e){
			System.out.println(e); 
			System.exit(1); 
		 }
		 
		 return newBoard;
	}
	
	//in game manager
	//have a boolean called isNewGame, set true if user inputs n, set false if user inputs r
	//if user inputs n
	//isNewGame = true
	//myChessPlayer.playChess(myScanner,true)
	//if user inputs r
	//string = requestFoldername()
	//playback(string)
	//myChessPlayer.playChess(myScanner,false)
	
	//in chessplayer
	//in playchess method
	//if the boolean passed in is true
	//do what it normally does
	//if the boolean passed in is false
	//print a diffferent message
	//check for checkmate
	//if not checkmate (no one has lost)
	//THEN while(stillPlaying)
	//	playRound(myScanner)
	
	//requestFolderName function{
	//pass in scanner as a parameter
	//prompt user for the name of the folder they want
	//return the name
	//}
	
	public String requestFolderName(Scanner myScanner){
		String userInput = "";
		System.out.println("What is the name of the game you want to reload?");
		userInput = myScanner.nextLine();
		return userInput;
		
	}
	
	//playback function{
	//take a folder name as a parameter
	//print a default board, then wait one second
	//then for each file in the folder, 
	//	read the file into a boardData array (using readFile) 
	//	and print that board state (using printBoard), 
	//	turn ++, 
	//	then wait one second
	//}
	
	public int playback(String name){
		Board defaultBoard = new Board();
		printer.printBoard(defaultBoard);
		int turn = 0;
		//folder = getfolder(name)
		//for each file in folder
			//Board board = readFile(file)
			//printer.printBoard(board)
			//turn++
			//wait one second
		
		//attempt here~~~

        File folder = new File(name);
        
        // Safety check: Ensure the path is a valid directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        System.out.println("File: " + file.getName());
                        Board board = readFile(file);
                        printer.printBoard(board);
                        turn++;
                        try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							Thread.currentThread().interrupt();
						}
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path.");
        }
		
		//end attempt~~~
		
		return turn;
	}
	
	
	public Board getReloadedBoard(String name){
		Board board = new Board();
		//folder = getfolder(name)
		//for each file in folder
			//Board board = readFile(file)
			//printer.printBoard(board)
			//turn++
			//wait one second
		
		//attempt here~~~

        File folder = new File(name);
        
        // Safety check: Ensure the path is a valid directory
        if (folder.exists() && folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        board = readFile(file);
                    }
                }
            }
        } else {
            System.out.println("Invalid directory path.");
        }
		
		//end attempt~~~
		
		return board;
	}
	
}
