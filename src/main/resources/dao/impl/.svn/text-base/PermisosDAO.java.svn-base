package timeLapse.resources.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.IPermisosDAO;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.resources.persistenceDTO.TlrPermiso;

@Repository
public class PermisosDAO extends DaoTemplate implements IPermisosDAO {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	public void desvinculaRolYAcciones(TlpRol rol, List<TlpAccion> acciones) {
		
		//Busco los permisos asociados al rol y acciones pasadas como parámetro
		List<TlrPermiso> permisosABorrar = findAll(rol, acciones);
		
		//Luego los borro uno a uno
		if(permisosABorrar != null){
			for(TlrPermiso permiso : permisosABorrar){
				em.remove(permiso);
				
			}
		}
	}
	@Transactional
	public void vinculaRolYAcciones(TlpRol rol, List<TlpAccion> acciones) {
		//Creo un nuevo permiso por cada acción que se haya seleccionado para el mismo rol
		for(TlpAccion accion : acciones){
			TlrPermiso permiso = new TlrPermiso();
			permiso.setTlpRol(rol);
			permiso.setTlpAccion(accion);
			em.persist(permiso);
		}
		

	}
	@SuppressWarnings("unchecked")
	public List<TlrPermiso> findAll(TlpRol rol, List<TlpAccion> acciones) {
		String consulta = "select per from TlrPermiso per where per.tlpRol.oid = ? and per.tlpAccion.oid in (";
		
		
		Iterator<TlpAccion> it = acciones.iterator();	
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
		query.setParameter(1, rol.getOid());
		it = acciones.iterator();
		int i = 2;
		while (it.hasNext()){
			TlpAccion acc = it.next(); 
			query.setParameter(i++, acc.getOid());			
			
		}
		return query.getResultList();
	}
	@SuppressWarnings("unchecked")
	public List<TlrPermiso> findAllPorNombre(TlpRol rol, List<String> acciones) {
		String consulta = "select per from TlrPermiso per where per.tlpRol.oid = ? and per.tlpAccion.path in (";
		
		
		Iterator<String> it = acciones.iterator();	
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
		query.setParameter(1, rol.getOid());
		it = acciones.iterator();
		int i = 2;
		while (it.hasNext()){
			String acc = it.next(); 
			query.setParameter(i++, acc);			
			
		}
		return query.getResultList();
	}
	

}
