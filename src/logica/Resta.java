package logica;

/**
 * Metodo que implementa un plugin de una resta.
 * @author Gonzalo M. Riquelme Ludwig - LU:125469
 */
public class Resta implements PluginFunction{

	@Override
	public String getPluginName() {
		return "Resta";
	}

	@Override
	public float doOperation(int n1, int n2){
		return n1-n2;
	}
	
}
