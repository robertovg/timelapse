package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IObjetivoDAO;
import timeLapse.resources.persistenceDTO.TlpObjetivo;
import timeLapse.resources.persistenceDTO.TlpRol;


@Repository
public class ObjetivoDAO extends DaoTemplate implements IObjetivoDAO {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	

	@SuppressWarnings("unchecked")
	public List<TlpObjetivo> findAll() {
		Query query = getEntityManager().createQuery(
				"select p FROM TlpObjetivo p order by p.orden");
		List<TlpObjetivo> resul = query.getResultList();
		return resul;
	
	}
	@SuppressWarnings("unchecked")
	public List<TlpObjetivo> findAll(TlpRol rol) {
		Query query = getEntityManager().createQuery(
				" select obj " +
				" from TlpObjetivo obj " +
				" where obj.oid in ( " +
				" 	select fun.tlpObjetivo " +
				" 	from TlpFuncionalidad fun " +
				" 	where fun.oid in ( " +
				"       select acc.tlpFuncionalidad " +
				" 		from TlpAccion acc " +
				" 		where acc.oid in (select per.tlpAccion " +
				" 		from  TlrPermiso per " +
				" 		where per.tlpRol.oid = ?) " +
				" 		) " +
				" 	) " +
				" order by obj.orden");
		query.setParameter(1, rol.getOid());
		List<TlpObjetivo> resul = query.getResultList();
		return resul;
	
	}
	@Transactional
	public void save(TlpObjetivo TlpObjetivo) {
		if (TlpObjetivo.getOid() == null) {
			// new
			em.persist(TlpObjetivo);

		} else {
			// update
			em.merge(TlpObjetivo);
		}
	}
	@Transactional
	public TlpObjetivo remove(String oid) {
		TlpObjetivo obj = find(oid);
		if (obj != null) {
			em.remove(obj);
		}
		return obj;
	}

	private EntityManager getEntityManager() {
		return em;
	}

	public TlpObjetivo find(String oid) {
		return em.find(TlpObjetivo.class, oid);
	}

}