//2600104L
import java.util.ArrayList;

public class Square {
	private int squarePosition; //location of the Square
	private int delta = 0; 
	private ArrayList<Player> playersInArray = new ArrayList<Player>(); //stores added players in this ListArray
	private int numOfPlayer = 0; //use to keep track of how many players are in the array

	
	public Square(int pos, int delta) { //constructor. 
		this.squarePosition = pos;
		this.delta = delta;	
	}
	
	public int getDelta() {
		return delta;
	}
	
	public int getSquarePosition() { //get the current Square Position
		return squarePosition;
	}
	
	public void setDelta(int delta) { //use to configure the delta in Square
		this.delta = delta;
	}
	
	public void addPlayers(Player player) { //Add players into the Square class Player ArrayList
		this.playersInArray.add(player);
		this.playersInArray.get(numOfPlayer).setSquareLocation(this); //set added players to hold this square reference.
		numOfPlayer ++; //keep track of numbers of players stored in Square ArrayList
	}
	
	public void deletePlayer(Player player) {
		this.playersInArray.remove(player); //removes the player stored in Square
		numOfPlayer --; // keep track of numbers of players

	}
	
	
	public String toString() {
		String name = "";
		for(int i=0; i<numOfPlayer; i++) {
			name += playersInArray.get(i).toString();
		}
		if(delta==0) { // Only print delta with non 0 value
		return  String.format("%4s %3d (   )", name,squarePosition) ;
		} else {
			return  String.format("%4s %3d (%2d )", name,squarePosition, delta) ;
		}
	}
}


