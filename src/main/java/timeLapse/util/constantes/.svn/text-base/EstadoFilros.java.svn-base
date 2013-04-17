package timeLapse.util.constantes;

/**
 * Enumerado para los tipos de filtros que pueden existir
 * @author robe
 *
 */
public enum EstadoFilros {
	excluir(-1), 
	incluir(0), 
	exclusivo(1);
	private int estado;
	private EstadoFilros(int constante){
		this.estado = constante;
		
	}
	public int getEstado() {
		return estado;
	}
	/**
	 * Devuelve el EstadoFiltro que equivale al valor pasado como parámetro
	 * @param constante
	 * @return
	 */
	public static EstadoFilros getFiltro(Integer constante){
		EstadoFilros resul = null;
		if(constante != null){
			
			switch (constante) {
			case -1:
				resul = EstadoFilros.excluir;
				break;
			case 0:
				resul = EstadoFilros.incluir;
				break;
			case 1:
				resul = EstadoFilros.exclusivo;
				break;
			default:
				break;
			}
		}
		
		return resul;
	}
	
	
	
}
