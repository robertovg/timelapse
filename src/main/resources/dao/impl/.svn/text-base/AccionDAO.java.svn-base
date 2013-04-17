package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IAccionDAO;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;


@Repository
public class AccionDAO extends DaoTemplate implements IAccionDAO {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<TlpAccion> findAll(String funcionalidadOID, TlpRol rol) {
		Query query = getEntityManager().createQuery(
				"select acc FROM TlpAccion acc "
						+ "where acc.tlpFuncionalidad.oid = ?  " +
						  " and acc.oid in ( " +
						  "select per.tlpAccion " +
					      "      from TlrPermiso per " +
					      "      where per.tlpRol.oid = ? " +
					      "      ) " +
						  "order by acc.tlpFuncionalidad.oid, acc.orden asc");
		query.setParameter(1, funcionalidadOID);
		query.setParameter(2, rol.getOid());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TlpAccion> findAll() {
		Query query = getEntityManager().createQuery(
				"select p FROM TlpAccion p order by p.tlpFuncionalidad.oid , p.orden asc");
		return query.getResultList();
	}
	@Transactional
	public void save(TlpAccion dacc) {
		if (dacc.getOid() == null) {
			// new
			em.persist(dacc);

		} else {
			// update
			em.merge(dacc);
		}
	}

	public TlpAccion find(String oid) {
		return em.find(TlpAccion.class, oid);
	}
	@Transactional
	public TlpAccion remove(String oid) {
		TlpAccion acc = find(oid);
		if (acc != null) {
			em.remove(acc);
		}
		return acc;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	@SuppressWarnings("unchecked")
	public List<TlpAccion> findAsociadas(TlpRol rol) {
		Query query = getEntityManager().createQuery(
		" select acc FROM TlpAccion acc, TlrPermiso per" +
		" where acc.oid = per.tlpAccion.oid" + 
		" and per.tlpRol = ?" +		
		" order by acc.orden");
		query.setParameter(1, rol);
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<TlpAccion> findNoAsociadas(TlpRol rol) {
		Query query = getEntityManager().createQuery(
		" select acc FROM TlpAccion acc" +
		" where acc.oid not in (" +
		" select per.tlpAccion.oid" +
		" from TlrPermiso per " + 
		" where per.tlpRol = ?)" +		
		" order by acc.orden");
		query.setParameter(1, rol);
		return query.getResultList();
	}

}