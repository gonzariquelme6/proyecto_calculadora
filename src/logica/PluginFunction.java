package logica;

public interface PluginFunction {

	/**
	 * Consulta el nombre del plugin
	 * @return
	 */
	public String getPluginName();

	/**
	 * Obtiene el resultado de la operacion
	 * @param n1 primer valor obtenido
	 * @param n2 segundo valor obtenido
	 * @return resultado de la operacion
	 */
	public float doOperation(int n1, int n2);
}
