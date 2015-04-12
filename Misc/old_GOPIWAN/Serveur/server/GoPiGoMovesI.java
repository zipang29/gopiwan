package server;

import java.io.IOException;

/**
 * Mouvements les plus simples réalisables par le GoPiGo
 * 
 * Les fonctions décrites dans cette classe amorcent simplement un mouvement basique,
 * qui s'arrêtera en lancant un autre mouvement ou en invoquant la fonction stop.
 * 
 * 
 * @author Nicolas
 *
 */
public interface GoPiGoMovesI {
	

	public void turnRight() ;
	
	public void turnLeft() ;
	
	public void forward() ;
	
	public void backward() ;
	
	public void stop() ;

}
