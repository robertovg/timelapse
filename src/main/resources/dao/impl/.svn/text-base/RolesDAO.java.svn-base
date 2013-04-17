package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IRolesDAO;
import timeLapse.resources.persistenceDTO.TlpRol;

@Repository
public class RolesDAO extends DaoTemplate implements IRolesDAO {
	private EntityManager em;

	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	public List<TlpRol> findAll() {
		Query query = em
		.createQuery("select p FROM TlpRol p order by p.nombre");
		return query.getResultList();
	}

}
