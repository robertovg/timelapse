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
@Table(name = "tlp_funcionalidad", catalog = "timelapse")
public class TlpFuncionalidad implements java.io.Serializable {

	private String oid;
	private TlpObjetivo tlpObjetivo;
	private String nombre;
	private String descripcion;
	private Integer orden;
	private List<TlpAccion> tlpAccions = new ArrayList<TlpAccion>(0);

	public TlpFuncionalidad() {
	}

	public TlpFuncionalidad(String oid, TlpObjetivo tlpObjetivo, String nombre,
			Integer orden) {
		this.oid = oid;
		this.tlpObjetivo = tlpObjetivo;
		this.nombre = nombre;
		this.orden = orden;
	}

	public TlpFuncionalidad(String oid, TlpObjetivo tlpObjetivo, String nombre,
			String descripcion, Integer orden, List<TlpAccion> tlpAccions) {
		this.oid = oid;
		this.tlpObjetivo = tlpObjetivo;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.orden = orden;
		this.tlpAccions = tlpAccions;
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
	@JoinColumn(name = "objetivoOID", nullable = false)
	public TlpObjetivo getTlpObjetivo() {
		return this.tlpObjetivo;
	}

	public void setTlpObjetivo(TlpObjetivo tlpObjetivo) {
		this.tlpObjetivo = tlpObjetivo;
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

	@Column(name = "orden", nullable = false)
	public Integer getOrden() {
		return this.orden;
	}

	public void setOrden(Integer orden) {
		this.orden = orden;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tlpFuncionalidad")
	public List<TlpAccion> getTlpAccions() {
		return this.tlpAccions;
	}

	public void setTlpAccions(List<TlpAccion> tlpAccions) {
		this.tlpAccions = tlpAccions;
	}

}
