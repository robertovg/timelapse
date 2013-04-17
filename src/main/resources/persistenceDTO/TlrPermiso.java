package timeLapse.resources.persistenceDTO;

// Generated 13-oct-2009 23:10:27 by Hibernate Tools 3.2.4.GA

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

/**
 * 			Clase autogenerada a usando Hibernate Tools
 * 			3.2.2.GA
 * 			@author robe
 */
@Entity
@Table(name = "tlr_permiso", catalog = "timelapse")
public class TlrPermiso implements java.io.Serializable {

	private String oid;
	private TlpAccion tlpAccion;
	private TlpRol tlpRol;

	public TlrPermiso() {
	}

	public TlrPermiso(String oid, TlpAccion tlpAccion, TlpRol tlpRol) {
		this.oid = oid;
		this.tlpAccion = tlpAccion;
		this.tlpRol = tlpRol;
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
	@JoinColumn(name = "tld_accion_OID", nullable = false)
	public TlpAccion getTlpAccion() {
		return this.tlpAccion;
	}

	public void setTlpAccion(TlpAccion tlpAccion) {
		this.tlpAccion = tlpAccion;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tld_rol_oid", nullable = false)
	public TlpRol getTlpRol() {
		return this.tlpRol;
	}

	public void setTlpRol(TlpRol tlpRol) {
		this.tlpRol = tlpRol;
	}

}
