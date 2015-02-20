package server;

/**
 * Enumeration PrimitiveMove
 * 
 * Liste des mouvements �l�mentaires r�alisables par le robot.
 * N'importe quel mouvement complexe est une combinaison lin�aire
 * de ces mouvements �l�mentaires.
 * 
 * @author Nicolas
 *
 */
public enum PrimitiveMove {

	FORWARD, 	// mouvement de translation avant
	BACK, 		// mouvement de translation arri�re
	LEFT, 		// mouvement de rotation anti-horaire
	RIGHT ; 	// mouvement de rotation horaire
}	
