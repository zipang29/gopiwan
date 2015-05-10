package server.gopigo;

import java.io.IOException;

/**
 * 	Ensemble des fonctions utiles pour controler le streaming vid�o.<br>
 * 	Ce processus de streaming g�n�re des bitmaps (pas d'encodage donc plus rapide) d'une dimension<br>
 * 	donn�e et � intervalle r�gulier.
 *
 */
public interface GoPiGoStreaming {

	/**
	 * D�marre le processus d'acquisition.
	 * @throws IOException
	 */
	public void startStreaming() throws IOException;
	
	/**
	 * Met le processus d'acquisition en pause.
	 * @throws IOException
	 */
	public void stopStreaming() throws IOException;
		
	/**
	 * Modifie la r�solution des images g�n�r�es
	 * @param h - hauteur en pixels
	 * @param w	- largeur en pixels
	 */
	public void setResolution(int h, int w) ;
	
	/**
	 * Modifie la fr�quence � laquelle les images sont g�n�r�es
	 * @param fps - nombre d'images g�n�r�es en une seconde
	 */
	public void setFPS(int fps) ;
}
