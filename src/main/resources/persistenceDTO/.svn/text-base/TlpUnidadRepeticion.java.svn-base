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
@Table(name = "tlp_unidad_repeticion", catalog = "timelapse")
public class TlpUnidadRepeticion implements java.io.Serializable {

	private String oid;
	private String nombre;
	private List<TldPeriodicidad> tldPeriodicidads = new ArrayList<TldPeriodicidad>(
			0);

	public TlpUnidadRepeticion() {
	}

	public TlpUnidadRepeticion(String nombre) {
		this.nombre = nombre;
	}

	public TlpUnidadRepeticion(String nombre,
			List<TldPeriodicidad> tldPeriodicidads) {
		this.nombre = nombre;
		this.tldPeriodicidads = tldPeriodicidads;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tlpUnidadRepeticion")
	public List<TldPeriodicidad> getTldPeriodicidads() {
		return this.tldPeriodicidads;
	}

	public void setTldPeriodicidads(List<TldPeriodicidad> tldPeriodicidads) {
		this.tldPeriodicidads = tldPeriodicidads;
	}

}
