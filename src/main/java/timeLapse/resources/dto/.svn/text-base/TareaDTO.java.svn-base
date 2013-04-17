package timeLapse.resources.dto;

import java.util.Calendar;
import java.util.Date;

import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.util.Utiles;
/**
 * Clase que representa a una tarea serializada. Se utiliza para pasar las tareas a la vista y no tener que 
 * "aplanar" las tareas en javascript, ya que podría sobrecargar el navegador del cliente.
 * @author robe
 *
 */
public class TareaDTO {

	private String oid;
	private String oidUsuarioAsociado;
	private String nombreUsuarioUsuarioAsociado;
	private String oidCategoria;
	private String nombreCategoria;
	private String oidUsuarioCreador;
	private String nombreUsuarioCreador;
	private String oidTareaPadre;
	private String nombreTareaPadre;
	private String oidGrupo;
	private String nombreGrupo;
	private String periodicidad;	
	private String nombre;
	private String descripcion;
	private FechaDTO fechaCreacion;
	private FechaDTO fechaRealizacion;
	private String horaRealizacion;
	private FechaDTO fechaInicio;
	private String horaInicio;
	private FechaDTO fechaFin;
	private String horaFin;
	private String localizacionAsociada;
	private String autorrealizable;
	private Integer importancia;
	/**
	 * Propiedad que sirve para indicar si una tarea está o no creada por el usuario logueado. Por defecto
	 * se crea como false.
	 */
	private String esPropiaLaTarea;
	

	public TareaDTO(){
		super();
	}
	
	public TareaDTO(TldTarea tarea){
		this();		
		this.oid = tarea.getOid();
		this.oidUsuarioAsociado = tarea.getTldUsuarioByUsuarioAsociadoOid() != null ? tarea.getTldUsuarioByUsuarioAsociadoOid().getOid() : null;
		this.nombreUsuarioUsuarioAsociado = tarea.getTldUsuarioByUsuarioAsociadoOid() != null ? tarea.getTldUsuarioByUsuarioAsociadoOid().getNombreUsuario() : null;
		this.oidCategoria = tarea.getTldCategoria() != null ? tarea.getTldCategoria().getOid() : null;
		this.nombreCategoria = tarea.getTldCategoria() != null ? tarea.getTldCategoria().getNombre() : null;
		this.oidUsuarioCreador = tarea.getTldUsuarioByUsuarioCreadorOid()!= null ? tarea.getTldUsuarioByUsuarioCreadorOid().getOid() : null;
		this.nombreUsuarioCreador = tarea.getTldUsuarioByUsuarioCreadorOid() != null ? tarea.getTldUsuarioByUsuarioCreadorOid().getNombreUsuario() : null;
		this.oidTareaPadre = tarea.getTldTarea() != null ? tarea.getTldTarea().getOid() : null;
		this.nombreTareaPadre = tarea.getTldTarea() != null ? tarea.getTldTarea().getNombre() : null;
		this.oidGrupo = tarea.getTldGrupo() != null ? tarea.getTldGrupo().getOid() : null;
		this.nombreGrupo = tarea.getTldGrupo() != null ? tarea.getTldGrupo().getNombre() : null;
		this.periodicidad = tarea.getTldTarea() != null ? Utiles.periodicidadToString(tarea.getTldPeriodicidad()) : null;	
		this.nombre = tarea.getNombre() != null ? tarea.getNombre() : null;
		this.descripcion = tarea.getDescripcion() != null ? tarea.getDescripcion() : null;
		this.fechaCreacion = tarea.getFechaCreacion() != null ? new FechaDTO(tarea.getFechaCreacion()) : null;
		
		this.horaRealizacion = tarea.getHoraRealizacion() != null ? Utiles.timeToString(tarea.getHoraRealizacion()) : null;
		Date fechaRealizacion = tarea.getFechaRealizacion();
		if(this.horaRealizacion != null){
			
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaRealizacion);
			Calendar hora = Calendar.getInstance();
			hora.setTime(tarea.getHoraRealizacion());
			
			fecha.set(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
			fecha.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
			fecha.set(Calendar.SECOND, hora.get(Calendar.SECOND));
			
			fechaRealizacion = fecha.getTime();
		}
		this.fechaRealizacion = fechaRealizacion != null ? new FechaDTO(fechaRealizacion, this.horaRealizacion) : null;
		this.horaInicio = tarea.getHoraInicio() != null ? Utiles.timeToString(tarea.getHoraInicio()) : null;
		Date fechaInicio = tarea.getFechaInicio();
		if(this.horaInicio != null){
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaInicio);
			Calendar hora = Calendar.getInstance();
			hora.setTime(tarea.getHoraInicio());
			
			fecha.set(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
			fecha.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
			fecha.set(Calendar.SECOND, hora.get(Calendar.SECOND));
			
			fechaInicio = fecha.getTime();
		}
		this.fechaInicio = fechaInicio != null ? new FechaDTO(fechaInicio, this.horaInicio) : null;

		
		this.horaFin = tarea.getHoraFin() != null ? Utiles.timeToString(tarea.getHoraFin()) : null;
		Date fechaFin = tarea.getFechaFin();
		if(this.horaFin != null){
			Calendar fecha = Calendar.getInstance();
			fecha.setTime(fechaFin);
			Calendar hora = Calendar.getInstance();
			hora.setTime(tarea.getHoraFin());
			
			fecha.set(Calendar.HOUR_OF_DAY, hora.get(Calendar.HOUR_OF_DAY));
			fecha.set(Calendar.MINUTE, hora.get(Calendar.MINUTE));
			fecha.set(Calendar.SECOND, hora.get(Calendar.SECOND));
			
			fechaFin = fecha.getTime();
		}
		this.fechaFin = fechaFin != null ? new FechaDTO(fechaFin, this.horaFin) : null;
		this.localizacionAsociada = tarea.getLocalizacionAsociada() != null ? tarea.getLocalizacionAsociada(): null;
		this.autorrealizable = tarea.getAutorrealizable() == 1 ? "true" : "false";
		this.importancia = tarea.getImportancia() != null ? tarea.getImportancia() : null;
		this.esPropiaLaTarea = "false"; 
	}

