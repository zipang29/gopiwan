package server;

/**
 * Class CompositeMove
 * 
 * Repr�sente un mouvement plus complexe.
 * On part du principe que tout mouvement est d�composable en suite de CompositeMove,
 * qui sont eux m�me une combinaision de deux mouvements primitifs execut�s en m�me temps.
 * 
 * @author Nicolas
 *
 */
public class CompositeMove {
	
	private Move move1, move2 ;
	
	private int speed1, speed2 ;
	
	public CompositeMove(Move move1, Move move2, int speed1, int speed2){
		
		this.move1 = move1 ; 
		this.move2 = move2 ;
		this.speed1 = speed1 ; 
		this.speed2 = speed2 ;
	}
	
	

}
