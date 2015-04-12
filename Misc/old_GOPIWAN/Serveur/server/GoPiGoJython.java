package server;

/*
 * En attendant que la librairie gopigo soit disponible,
 * on se contente de faire des appels à la librairie python
 * via un interpréteur python intégré à l'environnement Java, Jython
 * 
 * Il s'agit donc pour l'heure d'une approche intergicielle
 */
import org.python.util.PythonInterpreter; 

public class GoPiGoJython implements GoPiGoMovesI{
	
	private PythonInterpreter python ;
	
	public GoPiGoJython(){
		
		this.python = new PythonInterpreter() ;
		try{
		python.exec("import sys");
		python.exec("sys.path.append('/usr/local/lib/python2.7/dist-packages/')");
		python.exec("import gopigo");
		}catch(Exception e){ 
			System.out.println("Erreur, la lib python gopigo n'est pas présente sur cet appareil");
		}
	}

	@Override
	public void turnRight() {
		if(Globals.verbose) System.out.println("Turn Right");
		python.exec("gopigo.right()") ;
	}

	@Override
	public void turnLeft() {
		if(Globals.verbose) System.out.println("Turn Left");
		python.exec("gopigo.left()") ;
	}

	@Override
	public void forward() {
		if(Globals.verbose) System.out.println("Go Forward");
		python.exec("gopigo.fwd()") ;
	}

	@Override
	public void backward() {
		if(Globals.verbose) System.out.println("Go Backward");
		python.exec("gopigo.bwd()") ;	
	}

	@Override
	public void stop() {
		
		python.exec("gopigo.stop()") ;
	}

}
