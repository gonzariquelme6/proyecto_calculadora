package logica;

/**
 * Metodo que implementa un plugin de una potencia.
 * @author Gonzalo M. Riquelme Ludwig - LU:125469
 */
public class Potencia implements PluginFunction{

	@Override
	public String getPluginName() {
		return "Potencia";
	}

	@Override
	public float doOperation(int n1, int n2) throws ArithmeticException {
		return (float) Math.pow(n1, n2);
	}
	
}
