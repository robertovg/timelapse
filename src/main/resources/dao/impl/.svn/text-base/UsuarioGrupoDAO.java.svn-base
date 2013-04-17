package timeLapse.resources.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IUsuarioGrupoDAO;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlrUsuarioGrupo;

@Repository
public class UsuarioGrupoDAO extends DaoTemplate implements IUsuarioGrupoDAO {
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public void desvinculaUsuarioGrupo(TldGrupo grupo, List<TldUsuario> usuarios) {
		//Busco las relaciones usuario grupos
		List<TlrUsuarioGrupo> usuarioPermisoABorrar = findAll(grupo, usuarios);
		
		//Luego los borro uno a uno
		if(usuarioPermisoABorrar != null){
			for(TlrUsuarioGrupo usuarioGrupo : usuarioPermisoABorrar){
				em.remove(usuarioGrupo);
				
			}
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<TlrUsuarioGrupo> findAll(TldGrupo grupo,
			List<TldUsuario> usuarios) {
		String consulta = "select usu from TlrUsuarioGrupo usu where usu.tldGrupo.oid = ? and usu.tldUsuario.oid in (";
		
		
		Iterator<TldUsuario> it = usuarios.iterator();	
		while (it.hasNext()){
			it.next(); 
			if(it.hasNext()){
				consulta += " ?,";
			}else{
				consulta += " ? )";
			}
			
		}
		
		Query query = em
				.createQuery(consulta);
		query.setParameter(1, grupo.getOid());
		it = usuarios.iterator();
		int i = 2;
		while (it.hasNext()){
			TldUsuario usuarioIterado = it.next(); 
			query.setParameter(i++, usuarioIterado.getOid());			
			
		}
		return query.getResultList();
	}

	@Override
	@Transactional
	public void vinculaUsuarioGrupo(TldGrupo grupo, List<TldUsuario> usuarios) {
		//Creo una nueva tupla por cada usuario pasado como parámetro
		for(TldUsuario usuarioIterado : usuarios){
			TlrUsuarioGrupo usuarioGrupoBD = new TlrUsuarioGrupo();
			usuarioGrupoBD.setTldGrupo(grupo);
			usuarioGrupoBD.setTldUsuario(usuarioIterado);
			
			em.persist(usuarioGrupoBD);
		}
		
	}
	

}