	/**
	 * @return the oid
	 */
	public String getOid() {
		return oid;
	}

	/**
	 * @param oid the oid to set
	 */
	public void setOid(String oid) {
		this.oid = oid;
	}

	/**
	 * @return the oidUsuarioAsociado
	 */
	public String getOidUsuarioAsociado() {
		return oidUsuarioAsociado;
	}

	/**
	 * @param oidUsuarioAsociado the oidUsuarioAsociado to set
	 */
	public void setOidUsuarioAsociado(String oidUsuarioAsociado) {
		this.oidUsuarioAsociado = oidUsuarioAsociado;
	}

	/**
	 * @return the nombreUsuarioUsuarioAsociado
	 */
	public String getNombreUsuarioUsuarioAsociado() {
		return nombreUsuarioUsuarioAsociado;
	}

	/**
	 * @param nombreUsuarioUsuarioAsociado the nombreUsuarioUsuarioAsociado to set
	 */
	public void setNombreUsuarioUsuarioAsociado(String nombreUsuarioUsuarioAsociado) {
		this.nombreUsuarioUsuarioAsociado = nombreUsuarioUsuarioAsociado;
	}

	/**
	 * @return the oidCategoria
	 */
	public String getOidCategoria() {
		return oidCategoria;
	}

	/**
	 * @param oidCategoria the oidCategoria to set
	 */
	public void setOidCategoria(String oidCategoria) {
		this.oidCategoria = oidCategoria;
	}

	/**
	 * @return the nombreCategoria
	 */
	public String getNombreCategoria() {
		return nombreCategoria;
	}

	/**
	 * @param nombreCategoria the nombreCategoria to set
	 */
	public void setNombreCategoria(String nombreCategoria) {
		this.nombreCategoria = nombreCategoria;
	}

	/**
	 * @return the oidUsuarioCreador
	 */
	public String getOidUsuarioCreador() {
		return oidUsuarioCreador;
	}

	/**
	 * @param oidUsuarioCreador the oidUsuarioCreador to set
	 */
	public void setOidUsuarioCreador(String oidUsuarioCreador) {
		this.oidUsuarioCreador = oidUsuarioCreador;
	}

	/**
	 * @return the nombreUsuarioCreador
	 */
	public String getNombreUsuarioCreador() {
		return nombreUsuarioCreador;
	}

	/**
	 * @param nombreUsuarioCreador the nombreUsuarioCreador to set
	 */
	public void setNombreUsuarioCreador(String nombreUsuarioCreador) {
		this.nombreUsuarioCreador = nombreUsuarioCreador;
	}

	/**
	 * @return the oidTareaPadre
	 */
	public String getOidTareaPadre() {
		return oidTareaPadre;
	}

	/**
	 * @param oidTareaPadre the oidTareaPadre to set
	 */
	public void setOidTareaPadre(String oidTareaPadre) {
		this.oidTareaPadre = oidTareaPadre;
	}

