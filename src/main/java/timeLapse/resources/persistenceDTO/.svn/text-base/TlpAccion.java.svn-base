package timeLapse.resources.persistenceDTO;

// Generated 13-oct-2009 23:10:27 by Hibernate Tools 3.2.4.GA

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

import org.hibernate.annotations.GenericGenerator;

/**
 * 			Clase autogenerada a usando Hibernate Tools
 * 			3.2.2.GA
 * 			@author robe
 */
@Entity
@Table(name = "tlp_accion", catalog = "timelapse")
public class TlpAccion implements java.io.Serializable {

	private String oid;
	private TlpFuncionalidad tlpFuncionalidad;
	private String nombre;
	private String descripcion;
	private String path;
	private Integer orden;
	private List<TlrPermiso> tlrPermisos = new ArrayList<TlrPermiso>(0);

	public TlpAccion() {
	}

	public TlpAccion(String oid, TlpFuncionalidad tlpFuncionalidad,
			String nombre, String path, Integer orden) {
		this.oid = oid;
		this.tlpFuncionalidad = tlpFuncionalidad;
		this.nombre = nombre;
		this.path = path;
		this.orden = orden;
	}

	public TlpAccion(String oid, TlpFuncionalidad tlpFuncionalidad,
			String nombre, String descripcion, String path, Integer orden,
			List<TlrPermiso> tlrPermisos) {
		this.oid = oid;
		this.tlpFuncionalidad = tlpFuncionalidad;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.path = path;
		this.orden = orden;
		this.tlrPermisos = tlrPermisos;
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
	@JoinColumn(name = "funcionalidadOID", nullable = false)
	public TlpFuncionalidad getTlpFuncionalidad() {
		return this.tlpFuncionalidad;
	}

	public void setTlpFuncionalidad(TlpFuncionalidad tlpFuncionalidad) {
		this.tlpFuncionalidad = tlpFuncionalidad;
	}

	@Column(name = "nombre", nullable = false, length = 45)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "descripcion", length = 45)
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "path", nullable = false, length = 100)
	public String getPath() {
		return this.path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	@Column(name = "orden", nullable = false)
	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tlpAccion")
	public List<TlrPermiso> getTlrPermisos() {
		return this.tlrPermisos;
	}

	public void setTlrPermisos(List<TlrPermiso> tlrPermisos) {
		this.tlrPermisos = tlrPermisos;
	}

}
