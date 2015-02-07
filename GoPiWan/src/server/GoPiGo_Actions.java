package server;

/**
 * 		GoPiGo_Actions Interface
 * 
 * Actions possibles sur le GoPiGo
 * Fonctions appellables directement par un programme tiers, ou par le biais
 * d'une interface intéréagissant avec le serveur HTTP.
 * 
 * @author Nicolas
 *
 */
public interface GoPiGo_Actions {
	
	
	/// ON / OFF
	
	/**
	 * 
	 */
	void init() ;
	
	/**
	 * 
	 */
	void shutdown() ;
	
	
	/// MOVES PRIMITIVES	
	
	/**
	 * 
	 * 
	 * @param distance
	 * @param speed
	 */
	void forward(float distance, float speed) ;
	
	/**
	 * 
	 * 
	 * @param distance
	 * @param speed
	 */
	void back(float distance, float speed) ;
	
	/**
	 * 
	 * 
	 * @param distance
	 * @param speed
	 */
	void turnRight(float distance, float speed) ;
	
	/**
	 * 
	 * 
	 * @param distance
	 * @param speed
	 */
	void turnLeft(float distance, float speed) ;
	
	
	/// MOVES EVOLUATES	
	
	/**
	 * 
	 * 
	 * @param way
	 */
	//void move(Equation way) ;
	
	/**
	 * 
	 * Equivalent à l'appel du mouvement associé
	 * 
	 * @param move
	 */
	void move(PrimitiveMove move) ;

	/**
	 * 
	 * 
	 * @param way
	 */
	void move(CompositeMove way) ;
	
	
	/// SENSORS	
	
	/**
	 * 
	 * 
	 * @param device
	 */
	void on(Device device) ;
	
	/**
	 * 
	 * 
	 * @param device
	 */
	void off(Device device) ;
	
	
	/// FLUX
	
	//FluxVideo getVideoStream(int fps) ;
}
