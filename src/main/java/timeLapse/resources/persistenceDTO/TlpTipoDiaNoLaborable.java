package timeLapse.resources.persistenceDTO;

// Generated 13-oct-2009 23:10:27 by Hibernate Tools 3.2.4.GA

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.hibernate.annotations.GenericGenerator;

/**
 * 			Clase autogenerada a usando Hibernate Tools 3.2.2.GA
 * 			@author robe
 * 	
 */
@Entity
@Table(name = "tlp_tipo_dia_no_laborable", catalog = "timelapse")
public class TlpTipoDiaNoLaborable implements java.io.Serializable {

	private String oid;
	private String nombre;
	private String descripcion;
	private List<TldDiaNoLaborable> tldDiaNoLaborables = new ArrayList<TldDiaNoLaborable>(
			0);

	public TlpTipoDiaNoLaborable() {
	}

	public TlpTipoDiaNoLaborable(String nombre) {
		this.nombre = nombre;
	}

	public TlpTipoDiaNoLaborable(String nombre, String descripcion,
			List<TldDiaNoLaborable> tldDiaNoLaborables) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.tldDiaNoLaborables = tldDiaNoLaborables;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tlpTipoDiaNoLaborable")
	public List<TldDiaNoLaborable> getTldDiaNoLaborables() {
		return this.tldDiaNoLaborables;
	}

	public void setTldDiaNoLaborables(List<TldDiaNoLaborable> tldDiaNoLaborables) {
		this.tldDiaNoLaborables = tldDiaNoLaborables;
	}

}
