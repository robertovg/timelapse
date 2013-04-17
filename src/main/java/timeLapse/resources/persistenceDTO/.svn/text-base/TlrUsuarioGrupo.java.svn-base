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
 * 	
 */
@Entity
@Table(name = "tlr_usuario_grupo", catalog = "timelapse")
public class TlrUsuarioGrupo implements java.io.Serializable {

	private String oid;
	private TldGrupo tldGrupo;
	private TldUsuario tldUsuario;

	public TlrUsuarioGrupo() {
	}

	public TlrUsuarioGrupo(TldGrupo tldGrupo, TldUsuario tldUsuario) {
		this.tldGrupo = tldGrupo;
		this.tldUsuario = tldUsuario;
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
	@JoinColumn(name = "grupoOID", nullable = false)
	public TldGrupo getTldGrupo() {
		return this.tldGrupo;
	}

	public void setTldGrupo(TldGrupo tldGrupo) {
		this.tldGrupo = tldGrupo;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "usuarioOID", nullable = false)
	public TldUsuario getTldUsuario() {
		return this.tldUsuario;
	}

	public void setTldUsuario(TldUsuario tldUsuario) {
		this.tldUsuario = tldUsuario;
	}

}
