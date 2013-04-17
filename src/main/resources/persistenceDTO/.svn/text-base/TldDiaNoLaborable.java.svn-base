package timeLapse.resources.persistenceDTO;

// Generated 13-oct-2009 23:10:27 by Hibernate Tools 3.2.4.GA

import java.util.Date;
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
@Table(name = "tld_dia_no_laborable", catalog = "timelapse")
public class TldDiaNoLaborable implements java.io.Serializable {

	private String oid;
	private TlpTipoDiaNoLaborable tlpTipoDiaNoLaborable;
	private Date fecha;

	public TldDiaNoLaborable() {
	}

	public TldDiaNoLaborable(TlpTipoDiaNoLaborable tlpTipoDiaNoLaborable,
			Date fecha) {
		this.tlpTipoDiaNoLaborable = tlpTipoDiaNoLaborable;
		this.fecha = fecha;
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
	@JoinColumn(name = "tipoDiaOID", nullable = false)
	public TlpTipoDiaNoLaborable getTlpTipoDiaNoLaborable() {
		return this.tlpTipoDiaNoLaborable;
	}

	public void setTlpTipoDiaNoLaborable(
			TlpTipoDiaNoLaborable tlpTipoDiaNoLaborable) {
		this.tlpTipoDiaNoLaborable = tlpTipoDiaNoLaborable;
	}

	@Column(name = "fecha", nullable = false, length = 10)
	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

}
