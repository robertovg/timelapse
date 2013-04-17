/**
 * 
 */
package timeLapse.resources.dao.impl;

import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import timeLapse.resources.dao.DaoTemplate;
import timeLapse.resources.dao.ITareaDAO;
import timeLapse.resources.dto.FiltroTareasDTO;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.EstadoFilros;
import timeLapse.util.constantes.OrdenTareas;

/**
 * @author robe
 *
 */
@Repository
public class TareaDAO extends DaoTemplate implements ITareaDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	/* (non-Javadoc)
	 * @see timeLapse.resources.dao.ITareaDAO#find(java.lang.String)
	 */
	public TldTarea find(String oid) {
		return em.find(TldTarea.class, oid);

	}

	/* (non-Javadoc)
	 * @see timeLapse.resources.dao.ITareaDAO#findAll(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public List<TldTarea> findAll(FiltroTareasDTO filtro) {
		String oidUsuario = filtro.getOidUsuario();
		OrdenTareas ordenPorFechas = filtro.getOrden();
		String cadConsulta = 
				" select tarea" +
				" from TldTarea tarea " +
				" where 1 = 1 ";
		if(oidUsuario != null){
			
			cadConsulta += " and ";
			if(filtro.isIncluyeNoCreadas()){
				cadConsulta += "( ";
			}
			
			cadConsulta += "tarea.tldUsuarioByUsuarioCreadorOid.oid like ? ";
			if(filtro.isIncluyeNoCreadas()){
				cadConsulta += " or tarea.tldUsuarioByUsuarioAsociadoOid.oid like ? )";
			}
		}
			
				/*
				 * Exclusiones/Inclusiones 
				 */
				//tareasRealizadas
				switch (filtro.getTareasRealizadas()) {
				case excluir:
					cadConsulta += " and tarea.fechaRealizacion is null ";
					break;
				case exclusivo:
					cadConsulta += " and tarea.fechaRealizacion is not null ";
					break;
				}
				//Temporizadas
				switch (filtro.getTemporizadas()) {
				case excluir:
					cadConsulta += " and tarea.fechaInicio = ?" +
							" and tarea.fechaFin = ?";
					break;
				case exclusivo:
					cadConsulta += " and (tarea.fechaInicio <> ?" +
					" or tarea.fechaFin <> ?) "; 
					break;
				}
				
				//TareasPadre
				switch (filtro.getTareasQueSonPadre()) {
				case excluir:
					cadConsulta += " and tarea.oid not in (select distinct tareaHija.tldTarea.oid" +
							" from TldTarea tareaHija " +
							" where tareaHija.oid != tarea.oid " +
							" and tareaHija.tldTarea.oid = tarea.oid)";
					
					break;
				case exclusivo:
					cadConsulta += " and tarea.oid in (select distinct tareaHija.tldTarea.oid" +
							" from TldTarea tareaHija " +
							" where tareaHija.oid != tarea.oid " +
							" and tareaHija.tldTarea.oid = tarea.oid)";
					break;
				}
				//Autorrealizables
				switch (filtro.getAutorrealizables()) {
				case excluir:
					cadConsulta += " and tarea.autorrealizable = 0";
					
					break;
				case exclusivo:
					cadConsulta += " and tarea.autorrealizable = 1";
					break;
				}
				//Periodicas
				switch (filtro.getPeriodicas()) {
				case excluir:
					cadConsulta += " and tarea.tldPeriodicidad is null ";
					
					break;
				case exclusivo:
					cadConsulta += " and tarea.tldPeriodicidad is not null ";
					break;
				}
				//Tareas vinculadas por el usuarios
				switch (filtro.getVinculadasUsuario()) {
				case excluir:
					cadConsulta += " and tarea.tldUsuarioByUsuarioAsociadoOid is null ";
					
					break;
				case exclusivo:
					cadConsulta += " and tarea.tldUsuarioByUsuarioAsociadoOid is not null ";
					break;
				}
				
				//Recorro las cateogrías y las pongo como restricciones 
				/*
				 * Asociaciones 
				 */
				for(String categoria : filtro.getCategorias() ){
					
					if(categoria.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldCategoria is null ";
						
					}else if(categoria.equals(VALOR_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldCategoria is not null ";
						
					}else if(!categoria.equals(VALOR_TODAS_EN_LISTAS)){					
						
						cadConsulta += " and tarea.tldCategoria.oid like ? ";
					}
				}
				
				for(String grupos : filtro.getGrupos()){
					
					if(grupos.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldGrupo is null ";
						
					}else if(grupos.equals(VALOR_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldGrupo is not null ";
						
					}else if(!grupos.equals(VALOR_TODAS_EN_LISTAS)){						
						
						cadConsulta += " and tarea.tldGrupo.oid like ? ";
					}
					
				}
				for(String padres : filtro.getTareasPadres()){
					
					if(padres.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldTarea is null ";
						
					}else if(padres.equals(VALOR_ASOCIADAS_EN_LISTAS)){
						
						cadConsulta += " and tarea.tldTarea is not null ";
						
					}else if(!padres.equals(VALOR_TODAS_EN_LISTAS)){							
						
						cadConsulta += " and tarea.tldTarea.oid like ? ";
					}
				}
				/*
				 * Fechas 
				 */
				//Fecha Planificación
				if(filtro.getFechaPlanificacionInferior() != null && filtro.getFechaPlanificacionSuperior() != null){
					cadConsulta += " and ( ((tarea.fechaInicio <> ? and tarea.fechaFin <> ?) and ( " +
							" (? <= tarea.fechaInicio and ? >= tarea.fechaInicio) " +
							" or  (? > tarea.fechaInicio and ? <= tarea.fechaFin))) " +
							" or ((tarea.fechaInicio = ? and tarea.fechaFin <> ?) and ( " +
							" ? <= tarea.fechaFin)) " + 
							" or ((tarea.fechaInicio <> ? and tarea.fechaFin = ?) and ( " +
							"	? <= tarea.fechaInicio)) )";
				}else if(filtro.getFechaPlanificacionInferior() != null){
					cadConsulta += " and ( ((tarea.fechaInicio <> ? and tarea.fechaFin <> ?) and ( " +
							" ? <= tarea.fechaFin)) " +							
							" or ((tarea.fechaInicio = ? and tarea.fechaFin <> ?) and ( " +
							" ? <= tarea.fechaFin)) " + 
							" or (tarea.fechaInicio <> ? and tarea.fechaFin = ?) )";
				}else if(filtro.getFechaPlanificacionSuperior() != null){
					cadConsulta += " and ( ((tarea.fechaInicio <> ? and tarea.fechaFin <> ?) and ( " +
							" ? >= tarea.fechaInicio)) " +
							" or (tarea.fechaInicio = ? and tarea.fechaFin <> ?) " + 
							" or ((tarea.fechaInicio <> ? and tarea.fechaFin = ?) and ( " +
							"	? >= tarea.fechaInicio)) )";
				}
				//Fecha realización
				if(filtro.getFechaRealizacionInferior() != null && filtro.getFechaRealizacionSuperior() != null){
					cadConsulta += " and ( tarea.fechaRealizacion is not null " +
							" and ? <= tarea.fechaRealizacion and ? >= tarea.fechaRealizacion)";
				}else if(filtro.getFechaRealizacionInferior() != null){
					cadConsulta += " and ( tarea.fechaRealizacion is not null " +
						" and ? <= tarea.fechaRealizacion)";
				}else if(filtro.getFechaRealizacionSuperior() != null){
					cadConsulta += " and ( tarea.fechaRealizacion is not null " +
					" and ? >= tarea.fechaRealizacion)";
				}
				//Fecha creación
				if(filtro.getFechaCreacionInferior() != null && filtro.getFechaCreacionSuperior() != null){
					cadConsulta += " and  (? <= tarea.fechaCreacion and ? >= tarea.fechaCreacion) ";
				}else if(filtro.getFechaCreacionInferior() != null){
					cadConsulta += " and ? <= tarea.fechaCreacion ";
				}else if(filtro.getFechaCreacionSuperior() != null){
					cadConsulta += " and ? >= tarea.fechaCreacion ";
				}
				
				
				//CadenaContiene
				if(!StringUtils.isBlank(filtro.getCadenaContiene())){
					cadConsulta += " and ( tarea.nombre like ? " +
					" or tarea.descripcion like ? ) ";
				}
				//CadenaNoContiene
				if(!StringUtils.isBlank(filtro.getCadenaNoContiene())){
					cadConsulta += " and not( tarea.nombre like ? " +
					" or tarea.descripcion like ? ) ";
				}
				//CadenaNoContiene
				if(!StringUtils.isBlank(filtro.getUbicacion())){
					cadConsulta += " and tarea.localizacionAsociada like ? ";
				}
				//listOidTareasEspecificas
				if(!filtro.getListOidTareasEspecificas().isEmpty()){
					
					cadConsulta += " and tarea.oid in ( ";
					Iterator<String> it = filtro.getListOidTareasEspecificas().iterator();
					while(it.hasNext()){				
						@SuppressWarnings("unused")
						String oidIterado = it.next();
						cadConsulta += " ?";
						if(it.hasNext()){
							cadConsulta += ", ";
						}
					}
					cadConsulta += " ) ";
				}
				
				if(ordenPorFechas.equals(OrdenTareas.fecha)){
					cadConsulta += " order by tarea.fechaFin asc, tarea.horaFin asc, tarea.fechaInicio asc, tarea.horaInicio asc, tarea.importancia desc, tarea.fechaCreacion asc"; 
				}else if(ordenPorFechas.equals(OrdenTareas.importancia)){
					cadConsulta += " order by tarea.importancia desc, tarea.fechaFin asc, tarea.horaFin asc, tarea.fechaInicio asc, tarea.horaInicio asc, tarea.fechaCreacion asc";
				}
		
		//Añado los valores a la consulta
		Query query = em.createQuery(cadConsulta);		
		int i = 1;	
		if(oidUsuario != null){
			query.setParameter(i++, oidUsuario);
			if(filtro.isIncluyeNoCreadas()){
				query.setParameter(i++, oidUsuario);
			}
		}
		if(filtro.getTemporizadas().equals(EstadoFilros.excluir) || filtro.getTemporizadas().equals(EstadoFilros.exclusivo)){
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);

		}
		for(String categoria : filtro.getCategorias() ){
			
			if(!categoria.equals(VALOR_TODAS_EN_LISTAS) && !categoria.equals(VALOR_ASOCIADAS_EN_LISTAS) 
					&& !categoria.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
				query.setParameter(i++, categoria);
			}
			
		}
		for(String grupos : filtro.getGrupos()){
			if(!grupos.equals(VALOR_TODAS_EN_LISTAS) && !grupos.equals(VALOR_ASOCIADAS_EN_LISTAS) 
					&& !grupos.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
				query.setParameter(i++, grupos);
			}
		}
		for(String padres : filtro.getTareasPadres()){
			if(!padres.equals(VALOR_TODAS_EN_LISTAS) && !padres.equals(VALOR_ASOCIADAS_EN_LISTAS) 
					&& !padres.equals(VALOR_NO_ASOCIADAS_EN_LISTAS)){
				query.setParameter(i++, padres);
			}
		}
		if(filtro.getFechaPlanificacionInferior() != null && filtro.getFechaPlanificacionSuperior() != null){
			
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, filtro.getFechaPlanificacionSuperior());
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionSuperior());
						
		}else if(filtro.getFechaPlanificacionInferior() != null){
	
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionInferior());
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			
		}else if(filtro.getFechaPlanificacionSuperior() != null){
			
			
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionSuperior());
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, FECHA_NULA);
			query.setParameter(i++, filtro.getFechaPlanificacionSuperior());

			

		}
		
		if(filtro.getFechaRealizacionInferior() != null && filtro.getFechaRealizacionSuperior() != null){
			query.setParameter(i++, filtro.getFechaRealizacionInferior());
			query.setParameter(i++,  filtro.getFechaRealizacionSuperior());			
			
		}else if(filtro.getFechaRealizacionInferior() != null){
			
			query.setParameter(i++, filtro.getFechaRealizacionInferior());			
		}else if(filtro.getFechaRealizacionSuperior() != null){
						
			query.setParameter(i++,  filtro.getFechaRealizacionSuperior());
		}
		
		if(filtro.getFechaCreacionInferior() != null && filtro.getFechaCreacionSuperior() != null){
			query.setParameter(i++, filtro.getFechaCreacionInferior());
			query.setParameter(i++,  filtro.getFechaCreacionSuperior());			
			
		}else if(filtro.getFechaCreacionInferior() != null){
			
			query.setParameter(i++, filtro.getFechaCreacionInferior());			
		}else if(filtro.getFechaCreacionSuperior() != null){
						
			query.setParameter(i++,  filtro.getFechaCreacionSuperior());
		}
		
		//CadenaBusqueda
		if(!StringUtils.isBlank(filtro.getCadenaContiene())){
			query.setParameter(i++, "%" + filtro.getCadenaContiene() + "%");
			query.setParameter(i++, "%" + filtro.getCadenaContiene() + "%");
		}
		//CadenaBusqueda
		if(!StringUtils.isBlank(filtro.getCadenaNoContiene())){
			query.setParameter(i++, "%" + filtro.getCadenaNoContiene() + "%");
			query.setParameter(i++, "%" + filtro.getCadenaNoContiene() + "%");
		}
		//CadenaBusqueda
		if(!StringUtils.isBlank(filtro.getUbicacion())){
			query.setParameter(i++, "%" + filtro.getUbicacion() + "%");
			
		}
		
		//listOidTareasEspecificas
		if(!filtro.getListOidTareasEspecificas().isEmpty()){
			
			Iterator<String> it = filtro.getListOidTareasEspecificas().iterator();
			while(it.hasNext()){				
				String oidIterado = it.next();
				query.setParameter(i++, oidIterado);
			
			}
			
		}
		
		
		//Obtengo el resultado de la ejecución
		List<TldTarea> resul = query.getResultList();
		
		for(TldTarea tareaIterada : resul){
			if(Utiles.sonIguales(FECHA_NULA, tareaIterada.getFechaInicio())){
				tareaIterada.setFechaInicio(null);
			}
			
			if(Utiles.sonIguales(FECHA_NULA, tareaIterada.getFechaFin())){
				tareaIterada.setFechaFin(null);
			}
		}
		
		return resul;
	}
	

	/* (non-Javadoc)
	 * @see timeLapse.resources.dao.ITareaDAO#remove(java.lang.String)
	 */
	@Transactional
	public TldTarea remove(String oid) {
		TldTarea tarea = find(oid);
		if (tarea != null) {
			em.remove(tarea);
		}
		return tarea;

	}

	/* (non-Javadoc)
	 * @see timeLapse.resources.dao.ITareaDAO#save(timeLapse.resources.hibernateDTO.TldTarea)
	 */
	@Transactional
	public void save(TldTarea tarea) {
		//Cuadno las fechas son nulas, se pone un valor muy alto para que sea posible sacarlas ordenadas.
		if(tarea.getFechaInicio() == null){
			tarea.setFechaInicio(FECHA_NULA);
		}
		
		if(tarea.getFechaFin() == null){
			tarea.setFechaFin(FECHA_NULA);
		}
		
		if (tarea.getOid() == null) {
			// new
			em.persist(tarea);

		} else {
			// update
			em.merge(tarea);
		}

	}

	public boolean existeTareaConEsteNombre(String nombreTarea, String oidUsuarioRegistrado) {
		Query query = em.createQuery(
				" select tarea " +
				" from TldTarea tarea " +
				" where (tarea.tldUsuarioByUsuarioCreadorOid.oid like ?" +
				" or tarea.tldUsuarioByUsuarioAsociadoOid.oid like ? )" +
				" and tarea.nombre like ? ");
				
		query.setParameter(1, oidUsuarioRegistrado);
		query.setParameter(2, oidUsuarioRegistrado);
		query.setParameter(3, nombreTarea);		
		boolean resul = !query.getResultList().isEmpty();
		
		
		return resul;
	}

	@Override
	@SuppressWarnings("unchecked")
	public List<TldTarea> findAllPadres(String oidUsuario) {
		Query query = em.createQuery(
				" select distinct tarea1 " +
				" from TldTarea tarea1, TldTarea tarea2 " +
				" where (tarea1.tldUsuarioByUsuarioCreadorOid.oid like ?" +
				" or tarea1.tldUsuarioByUsuarioAsociadoOid.oid like ? )" +
				" and (tarea2.tldUsuarioByUsuarioCreadorOid.oid like ?" +
				" or tarea2.tldUsuarioByUsuarioAsociadoOid.oid like ? ) " +
				" and tarea1.oid <> tarea2.oid " +
				" and tarea2.tldTarea.oid like tarea1.oid");
				
		query.setParameter(1, oidUsuario);
		query.setParameter(2, oidUsuario);
		query.setParameter(3, oidUsuario);
		query.setParameter(4, oidUsuario);
		
		return query.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<TldTarea> findTareasAAsociarComoPadres(String oidUsuario, String oidTareasPadre) {
		String cadConsulta = 
		" select tarea " +
		" from TldTarea tarea " +
		" where tarea.tldUsuarioByUsuarioCreadorOid.oid like ?" +
		" and ";
		if(oidTareasPadre != null){
			cadConsulta += "  (tarea.oid like ? or ";
		}
		 
		cadConsulta += " (tarea.fechaRealizacion is null and tarea.tldPeriodicidad is null) ";
		if(oidTareasPadre != null){
			cadConsulta += ") ";
		}
		
		Query query = em.createQuery(cadConsulta);
		query.setParameter(1, oidUsuario);
		if(oidTareasPadre != null){
			query.setParameter(2, oidTareasPadre);
		}
		
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void desvinculaTareasGrupoDesvinculado(TldGrupo grupo,
			List<TldUsuario> listaUsuariosADesasociar) {
		//Obtengo todos las tareas a "desasociar" del grupo del que van a ser desasociados los usuarios
		String cadConsulta = 
				" select tarea " +
				" from TldTarea tarea " +
				" where tarea.tldGrupo.oid like ? " +
				" and tarea.tldUsuarioByUsuarioCreadorOid.oid in ( ";
		
		Iterator<TldUsuario> it = listaUsuariosADesasociar.iterator();	
		while (it.hasNext()){
			it.next(); 
			if(it.hasNext()){
				cadConsulta += " ?,";
			}else{
				cadConsulta += " ? )";
			}
			
		}		
		Query query = em.createQuery(cadConsulta);		
		query.setParameter(1, grupo.getOid());
		it = listaUsuariosADesasociar.iterator();
		int i = 2;
		while (it.hasNext()){
			TldUsuario usuarioIterado = it.next(); 
			query.setParameter(i++, usuarioIterado.getOid());			
			
		}
		
		List<TldTarea> tareasADesagrupar = query.getResultList();
		//Desasocio todas las tareas del grupo
		for(TldTarea tareaIterada: tareasADesagrupar){
			tareaIterada.setTldGrupo(null);
			em.persist(tareaIterada);
		}
		

		
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public void desvinculaTareasUsuariosDelGrupoADesvinculado(TldGrupo grupo,
			List<TldUsuario> listaUsuariosADesasociar) {
		//Obtengo todos las tareas a "desasociar" del grupo del que van a ser desasociados los usuarios
		String cadConsulta = 
				" select tarea " +
				" from TldTarea tarea " +
				" where tarea.tldGrupo.oid like ? " +
				" and tarea.tldUsuarioByUsuarioAsociadoOid.oid in ( ";
		
		Iterator<TldUsuario> it = listaUsuariosADesasociar.iterator();	
		while (it.hasNext()){
			it.next(); 
			if(it.hasNext()){
				cadConsulta += " ?,";
			}else{
				cadConsulta += " ? )";
			}
			
		}		
		Query query = em.createQuery(cadConsulta);		
		query.setParameter(1, grupo.getOid());
		it = listaUsuariosADesasociar.iterator();
		int i = 2;
		while (it.hasNext()){
			TldUsuario usuarioIterado = it.next(); 
			query.setParameter(i++, usuarioIterado.getOid());			
			
		}
		
		List<TldTarea> tareasADesagrupar = query.getResultList();
		//Desasocio todas las tareas del grupo
		for(TldTarea tareaIterada: tareasADesagrupar){
			tareaIterada.setTldUsuarioByUsuarioAsociadoOid(null);
			em.persist(tareaIterada);
		}
		
	}



}
