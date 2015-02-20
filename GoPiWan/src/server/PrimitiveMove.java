package server;

/**
 * Enumeration PrimitiveMove
 * 
 * Liste des mouvements élémentaires réalisables par le robot.
 * N'importe quel mouvement complexe est une combinaison linéaire
 * de ces mouvements élémentaires.
 * 
 * @author Nicolas
 *
 */
public enum PrimitiveMove {

	FORWARD, 	// mouvement de translation avant
	BACK, 		// mouvement de translation arrière
	LEFT, 		// mouvement de rotation anti-horaire
	RIGHT ; 	// mouvement de rotation horaire
}	
