package timeLapse.resources.persistenceDTO;

// Generated 13-oct-2009 23:10:27 by Hibernate Tools 3.2.4.GA

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

/**
 * 			Clase autogenerada a usando Hibernate Tools
 * 			3.2.2.GA
 * 			@author robe
 * 		
 */
@Entity
@Table(name = "tld_tarea", catalog = "timelapse")
public class TldTarea implements java.io.Serializable {

	private String oid;
	private TldUsuario tldUsuarioByUsuarioAsociadoOid;
	private TldCategoria tldCategoria;
	private TldUsuario tldUsuarioByUsuarioCreadorOid;
	private TldTarea tldTarea;
	private TldGrupo tldGrupo;
	private TldPeriodicidad tldPeriodicidad;
	private String nombre;
	private String descripcion;
	private Date fechaCreacion;
	private Date fechaRealizacion;
	private Date horaRealizacion;
	private Date fechaInicio;
	private Date horaInicio;
	private Date fechaFin;
	private Date horaFin;
	private String localizacionAsociada;
	private Integer autorrealizable;
	private Integer importancia;
	private List<TldTarea> tldTareas = new ArrayList<TldTarea>(0);

	public TldTarea() {
	}

	public TldTarea(String nombre, Date fechaCreacion, Integer autorrealizable,
			Integer importancia) {
		this.nombre = nombre;
		this.fechaCreacion = fechaCreacion;
		this.autorrealizable = autorrealizable;
		this.importancia = importancia;
	}

	public TldTarea(TldUsuario tldUsuarioByUsuarioAsociadoOid,
			TldCategoria tldCategoria,
			TldUsuario tldUsuarioByUsuarioCreadorOid, TldTarea tldTarea,
			TldGrupo tldGrupo, TldPeriodicidad tldPeriodicidad, String nombre,
			String descripcion, Date fechaCreacion, Date fechaRealizacion,
			Date horaRealizacion, Date fechaInicio, Date horaInicio,
			Date fechaFin, Date horaFin, String localizacionAsociada,
			Integer autorrealizable, Integer importancia,
			List<TldTarea> tldTareas) {
		this.tldUsuarioByUsuarioAsociadoOid = tldUsuarioByUsuarioAsociadoOid;
		this.tldCategoria = tldCategoria;
		this.tldUsuarioByUsuarioCreadorOid = tldUsuarioByUsuarioCreadorOid;
		this.tldTarea = tldTarea;
		this.tldGrupo = tldGrupo;
		this.tldPeriodicidad = tldPeriodicidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaCreacion = fechaCreacion;
		this.fechaRealizacion = fechaRealizacion;
		this.horaRealizacion = horaRealizacion;
		this.fechaInicio = fechaInicio;
		this.horaInicio = horaInicio;
		this.fechaFin = fechaFin;
		this.horaFin = horaFin;
		this.localizacionAsociada = localizacionAsociada;
		this.autorrealizable = autorrealizable;
		this.importancia = importancia;
		this.tldTareas = tldTareas;
	}

	@GenericGenerator(name = "generator", strategy = "uuid.hex")
	@Id
	@GeneratedValue(generator = "generator")
	@Column(name = "oid", unique = true, nullable = false, length = 32)
	public String getOid() {
		return this.oid;
	}

	public void setOid(String oid) {
		this.oid = oid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioAsociadoOID")
	public TldUsuario getTldUsuarioByUsuarioAsociadoOid() {
		return this.tldUsuarioByUsuarioAsociadoOid;
	}

	public void setTldUsuarioByUsuarioAsociadoOid(
			TldUsuario tldUsuarioByUsuarioAsociadoOid) {
		this.tldUsuarioByUsuarioAsociadoOid = tldUsuarioByUsuarioAsociadoOid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "categoriaAsociadaOID")
	public TldCategoria getTldCategoria() {
		return this.tldCategoria;
	}

	public void setTldCategoria(TldCategoria tldCategoria) {
		this.tldCategoria = tldCategoria;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioCreadorOID")
	public TldUsuario getTldUsuarioByUsuarioCreadorOid() {
		return this.tldUsuarioByUsuarioCreadorOid;
	}

	public void setTldUsuarioByUsuarioCreadorOid(
			TldUsuario tldUsuarioByUsuarioCreadorOid) {
		this.tldUsuarioByUsuarioCreadorOid = tldUsuarioByUsuarioCreadorOid;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tareaPadreOID")
	public TldTarea getTldTarea() {
		return this.tldTarea;
	}

	public void setTldTarea(TldTarea tldTarea) {
		this.tldTarea = tldTarea;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "grupoAsociadoOID")
	public TldGrupo getTldGrupo() {
		return this.tldGrupo;
	}

	public void setTldGrupo(TldGrupo tldGrupo) {
		this.tldGrupo = tldGrupo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "periodicidadOID")
	public TldPeriodicidad getTldPeriodicidad() {
		return this.tldPeriodicidad;
	}

	public void setTldPeriodicidad(TldPeriodicidad tldPeriodicidad) {
		this.tldPeriodicidad = tldPeriodicidad;
	}

	@Column(name = "nombre", nullable = false, length = 100)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 500)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "fechaCreacion", nullable = false, length = 19)
	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaRealizacion", length = 10)
	public Date getFechaRealizacion() {
		return this.fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaRealizacion", length = 8)
	public Date getHoraRealizacion() {
		return this.horaRealizacion;
	}

	public void setHoraRealizacion(Date horaRealizacion) {
		this.horaRealizacion = horaRealizacion;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaInicio", length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaInicio", length = 8)
	public Date getHoraInicio() {
		return this.horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Temporal(TemporalType.TIME)
	@Column(name = "horaFin", length = 8)
	public Date getHoraFin() {
		return this.horaFin;
	}

	public void setHoraFin(Date horaFin) {
		this.horaFin = horaFin;
	}

	@Column(name = "localizacionAsociada", length = 500)
	public String getLocalizacionAsociada() {
		return this.localizacionAsociada;
	}

	public void setLocalizacionAsociada(String localizacionAsociada) {
		this.localizacionAsociada = localizacionAsociada;
	}

	@Column(name = "autorrealizable", nullable = false)
	public Integer getAutorrealizable() {
		return this.autorrealizable;
	}

	public void setAutorrealizable(Integer autorrealizable) {
		this.autorrealizable = autorrealizable;
	}

	@Column(name = "importancia", nullable = false)
	public Integer getImportancia() {
		return this.importancia;
	}

	public void setImportancia(Integer importancia) {
		this.importancia = importancia;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldTarea")
	public List<TldTarea> getTldTareas() {
		return this.tldTareas;
	}

	public void setTldTareas(List<TldTarea> tldTareas) {
		this.tldTareas = tldTareas;
	}

}
