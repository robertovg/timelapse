package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IFuncionalidadDAO;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpRol;


@Repository
public class FuncionalidadDAO extends DaoTemplate implements IFuncionalidadDAO {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public TlpFuncionalidad find(String oid) {
		return em.find(TlpFuncionalidad.class, oid);
	}

	@SuppressWarnings("unchecked")
	public List<TlpFuncionalidad> findAll(String objOID, TlpRol rol) {
		Query query = getEntityManager()
				.createQuery(
					      " select fun" +
					      " from TlpFuncionalidad fun " +
					      " where fun.tlpObjetivo.oid like ? " +
					      " and fun.oid in ( " +
					      " select acc.tlpFuncionalidad " +
					      "     from TlpAccion acc " +
					      "     where acc.oid in (select per.tlpAccion " +
					      "      from TlrPermiso per " +
					      "      where per.tlpRol.oid = ?) " +
					      "      ) " +
					      "     order by fun.orden desc ");						
						
		query.setParameter(1, objOID);
		query.setParameter(2, rol.getOid());
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<TlpFuncionalidad> findAll() {
		Query query = getEntityManager().createQuery(
				"select p FROM TlpFuncionalidad p order by p.tlpObjetivo, p.orden ");
		return query.getResultList();
	}
	@Transactional
	public void save(TlpFuncionalidad dfunc) {
		if (dfunc.getOid() == null) {
			// new
			em.persist(dfunc);

		} else {
			// update
			em.merge(dfunc);
		}
	}
	@Transactional
	public TlpFuncionalidad remove(String oid) {
		TlpFuncionalidad fun = find(oid);
		if (fun != null) {
			em.remove(fun);
		}
		return fun;
	}

	private EntityManager getEntityManager() {
		return em;
	}

}