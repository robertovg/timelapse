package timeLapse.resources.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.persistenceDTO.TldUsuario;


@Repository
public class UsuarioDAO extends DaoTemplate implements IUsuarioDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	   
	@SuppressWarnings("unchecked")
	public List<TldUsuario> findAllNoGrupo(String oidGrupo) {
		Query query = em
				.createQuery("select u " +
						"FROM TldUsuario u " +
						"where u.oid not in ( " +
						"	select g.tldUsuario.oid " +
						"	from TldGrupo g " +
						"	where g.oid like ?1 ) " +
						"and u.oid not in ( " +
						"	select r.tldUsuario.oid" +
						"	from TlrUsuarioGrupo r " +
						"	where r.tldGrupo.oid like ?2 ) " + 
						"order by u.nombreUsuario");
		query.setParameter(1, oidGrupo);
		query.setParameter(2, oidGrupo);
		
		return query.getResultList();
	}

	
	public TldUsuario findByNombreUsuarioYPassword(String nombreUsuario,
			String passwordMD5) throws NonUniqueResultException, NoResultException{
		TldUsuario objResul = null;
		
		Query query = em.createQuery("select p FROM TldUsuario p "
				+ "where p.nombreUsuario = ?1  " 
				+ "and p.contrasenna = ?2  ");
		
		
		query.setParameter(1, nombreUsuario);
		query.setParameter(2, passwordMD5);
		
		objResul = (TldUsuario)query.getSingleResult();
		
		
		return objResul;

	}
	
	@SuppressWarnings("unchecked")
	public List<TldUsuario> findByNombre(String nombreUsuario) {
		List<TldUsuario> listResul = null;
		
		Query query = em.createQuery("select p FROM TldUsuario p "
				+ "where p.nombreUsuario = ?1  ");
		
		
		query.setParameter(1, nombreUsuario);		
		
		listResul = (List<TldUsuario>)query.getResultList();
		
		return listResul;
	}

	@Transactional
	public void save(TldUsuario usuario) {
		if (usuario.getOid() == null) {
			// new
			em.persist(usuario);

		} else {
			// update
			em.merge(usuario);
		}
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TldUsuario> findAllGrupo(String oidGrupo, String oidUsuarioRegistrado) {
		List<TldUsuario> listaResul = null;
		
		Query query = em
				.createQuery("select u " +
						"FROM TldUsuario u " +
						"where u.oid in ( " +
						"	select r.tldUsuario.oid" +
						"	from TlrUsuarioGrupo r, TldGrupo g " +
						"	where r.tldGrupo.oid like ?1" +
						" 	and r.tldGrupo.oid like g.oid" +
						"	and (g.tldUsuario.oid like ?2" +
						"		or r.tldUsuario.oid like ?3 ) ) " + 
						"order by u.nombreUsuario");
	
		
		query.setParameter(1, oidGrupo);
		query.setParameter(2, oidUsuarioRegistrado);
		query.setParameter(3, oidUsuarioRegistrado);
		
		listaResul = query.getResultList();
		
		
		return listaResul;
	}


	@Override
	public TldUsuario findById(String oidUsuario) {
		return em.find(TldUsuario.class, oidUsuario);
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<TldUsuario> findAllGrupoIncluyeCreador(String oidGrupo,
			String oidUsuarioRegistrado) {
		List<TldUsuario> listaResul = null;
		
		Query query = em
				.createQuery("select distinct u " +
						"FROM TldUsuario u " +
						"where u.oid in ( " +
						"	select r.tldUsuario.oid" +
						"	from TlrUsuarioGrupo r, TldGrupo g " +
						"	where r.tldGrupo.oid like ?1" +
						" 	and r.tldGrupo.oid like g.oid" +
						"	and (g.tldUsuario.oid like ?2" +
						"		or r.tldUsuario.oid like ?3 ) ) " +
						"or u.oid = (select gp.tldUsuario.oid " +
						"			from TldGrupo gp " +
						"			where gp.oid like ?4 " +
						"			and gp.tldUsuario.oid like ?5)" + 
						"order by u.nombreUsuario");
	
		
		query.setParameter(1, oidGrupo);
		query.setParameter(2, oidUsuarioRegistrado);
		query.setParameter(3, oidUsuarioRegistrado);
		query.setParameter(4, oidGrupo);
		query.setParameter(5, oidUsuarioRegistrado);

		listaResul = query.getResultList();
		
		
		return listaResul;
		
	}
	

}
