package timeLapse.util.constantes;




/**
 * Enumerado que contiene los diferentes tipos de avisos o excepciones que la aplicación 
 * eleva cuando tiene que informar de algún estado especial en su funcionamiento
 * 
 * @author robe
 *
 */
public enum ConstantesException {
	usoErroneoFactoriaMensajes("usoErroneoFactoriaMensajes", Severidad.error),
	nombreUsuarioExistente("nombreUsuarioExistente", Severidad.error),
	masDeUnUsuarioMismoNombrePasswd("masDeUnUsuarioMismoNombrePasswd", Severidad.error),
	yaExisteUnUsuarioConEseNombre("yaExisteUnUsuarioConEseNombre", Severidad.info),
	nombreUsuarioPasswordIncorrecto("nombreUsuarioPasswordIncorrecto", Severidad.info),
	tareaPadreInexistente("tareaPadreInexistente", Severidad.error),
	categoriaInexistente("categoriaInexistente", Severidad.error),
	grupoInexistente("grupoInexistente", Severidad.error),
	tareaConFechaFinMenorFechaInicio("tareaConFechaFinMenorFechaInicio", Severidad.error),
	tareaPadreNoEncontrada("tareaPadreNoEncontrada", Severidad.error),
	noSePuedeEnviarHoraInicioSinFechaInicio("noSePuedeEnviarHoraInicioSinFechaInicio", Severidad.error),
	noSePuedeEnviarHoraFinSinFechaFin("noSePuedeEnviarHoraFinSinFechaFin", Severidad.error),
	noSePuedeEnviarHoraRealizacionSinFechaRealizacion("noSePuedeEnviarHoraRealizacionSinFechaRealizacion",Severidad.error),
	seProdujoUnErrorConElFormateoDeLasFechas("seProdujoUnErrorConElFormateoDeLasFechas", Severidad.error),
	filtroTareasIncoherente("filtroTareasIncoherente", Severidad.error),
	tareaYaMarcadaComoRealizada("tareaYaMarcadaComoRealizada", Severidad.error),
	excedidoNumeroMaximoFilas("excedidoNumeroMaximoFilas", Severidad.error),	
	noSePuedeEliminarTareasConHijas("noSePuedeEliminarTareasConHijas", Severidad.error),	
	noSePuedeEliminarCategoriasConTareas("noSePuedeEliminarCategoriasConTareas", Severidad.error),
	noSePuedeEliminarGruposConTareas("noSePuedeEliminarGruposConTareas", Severidad.error),
	noSePuedeEliminarObjetivosConFuncionalidades("noSePuedeEliminarObjetivosConFuncionalidades", Severidad.error),
	noSePuedeEliminarFuncionalidadConAcciones("noSePuedeEliminarFuncionalidadConAcciones", Severidad.error),
	noSePuedeEliminarAccionesConRoles("noSePuedeEliminarAccionesConRoles", Severidad.error),
	tareaHijaInexistente("tareaHijaInexistente", Severidad.error),
	tareaInexistente("tareaInexistente", Severidad.error),
	noSePuedenAsociarTareasAlTiempoSinSeleccionarFechas("noSePuedenAsociarTareasAlTiempoSinSeleccionarFechas", Severidad.error),
	usuarioInexistente("usuarioInexistente", Severidad.error),
	relacionUsuarioGrupoYaExistente("relacionUsuarioGrupoYaExistente", Severidad.error),
	noSePuedeEliminarTareasSiNoEresElCreador("noSePuedeEliminarTareasSiNoEresElCreador", Severidad.error),
	tareaAutorrealizableNoSePuedeFinalizar("tareaAutorrealizableNoSePuedeFinalizar",Severidad.error)
	;
	
	private ConstantesException(String texto, Severidad severidad){
		this.texto = texto;
		this.severidad = severidad;		
	}
	private String texto;
	private Severidad severidad;
	
	public String getTexto() {
		return texto;
	}
	public Severidad getSeveridad() {
		return severidad;
	}

	
	
	
	
	
	
	
	}