	/**
	 * @return the nombreTareaPadre
	 */
	public String getNombreTareaPadre() {
		return nombreTareaPadre;
	}

	/**
	 * @param nombreTareaPadre the nombreTareaPadre to set
	 */
	public void setNombreTareaPadre(String nombreTareaPadre) {
		this.nombreTareaPadre = nombreTareaPadre;
	}

	/**
	 * @return the oidGrupo
	 */
	public String getOidGrupo() {
		return oidGrupo;
	}

	/**
	 * @param oidGrupo the oidGrupo to set
	 */
	public void setOidGrupo(String oidGrupo) {
		this.oidGrupo = oidGrupo;
	}

	/**
	 * @return the nombreGrupo
	 */
	public String getNombreGrupo() {
		return nombreGrupo;
	}

	/**
	 * @param nombreGrupo the nombreGrupo to set
	 */
	public void setNombreGrupo(String nombreGrupo) {
		this.nombreGrupo = nombreGrupo;
	}

	/**
	 * @return the periodicidad
	 */
	public String getPeriodicidad() {
		return periodicidad;
	}

	/**
	 * @param periodicidad the periodicidad to set
	 */
	public void setPeriodicidad(String periodicidad) {
		this.periodicidad = periodicidad;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return the descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * @param descripcion the descripcion to set
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/**
	 * @return the fechaCreacion
	 */
	public FechaDTO getFechaCreacion() {
		return fechaCreacion;
	}

	/**
	 * @param fechaCreacion the fechaCreacion to set
	 */
	public void setFechaCreacion(FechaDTO fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	/**
	 * @return the fechaRealizacion
	 */
	public FechaDTO getFechaRealizacion() {
		return fechaRealizacion;
	}

	/**
	 * @param fechaRealizacion the fechaRealizacion to set
	 */
	public void setFechaRealizacion(FechaDTO fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	/**
	 * @return the horaRealizacion
	 */
	public String getHoraRealizacion() {
		return horaRealizacion;
	}

	/**
	 * @param horaRealizacion the horaRealizacion to set
	 */
	public void setHoraRealizacion(String horaRealizacion) {
		this.horaRealizacion = horaRealizacion;
	}

	/**
	 * @return the fechaInicio
	 */
	public FechaDTO getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * @param fechaInicio the fechaInicio to set
	 */
	public void setFechaInicio(FechaDTO fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	/**
	 * @return the horaInicio
	 */
	public String getHoraInicio() {
		return horaInicio;
	}

	/**
	 * @param horaInicio the horaInicio to set
	 */
	public void setHoraInicio(String horaInicio) {
		this.horaInicio = horaInicio;
	}

	/**
	 * @return the fechaFin
	 */
	public FechaDTO getFechaFin() {
		return fechaFin;
	}

	/**
	 * @param fechaFin the fechaFin to set
	 */
	public void setFechaFin(FechaDTO fechaFin) {
		this.fechaFin = fechaFin;
	}

	/**
	 * @return the horaFin
	 */
	public String getHoraFin() {
		return horaFin;
	}

	/**
	 * @param horaFin the horaFin to set
	 */
	public void setHoraFin(String horaFin) {
		this.horaFin = horaFin;
	}

	/**
	 * @return the localizacionAsociada
	 */
	public String getLocalizacionAsociada() {
		return localizacionAsociada;
	}

	/**
	 * @param localizacionAsociada the localizacionAsociada to set
	 */
	public void setLocalizacionAsociada(String localizacionAsociada) {
		this.localizacionAsociada = localizacionAsociada;
	}

	/**
	 * @return the autorrealizable
	 */
	public String getAutorrealizable() {
		return autorrealizable;
	}

	/**
	 * @param autorrealizable the autorrealizable to set
	 */
	public void setAutorrealizable(String autorrealizable) {
		this.autorrealizable = autorrealizable;
	}

	/**
	 * @return the importancia
	 */
	public Integer getImportancia() {
		return importancia;
	}

	/**
	 * @param importancia the importancia to set
	 */
	public void setImportancia(Integer importancia) {
		this.importancia = importancia;
	}

	/**
	 * @return the esPropiaLaTarea
	 */
	public String getEsPropiaLaTarea() {
		return esPropiaLaTarea;
	}

	/**
	 * @param esPropiaLaTarea the esPropiaLaTarea to set
	 */
	public void setEsPropiaLaTarea(String esPropiaLaTarea) {
		this.esPropiaLaTarea = esPropiaLaTarea;
	}



	
}
