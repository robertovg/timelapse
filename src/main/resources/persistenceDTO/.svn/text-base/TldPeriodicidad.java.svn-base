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
@Table(name = "tld_periodicidad", catalog = "timelapse")
public class TldPeriodicidad implements java.io.Serializable {

	private String oid;
	private TlpUnidadRepeticion tlpUnidadRepeticion;
	private Date fechaInicio;
	private Date fechaFin;
	private Integer cada;
	private Integer omitirDiasNoLaborables;
	private List<TldTarea> tldTareas = new ArrayList<TldTarea>(0);

	public TldPeriodicidad() {
	}

	public TldPeriodicidad(TlpUnidadRepeticion tlpUnidadRepeticion,
			Date fechaInicio, Integer cada, Integer omitirDiasNoLaborables) {
		this.tlpUnidadRepeticion = tlpUnidadRepeticion;
		this.fechaInicio = fechaInicio;
		this.cada = cada;
		this.omitirDiasNoLaborables = omitirDiasNoLaborables;
	}

	public TldPeriodicidad(TlpUnidadRepeticion tlpUnidadRepeticion,
			Date fechaInicio, Date fechaFin, Integer cada,
			Integer omitirDiasNoLaborables, List<TldTarea> tldTareas) {
		this.tlpUnidadRepeticion = tlpUnidadRepeticion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.cada = cada;
		this.omitirDiasNoLaborables = omitirDiasNoLaborables;
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
	@JoinColumn(name = "unidadDeRepeticionOID", nullable = false)
	public TlpUnidadRepeticion getTlpUnidadRepeticion() {
		return this.tlpUnidadRepeticion;
	}

	public void setTlpUnidadRepeticion(TlpUnidadRepeticion tlpUnidadRepeticion) {
		this.tlpUnidadRepeticion = tlpUnidadRepeticion;
	}

	@Column(name = "fechaInicio", nullable = false, length = 10)
	public Date getFechaInicio() {
		return this.fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "fechaFin", length = 10)
	public Date getFechaFin() {
		return this.fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	@Column(name = "cada", nullable = false)
	public Integer getCada() {
		return this.cada;
	}

	public void setCada(Integer cada) {
		this.cada = cada;
	}

	@Column(name = "omitirDiasNoLaborables", nullable = false)
	public Integer getOmitirDiasNoLaborables() {
		return this.omitirDiasNoLaborables;
	}

	public void setOmitirDiasNoLaborables(Integer omitirDiasNoLaborables) {
		this.omitirDiasNoLaborables = omitirDiasNoLaborables;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "tldPeriodicidad")
	public List<TldTarea> getTldTareas() {
		return this.tldTareas;
	}

	public void setTldTareas(List<TldTarea> tldTareas) {
		this.tldTareas = tldTareas;
	}

}
