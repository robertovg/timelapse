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
 * 			Clase autogenerada a usando Hibernate Tools 3.2.2.GA
 * 			@author robe
 * 	
 */
@Entity
@Table(name = "tld_categoria", catalog = "timelapse")
public class TldCategoria implements java.io.Serializable {

	private String oid;
	private TldUsuario tldUsuario;
	private String nombre;
	private String descripcion;
	private List<TldTarea> tldTareas = new ArrayList<TldTarea>(0);

	public TldCategoria() {
	}

	public TldCategoria(TldUsuario tldUsuario, String nombre) {
		this.tldUsuario = tldUsuario;
		this.nombre = nombre;
	}

	public TldCategoria(TldUsuario tldUsuario, String nombre,
			String descripcion, List<TldTarea> tldTareas) {
		this.tldUsuario = tldUsuario;
		this.nombre = nombre;
		this.descripcion = descripcion;
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
	@JoinColumn(name = "usuarioOID", nullable = false)
	public TldUsuario getTldUsuario() {
		return this.tldUsuario;
	}

	public void setTldUsuario(TldUsuario tldUsuario) {
		this.tldUsuario = tldUsuario;
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

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldCategoria")
	public List<TldTarea> getTldTareas() {
		return this.tldTareas;
	}

	public void setTldTareas(List<TldTarea> tldTareas) {
		this.tldTareas = tldTareas;
	}

}
