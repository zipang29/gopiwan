package server;

/**
 * Class CompositeMove
 * 
 * Représente un mouvement plus complexe.
 * On part du principe que tout mouvement est décomposable en suite de CompositeMove,
 * qui sont eux même une combinaision de deux mouvements primitifs executés en même temps.
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
