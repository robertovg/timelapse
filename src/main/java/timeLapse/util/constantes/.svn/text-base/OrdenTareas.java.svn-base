/**
 * 
 */
package timeLapse.util.constantes;

/**
 * @author robe
 *
 */
public enum OrdenTareas {
	importancia(0),
	fecha(1);
	private int orden;
	private OrdenTareas(int constante){
		this.orden = constante;
		
	}
	public int getEstado() {
		return orden;
	}
	
	public static OrdenTareas getOrden(Integer constante){
		OrdenTareas resul = null;
		if(constante != null){
			
			switch (constante) {			
			case 0:
				resul = OrdenTareas.importancia;
				break;
			case 1:
				resul = OrdenTareas.fecha;
				break;
			default:
				break;
			}
		}
		
		return resul;
	}
	
}
