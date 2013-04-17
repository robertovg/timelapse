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
@Table(name = "tld_usuario", catalog = "timelapse")
public class TldUsuario implements java.io.Serializable {

	private String oid;
	private TlpRol tlpRol;
	private String nombreUsuario;
	private String nombrePropio;
	private String apellido1;
	private String apellido2;
	private String contrasenna;
	private List<TldGrupo> tldGrupos = new ArrayList<TldGrupo>(0);
	private List<TldTarea> tldTareasForUsuarioAsociadoOid = new ArrayList<TldTarea>(
			0);
	private List<TldCategoria> tldCategorias = new ArrayList<TldCategoria>(0);
	private List<TlrUsuarioGrupo> tlrUsuarioGrupos = new ArrayList<TlrUsuarioGrupo>(
			0);
	private List<TldTarea> tldTareasForUsuarioCreadorOid = new ArrayList<TldTarea>(
			0);

	public TldUsuario() {
	}

	public TldUsuario(TlpRol tlpRol, String nombreUsuario, String nombrePropio,
			String apellido1, String contrasenna) {
		this.tlpRol = tlpRol;
		this.nombreUsuario = nombreUsuario;
		this.nombrePropio = nombrePropio;
		this.apellido1 = apellido1;
		this.contrasenna = contrasenna;
	}

	public TldUsuario(TlpRol tlpRol, String nombreUsuario, String nombrePropio,
			String apellido1, String apellido2, String contrasenna,
			List<TldGrupo> tldGrupos,
			List<TldTarea> tldTareasForUsuarioAsociadoOid,
			List<TldCategoria> tldCategorias,
			List<TlrUsuarioGrupo> tlrUsuarioGrupos,
			List<TldTarea> tldTareasForUsuarioCreadorOid) {
		this.tlpRol = tlpRol;
		this.nombreUsuario = nombreUsuario;
		this.nombrePropio = nombrePropio;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.contrasenna = contrasenna;
		this.tldGrupos = tldGrupos;
		this.tldTareasForUsuarioAsociadoOid = tldTareasForUsuarioAsociadoOid;
		this.tldCategorias = tldCategorias;
		this.tlrUsuarioGrupos = tlrUsuarioGrupos;
		this.tldTareasForUsuarioCreadorOid = tldTareasForUsuarioCreadorOid;
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
	@JoinColumn(name = "rolOID", nullable = false)
	public TlpRol getTlpRol() {
		return this.tlpRol;
	}

	public void setTlpRol(TlpRol tlpRol) {
		this.tlpRol = tlpRol;
	}

	@Column(name = "nombreUsuario", nullable = false, length = 50)
	public String getNombreUsuario() {
		return this.nombreUsuario;
	}

	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}

	@Column(name = "nombrePropio", nullable = false, length = 100)
	public String getNombrePropio() {
		return this.nombrePropio;
	}

	public void setNombrePropio(String nombrePropio) {
		this.nombrePropio = nombrePropio;
	}

	@Column(name = "apellido1", nullable = false, length = 100)
	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	@Column(name = "apellido2", length = 100)
	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	@Column(name = "contrasenna", nullable = false, length = 100)
	public String getContrasenna() {
		return this.contrasenna;
	}

	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldUsuario")
	public List<TldGrupo> getTldGrupos() {
		return this.tldGrupos;
	}

	public void setTldGrupos(List<TldGrupo> tldGrupos) {
		this.tldGrupos = tldGrupos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldUsuarioByUsuarioAsociadoOid")
	public List<TldTarea> getTldTareasForUsuarioAsociadoOid() {
		return this.tldTareasForUsuarioAsociadoOid;
	}

	public void setTldTareasForUsuarioAsociadoOid(
			List<TldTarea> tldTareasForUsuarioAsociadoOid) {
		this.tldTareasForUsuarioAsociadoOid = tldTareasForUsuarioAsociadoOid;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldUsuario")
	public List<TldCategoria> getTldCategorias() {
		return this.tldCategorias;
	}

	public void setTldCategorias(List<TldCategoria> tldCategorias) {
		this.tldCategorias = tldCategorias;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldUsuario")
	public List<TlrUsuarioGrupo> getTlrUsuarioGrupos() {
		return this.tlrUsuarioGrupos;
	}

	public void setTlrUsuarioGrupos(List<TlrUsuarioGrupo> tlrUsuarioGrupos) {
		this.tlrUsuarioGrupos = tlrUsuarioGrupos;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldUsuarioByUsuarioCreadorOid")
	public List<TldTarea> getTldTareasForUsuarioCreadorOid() {
		return this.tldTareasForUsuarioCreadorOid;
	}

	public void setTldTareasForUsuarioCreadorOid(
			List<TldTarea> tldTareasForUsuarioCreadorOid) {
		this.tldTareasForUsuarioCreadorOid = tldTareasForUsuarioCreadorOid;
	}

}
