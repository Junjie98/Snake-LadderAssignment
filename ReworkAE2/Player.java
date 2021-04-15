//2600104L
import java.util.Random; 

public class Player {
	protected char playerID;
	protected int playerPosition;
	protected Square squareLocation; //Used to reference OR bind player to Square.

	public Player(char a) {//Constructor
		this.playerID = a;
		this.playerPosition = 0; //0 due to player always starts in board 0
	}

	public String toString() {
		String playID = String.valueOf(playerID); //convert char to String
		return playID ;
	}
	public int getplayerPosition() { //to get player position
		return playerPosition;
	}
	public void setplayerPosition(int pos) { //set new player position
		this.playerPosition = pos;
	}
	public char getplayerID() { //get playerID (e.g 'E' , 'S')
		return playerID;
	}
	
	public void setSquareLocation(Square square) { //binding players to Square. Will use it in Square Class when adding Player.
		this.squareLocation = square;
	}

	public boolean move() { //players from board ArrayList calls this method
		Random r = new Random();
		int count = r.nextInt(6) + 1;
		int newPlayerPos = this.playerPosition + count;  //Sums up the player current position with the die. 
		System.out.println(this.playerID +" was in "+ this.getplayerPosition() + " and rolls a " + count); //Printing shows where players
																										   //move from.
		if(newPlayerPos >= 49) { //If board goes over 49 or equals to 49, return true to stop loop in BoardClass takeTurns() method.
			return true;
		}else {
			this.playerPosition = newPlayerPos; //if game does not end, update the player Position.
			return false;
		}
	}
	
	public static void main(String[] args) {
		Player p1 = new Player('E'); //creation of a player
		Square s1 = new Square(0,0); //creation of a square
		s1.addPlayers(p1); //Square holds the reference of Player
						   //Note: Player holds the Square in Square Class addPlayers.
		System.out.println(p1);
		System.out.println(s1);
	}
	
}
