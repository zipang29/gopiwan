package config;

/***
 * Représente les Devices à démarrer lors de l'initialisation du GoPiGo
 * 
 * La liste de ces périphériques est représentée par une liste de bits,
 * dont l'index indique le péripherique activé.
 * 
 * @author Nicolas
 *
 */
public class Configuration {

	public boolean ALL_DISABLED = false ;
	
	public boolean WHEELS_ENABLED = false ;
	
	public boolean CAMERA_ENABLED = false ;
	
	public static final int NULL = 0b000 ;
	
	public static final int WHEELS = 0b001 ;
	
	public static final int CAMERA = 0b010 ;
		
	/**
	 * exemple d'utilisation : 
	 * Conficguration(Configuration.WHEELS | Configuration.CAMERA )
	 * 
	 * @param config
	 */
	public Configuration(int config){
		
		if((config & WHEELS) != 0) WHEELS_ENABLED = true ;
		if((config & CAMERA) != 0) WHEELS_ENABLED = true ;

	}
}
