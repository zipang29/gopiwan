package server;

import java.io.IOException;

/**
 * Mouvements les plus simples r�alisables par le GoPiGo
 * 
 * Les fonctions d�crites dans cette classe amorcent simplement un mouvement basique,
 * qui s'arr�tera en lancant un autre mouvement ou en invoquant la fonction stop.
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
