package logica;

/**
 * Metodo que implementa un plugin de una suma.
 * @author Gonzalo M. Riquelme Ludwig - LU:125469
 */
public class Suma implements PluginFunction{

	@Override
	public String getPluginName() {
		return "Suma";
	}

	@Override
	public float doOperation(int n1, int n2) {
		return n1+n2;
	}


}
