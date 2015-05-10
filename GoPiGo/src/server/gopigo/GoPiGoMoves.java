package server.gopigo;

import java.io.IOException;

/**
 *	Ensemble des mouvements réalisables par le GoPiGo.<br>
 *	Une fois appelés, ces fonctions animeront le robot du mouvement décrit<br>
 *	jusqu'à un appel à la fonction {@link #stop()}.<br>
 */
public interface GoPiGoMoves {
	

	/**
	 * Tourne le robot vers la droite.<br>
	 * Une roue active, meilleur controle.<br>
	 * @throws IOException
	 */
	public void turnRight() throws IOException;
	
	/**
	 * 	Tourne le robot vers la gauche.<br>
	 * 	Une roue active, meilleur controle.<br>
	 * @throws IOException
	 */
	public void turnLeft() throws IOException;
	
	/**
	 * Avance tout droit.
	 * @throws IOException
	 */
	public void forward() throws IOException;
	
	/**
	 * Recule.
	 * @throws IOException
	 */
	public void backward() throws IOException;
	
	/**
	 * Rotation sur place anti-horaire.<br>
	 * @throws IOException
	 */
	public void turnLeftRot() throws IOException ;
	
	/**
	 * Rotation sur place horaire.<br>
	 * @throws IOException
	 */
	public void turnRightRot() throws IOException ;
	
	/**
	 * Augmente la vitesse de 10.<br>
	 * @throws IOException
	 */
	public void increaseSpeed() throws IOException ; 
	
	/**
	 * Diminue la vitesse de 10.<br>
	 * @throws IOException
	 */
	public void decreaseSpeed() throws IOException ;
	
	/**
	 * Fixe la vitesse à un seuil donné, entre 0 et 255.<br>
	 * @param speed
	 * @throws IOException
	 */
	public void setSpeed(int speed) throws IOException ;
	
	/**
	 * Stoppe le mouvement courant.<br>
	 * @throws IOException
	 */
	public void stop() throws IOException ;
}
