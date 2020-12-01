package logica;

import java.io.File;
import java.util.*;

import javax.swing.JOptionPane;

/**
 * Clase Calculadora que modela el funcionamiento de una calculadora usando plugins
 * @author Gonzalo M Riquelme Ludwig - LU:125469
 */
public class Calculadora {

	private File ruta;
	private List<PluginFunction> plugins;
	private String [] archivos;
	private int cant_plugins;
	
	/**
	 * Creo una calculadora nueva
	 */
	public Calculadora () {
		ruta = new File("./plugins");
	}


	/**
	 * Metodo que carga los plugins de la ruta indicada
	 */
	public void getPlugins() throws NoClassDefFoundError{
		ClassLoader cl;
		Class c;
		Class [] interfaces;
		PluginFunction pf;
		
		plugins = new ArrayList<PluginFunction>();
		
		
		try {
			//cargo los archivos de la carpeta de plugins
			cl = new PluginClassLoader(ruta);
			archivos = ruta.list();
			cant_plugins=0;
			
			//si la carpeta de plugins no estaba vaica
			if (archivos!=null) {
				JOptionPane.showMessageDialog(null,"Ruta:"+ruta);
				for (int i=0;i<archivos.length;i++) {
					
					if (archivos[i].endsWith(".class")) {
						//si es un archivo que termina en .class llamo a loadClass del PluginClassLoader y cargo el plugin
						c = cl.loadClass(archivos[i].substring(0, archivos[i].indexOf(".")));
						interfaces = c.getInterfaces();
						
						for (Class interf : interfaces) {
							//si la clase implementa PluginFunction la agrego a la lista de plugins
							if (interf.getName().contentEquals("logica.PluginFunction")) {
								//ERROR EN EL CASTEO CUANDO EXPORTO EL JAR
								pf = (PluginFunction) c.getDeclaredConstructor().newInstance();
								plugins.add(pf);
								cant_plugins++;
								break;
							}
						}

					}
					
				}
			}
			else {
				JOptionPane.showMessageDialog(null,"Error en la ruta de los plugins.");
			}
		}catch (Exception ex) {
			JOptionPane.showMessageDialog(null, "Ex:"+ex.toString());
		}
	}

	/**
	 * Metodo que retorna el nombre de los plugins
	 * @return arrego con los nombres de los plugins en la carpeta seteada.
	 */
	public String[] getNombresPlugins() {
		PluginFunction function;
		String [] nombres = new String [cant_plugins];
		Iterator<PluginFunction> it = plugins.iterator();
		int i=0;
		
		//mientras el iterador tenga siguiente agrego el nombre del plugin al arreglo de nombres
		while(it.hasNext()) {
			function = (PluginFunction) it.next();
			nombres[i++] = function.getPluginName();
		}
		
		return nombres;
	}
	
	/**
	 * Metodo que ejecuta el plugin obtenido por parametro con los dos valores obtenidos por parametro
	 * @param num1 primer valor a usar en el plugin
	 * @param num2 segundo valor a usar en el plugin
	 * @param plugin nombre del plugin a ejecutar
	 * @return resultado del plugin ejecutado
	 */
	public float runPlugin(int num1, int num2, String plugin) {
		PluginFunction pf;
		String nombre;
		Iterator<PluginFunction> iter = plugins.iterator();
		float result=0;
		
		try {
			//mientras el iterador tenga siguiente recorro los plugins hasta encontrar la operacion deseada y obtengo su resultado
			while (iter.hasNext()) {
				pf = (PluginFunction) iter.next();
				nombre = pf.getPluginName();
				if (plugin.contentEquals(nombre)) {
					result = pf.doOperation(num1, num2);
				}
			}

		}catch (ArithmeticException ex) {
			throw new ArithmeticException();
		}
		
		return result;
	}
}