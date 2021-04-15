//2600104L
import java.util.ArrayList;

public class Board {
	private int rows;
	private int columns;
	Square[][] board = new Square[0][0]; //Board should have an attribute that is an array of Square Objects
	ArrayList<Player> playersInArrayBoard = new ArrayList<Player>(); //Board class stores all players playing the game
	
	
	public Board(int rows, int col) { //constructor 
		this.rows = rows;
		this.columns = col;
	}
	
	public int helperRow(int value) { //help Method to locate row of the Square (e.g helperRow(5) finds the row of square with value 5)
		int rowsValue = 0;
		for(int i=0; i<rows; i++) { //loop through the board to find the square value and return with Coordinate of row
			for(int j=0; j<columns; j++) {
						if(value == board[i][j].getSquarePosition() ) {
							rowsValue = i;
						}
			}
		}
		return rowsValue;
	}
	
	public int helperCol(int value) { //help Method to locate column of the Square (e.g helperCol(5) finds the column of square with value 5)
		int columnValue = 0;
		for(int i=0; i<rows; i++) { //loop through the board to find the square value and return with Coordinate of column
			for(int j=0; j<columns; j++) {
						if(value == board[i][j].getSquarePosition() ) {
							columnValue = j;
						}

			}
		}
		return columnValue;
	}

	public String toString() { //toString method to print out the Board object.
		String output = "";
		for(int i=0; i<rows; i++) {
			for(int j=0; j<columns;j++) {
				output += board[i][j].toString();
			}
			output += "\n";
		}
		return output;
	}
	

	public void addPlayer(Player player) { //Board addPlayer Calls the addPlayers method from Square Class 
		int squareToAdd = 0;//players are added to 0 by default.
		int pPositionRow = helperRow(squareToAdd);//locates the coordinates of Square 0
		int pPositionCol = helperCol(squareToAdd);
		board[pPositionRow][pPositionCol].addPlayers(player); //add player to Square
		playersInArrayBoard.add(player);//Keep a reference of all players playing the game in Board
	}
	

	public void createBoard() { //Create the board and wrapping the board as instructed .
		Square[][] buildBoard = new Square[rows][columns];
		board = buildBoard;
		int boardValue = rows*columns-1; //gets the total number needed for board
		
		for(int i=0; i<rows; i++) { 
			for(int j=0; j<columns; j++) {			
				if(i % 2 == 0) {	
					board[i][j] =  new Square(boardValue,0);  //if the board is an even number print it 
					boardValue --; //prints the board in descending order. (e.g 49, 48, 47, 46, 45)
					if(j == columns-1) { //if reaches the end of column, take boardValue and subtract the column.
						boardValue -= columns; 
					}
				}
				if(i%2 !=0 ) { //if board is an odd number
					boardValue++; //prints the board in an ascending order. (e.g 40,41,42,43,44)
					board[i][j] = new Square(boardValue,0);
				if(j == columns-1) { //reaches end of column, take boardValue and subtract column.
					boardValue -=columns;
				}
				}
			}
		}
	}
	


	public void takeTurns() {
		Square[][] buildBoardForTakeTurns = new Square[rows][columns]; //need this to print it out using board toString
		buildBoardForTakeTurns = board;
		boolean winsTheGame = false;

		while(winsTheGame == false) { //stops the loop when one of the player reaches end of the board.
			for(int i=0; i<playersInArrayBoard.size(); i++) { 
				int boardRow = helperRow(playersInArrayBoard.get(i).getplayerPosition());// use player position to locate the board square
				int boardCol = helperCol(playersInArrayBoard.get(i).getplayerPosition());
				Square currentSquare = board[boardRow][boardCol]; //setting the start square to player Position. (0 by default)
				currentSquare.deletePlayer(playersInArrayBoard.get(i)); //deletes the player to get ready to move to next square
				winsTheGame = playersInArrayBoard.get(i).move(); //calls the move method
				
				if(winsTheGame == true) { //if game ends do this
					int winBoardRow = helperRow(49); //BOARD CAN'T print over 49. Hence, all winners will stay on board 49
					int winBoardCol = helperCol(49); //Better illustration on who has won the game
					Square WinSquare = board[winBoardRow][winBoardCol];
					WinSquare.addPlayers(playersInArrayBoard.get(i));
					System.out.println(playersInArrayBoard.get(i) + " Wins the game");
					for(int h=0; h<rows; h++) {
						for(int j=0; j<columns; j++) {
								System.out.print(buildBoardForTakeTurns[h][j].toString()); //prints the board to view final result.
						}
						System.out.println();
					}
					System.out.print("GameEnds! Congratulation Player " + playersInArrayBoard.get(i));
					return;
				}
				
				boardRow = helperRow(playersInArrayBoard.get(i).getplayerPosition());//get player position and find the square coordinates in board again
				boardCol = helperCol(playersInArrayBoard.get(i).getplayerPosition());//as move method has been called above. Move method updates playerPosition based on die.
				Square nextSquare = board[boardRow][boardCol]; //new Square player should be located
				int checkDelta = nextSquare.getDelta(); //check delta of square from rolled die
				if(checkDelta !=0) {
					 int squareNumber = board[boardRow][boardCol].getSquarePosition(); //get for calculation
					 int addDelta = squareNumber + checkDelta;//add the squarePositon with delta
					 boardRow = helperRow(addDelta);
					 boardCol = helperCol(addDelta);
					 nextSquare = board[boardRow][boardCol]; //updates board after adding delta
					 nextSquare.addPlayers(playersInArrayBoard.get(i)); //Add players to their new Square.
					 playersInArrayBoard.get(i).setplayerPosition(nextSquare.getSquarePosition()); //update the playerPosition in Player Class after delta.
					 																			  // needs this to prevent duplication after moving players in Delta
					 for(int h=0; h<rows; h++) { //Print board to see action.
						for(int j=0; j<columns; j++) {
							System.out.print(buildBoardForTakeTurns[h][j].toString());
						}
						System.out.println();
					 }
					 
				}else{//if delta == 0
					boardRow = helperRow(playersInArrayBoard.get(i).getplayerPosition()); //gets playerPos
					boardCol = helperCol(playersInArrayBoard.get(i).getplayerPosition());
					nextSquare = board[boardRow][boardCol];//set nextSquare to board coordinates based on player Position.
					nextSquare.addPlayers(playersInArrayBoard.get(i)); //add player to the square
					for(int h=0; h<rows; h++) {
						for(int j=0; j<columns; j++) {
							System.out.print(buildBoardForTakeTurns[h][j].toString());
						}
						System.out.println();
					}
				}	
			}
		}
	}



	public static void main(String[] args) {
		Board gameBoard = new Board(10,5); 
		gameBoard.createBoard();//create the board with the Squares
		Player p1 = new Player('E'); 
		gameBoard.addPlayer(p1); //add player
		Player p2 = new Player('S');
		gameBoard.addPlayer(p2); 
		System.out.println(gameBoard);
		gameBoard.takeTurns();

	}
}
