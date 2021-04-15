//2600104L
public class Play {

	public static void main(String[] args) {
		int rows = 10;
		int columns = 5;
		Board playBoard = new Board(rows,columns); //set board to 10,5
		playBoard.createBoard();//builds the board
		Player p1 = new Player('E');
		playBoard.addPlayer(p1);
		Player p2 = new Player('S');
		playBoard.addPlayer(p2);
		HumanPlayer human = new HumanPlayer('H'); //calls HumanPlayer and set the playerID as H
		playBoard.addPlayer(human);//add human to the game
		
		//creates deltas
		for(int i=0; i<rows; i++) { 
			for(int j=0; j<columns; j++) {
				if (playBoard.board[i][j].getSquarePosition() == 48) {
					playBoard.board[i][j].setDelta(-3);
				}
				if (playBoard.board[i][j].getSquarePosition() == 34) {
					playBoard.board[i][j].setDelta(-1);
				}
				if (playBoard.board[i][j].getSquarePosition() == 33) {
					playBoard.board[i][j].setDelta(2);
				}
				if (playBoard.board[i][j].getSquarePosition() == 32) {
					playBoard.board[i][j].setDelta(-4);
				}
				if (playBoard.board[i][j].getSquarePosition() == 30) {
					playBoard.board[i][j].setDelta(-2);
				}
				if (playBoard.board[i][j].getSquarePosition() == 29) {
					playBoard.board[i][j].setDelta(-4);
				}
				if (playBoard.board[i][j].getSquarePosition() == 18) {
					playBoard.board[i][j].setDelta(4);
				}
				if (playBoard.board[i][j].getSquarePosition() == 15) {
					playBoard.board[i][j].setDelta(-1);
				}
						
			}
		}
		System.out.println(playBoard); //prints the board created to show starting board.
		playBoard.takeTurns(); // plays with the board
						

	}

}
