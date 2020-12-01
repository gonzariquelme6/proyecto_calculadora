package logica;

/**
 * Metodo que implementa un plugin de una division.
 * @author Gonzalo M. Riquelme Ludwig - LU:125469
 */
public class Division implements PluginFunction{

	@Override
	public String getPluginName() {
		return "Division";
	}

	@Override
	public float doOperation(int n1, int n2){
		if (n2==0) throw new ArithmeticException();
		else
			return (float)n1/n2;
	}
	
	
}
