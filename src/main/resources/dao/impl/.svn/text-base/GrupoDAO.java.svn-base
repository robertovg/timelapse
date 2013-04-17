package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IGrupoDAO;
import timeLapse.resources.persistenceDTO.TldGrupo;

@Repository
public class GrupoDAO extends DaoTemplate implements IGrupoDAO {
	@PersistenceContext
	private EntityManager em;

	public TldGrupo find(String oid) {
		return em.find(TldGrupo.class, oid);
	}

	@SuppressWarnings("unchecked")
	public List<TldGrupo> findAllCreados(String oidUsuario) {
		Query query = em.createQuery(
				" select grupo " +
				" from TldGrupo grupo " +
				" where grupo.tldUsuario.oid like ?" +
				" order by grupo.nombre");
		query.setParameter(1, oidUsuario);
		List<TldGrupo> resul = query.getResultList();
		return resul;
	}

	@SuppressWarnings("unchecked")
	public List<TldGrupo> findAllPertenecientes(String oidUsuario) {
		Query query = em.createQuery(
				" select grupo " +
				" from TldGrupo grupo " +
				" where grupo.oid in ( " +
				" 	select usuGrupo.tldGrupo.oid " +
				" 	from TlrUsuarioGrupo usuGrupo " +
				" 	where usuGrupo.tldUsuario.oid like ? ) " +
				" or grupo.tldUsuario.oid like ? " +  
				" order by grupo.nombre");
		query.setParameter(1, oidUsuario);
		query.setParameter(2, oidUsuario);
		List<TldGrupo> resul = query.getResultList();
		return resul;
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public List<TldGrupo> findAllUtilizados(String oidUsuario) {
		Query query = em.createQuery(
				" select distinct grupo " +
				" from TldGrupo grupo, TldTarea tarea " +
				" where (grupo.oid in ( " +
				" 	select usuGrupo.tldGrupo.oid " +
				" 	from TlrUsuarioGrupo usuGrupo " +
				" 	where usuGrupo.tldUsuario.oid like ? ) " +
				" or grupo.tldUsuario.oid like ? )" +
				" and (tarea.tldUsuarioByUsuarioCreadorOid.oid like ?" +
				" or tarea.tldUsuarioByUsuarioAsociadoOid.oid like ? )" +
				" and tarea.tldGrupo.oid like grupo.oid " +				
				" order by grupo.nombre");
		query.setParameter(1, oidUsuario);
		query.setParameter(2, oidUsuario);
		query.setParameter(3, oidUsuario);
		query.setParameter(4, oidUsuario);
		List<TldGrupo> resul = query.getResultList();
		return resul;
	}
	
	@Transactional
	public TldGrupo remove(String oid) {
		TldGrupo grupo = find(oid);
		if (grupo != null) {
			em.remove(grupo);
		}
		return grupo;

	}
	@Transactional
	public void save(TldGrupo grupo) {
		if (grupo.getOid() == null) {
			// new
			em.persist(grupo);

		} else {
			// update
			em.merge(grupo);
		}

	}


}
