package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.ICategoriaDAO;
import timeLapse.resources.persistenceDTO.TldCategoria;

@Repository
public class CategoriaDAO extends DaoTemplate implements ICategoriaDAO {
	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}

	public TldCategoria find(String oid) {
		return em.find(TldCategoria.class, oid);
	}

	@SuppressWarnings("unchecked")
	public List<TldCategoria> findAll(String oidUsuario) {
		Query query = em.createQuery(
		"select c FROM TldCategoria c where c.tldUsuario.oid like ? order by c.oid asc");
		query.setParameter(1, oidUsuario);
		return query.getResultList();
	}
	@Transactional
	public TldCategoria remove(String oid) {
		TldCategoria acc = find(oid);
		if (acc != null) {
			em.remove(acc);
		}
		return acc;

	}
	@Transactional
	public void save(TldCategoria categoria) {
		if (categoria.getOid() == null) {
			// new
			em.persist(categoria);

		} else {
			// update
			em.merge(categoria);
		}

	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TldCategoria> findAllUtilizados(String oidUsuario) {
		Query query = em.createQuery(
		" select distinct c FROM TldCategoria c, TldTarea tarea" +
		" where c.tldUsuario.oid like ?" +
		" and (tarea.tldUsuarioByUsuarioCreadorOid.oid like ?" +
		" or tarea.tldUsuarioByUsuarioAsociadoOid.oid like ? )" +
		" and tarea.tldCategoria.oid like c.oid" + 
		" order by c.oid asc");
		query.setParameter(1, oidUsuario);
		query.setParameter(2, oidUsuario);
		query.setParameter(3, oidUsuario);
		return query.getResultList();
	}

}
