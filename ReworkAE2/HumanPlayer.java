//2600104L
import java.util.Scanner;

public class HumanPlayer extends Player{
	
	public HumanPlayer(char p) {
		super(p);
	}
	
	public boolean move() { //override the move method from Player class
		System.out.println("YourTurn! Please enter an interger between 1 to 6 from your die results Human!");
		Scanner s = new Scanner(System.in);
		int dieValue = s.nextInt(); //get Human Player int value
		int newPlayerPos = this.playerPosition + dieValue; 
		System.out.println(this.playerID +" was in "+ this.getplayerPosition() + " and rolls a " + dieValue);
			
		if(newPlayerPos >= 49) { //game ends if player reaches 49 and above.
			return true;
		}else {
			this.playerPosition = newPlayerPos;
			return false;
		}
	}
	
	
}
