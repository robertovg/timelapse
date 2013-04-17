/**
 * 
 */
package timeLapse.business.tareas.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.tareas.ITareaBO;
import timeLapse.resources.dao.ICategoriaDAO;
import timeLapse.resources.dao.IGrupoDAO;
import timeLapse.resources.dao.ITareaDAO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dto.FiltroTareasDTO;
import timeLapse.resources.dto.TareaDTO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TldCategoria;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldPeriodicidad;
import timeLapse.resources.persistenceDTO.TldTarea;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

/**
 * @author robe
 * 
 */
@Service
@Scope("session")
public class TareaBO extends BusinessObjectTemplate implements ITareaBO {
		
	@Resource
	ITareaDAO tareaDao;
	
	@Resource
	ICategoriaDAO categoriaDAO;
	
	@Resource
	IGrupoDAO grupoDAO;
	
	@Resource
	IUsuarioDAO usuarioDao;
	
	/* (non-Javadoc)
	 * @see timeLapse.bussines.tareas.ITareaBO#actualiza(timeLapse.resources.hibernateDTO.TldGrupo)
	 */
	public void actualiza(TldTarea tarea) throws Exception {
		//Compruebo que la tarea padre exista
		if(tarea.getTldTarea() != null){
			try{
				tareaDao.find(tarea.getTldTarea().getOid());
			}catch (NoResultException e) {
				
				Mensaje men = factoriaMens.getMensaje(ConstantesException.tareaPadreInexistente);
				throw new TimeLapseException(men);
					
			}
		}
	
		//Compruebo que la categoría exísta
		if(tarea.getTldCategoria() != null){
			try{
				categoriaDAO.find(tarea.getTldCategoria().getOid());
			}catch (NoResultException e) {
				
				Mensaje men = factoriaMens.getMensaje(ConstantesException.categoriaInexistente);
				throw new TimeLapseException(men);
					
			}
		}
		
		//Compruebo que el grupo exista
		if(tarea.getTldGrupo() != null){
			try{
				grupoDAO.find(tarea.getTldGrupo().getOid());
			}catch (NoResultException e) {
				
				Mensaje men = factoriaMens.getMensaje(ConstantesException.grupoInexistente);
				throw new TimeLapseException(men);
					
			}
		}
		
		//Compruebo que la tarea padre exista.
		if(tarea.getTldTarea() != null){
			try{
				tareaDao.find(tarea.getTldTarea().getOid());
			}catch (NoResultException e) {
				
				Mensaje men = factoriaMens.getMensaje(ConstantesException.grupoInexistente);
				throw new TimeLapseException(men);
					
			}
		}
		
		if(tarea.getFechaInicio() != null && tarea.getFechaFin() != null){
			if(tarea.getFechaFin().before(tarea.getFechaInicio())){
				Mensaje men = factoriaMens.getMensaje(ConstantesException.tareaConFechaFinMenorFechaInicio);
				throw new TimeLapseException(men);
			}
		}
		
		if(tarea.getFechaCreacion() == null){
			
			TldTarea tareaBD = tareaDao.find(tarea.getOid());
			tarea.setFechaCreacion(tareaBD.getFechaCreacion());
			
			
		}
		
		
		
		tareaDao.save(tarea);

	}

	/* (non-Javadoc)
	 * @see timeLapse.bussines.tareas.ITareaBO#elimina(java.lang.String)
	 */
	public TldTarea elimina(String oid, String oidUsuarioRegistrado) throws Exception {
		TldTarea tareaOriginal = tareaDao.find(oid); 
		TldTarea resul = null;
		if(!tareaOriginal.getTldUsuarioByUsuarioCreadorOid().getOid().equals(oidUsuarioRegistrado)){			
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarTareasSiNoEresElCreador);
			throw new TimeLapseException(men);
		}
		
		try{
			resul = tareaDao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarTareasConHijas);
			throw new TimeLapseException(men);
		}
		
		return resul; 

	}


	/* (non-Javadoc)
	 * @see timeLapse.bussines.tareas.ITareaBO#listaTareas(java.lang.String)
	 */
	public List<TareaDTO> listaTareas(FiltroTareasDTO filtro, UsuarioDTO usuarioRegistrado) throws Exception {
		List<TareaDTO> listResul = new ArrayList<TareaDTO>();
		//Si el filtro no es correcto, elevo una excepción
		if(!filtro.esCorrecto()){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.filtroTareasIncoherente);
			throw new TimeLapseException(men);
		}
		List<TldTarea> listBD = tareaDao.findAll(filtro);
		if(listBD.size() > MAXIMAS_FILAS_MOSTRADAS){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.filtroTareasIncoherente);
			throw new TimeLapseException(men);
		}
			
		//Transformo las TldTareas a TareasDTO
		for(TldTarea tareaIterada : listBD){
			TareaDTO tareaDto = new TareaDTO(tareaIterada);
			if(tareaDto.getOidUsuarioCreador().equals(usuarioRegistrado.getOid())){
				tareaDto.setEsPropiaLaTarea("true");
			}
			listResul.add(tareaDto);
			
		}
		
		
		
		return listResul;
	}
	/* (non-Javadoc)
	 * @see timeLapse.bussines.tareas.ITareaBO#listaTareas(java.lang.String)
	 */
	public List<TareaDTO> listaProximasTareas(FiltroTareasDTO filtro, UsuarioDTO usuarioRegistrado) throws Exception {
		List<TareaDTO> listResul = new ArrayList<TareaDTO>();
		//Si el filtro no es correcto, elevo una excepción
		if(!filtro.esCorrecto()){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.filtroTareasIncoherente);
			throw new TimeLapseException(men);
		}
		List<TldTarea> listBD = tareaDao.findAll(filtro);
		
			
		//Transformo las 10 primeras tareas en TldTareas a TareasDTO		
		Iterator<TldTarea> it = listBD.iterator();
		int i = 0;
		while(it.hasNext() && i < numeroProximasTareas ){
			TldTarea tareaIterada = it.next(); 
			TareaDTO tareaDto = new TareaDTO(tareaIterada);
			if(tareaDto.getOidUsuarioCreador().equals(usuarioRegistrado.getOid())){
				tareaDto.setEsPropiaLaTarea("true");
			}
			listResul.add(tareaDto);
			i++;
		}
		
		
		
		return listResul;
	}
	
	
	

	public String clonarTarea(String oidTarea, String oidUsuarioRegistrado) {
		TldTarea tareaBD = tareaDao.find(oidTarea);
		/*
		 * La tarea es propia si la original esta creada por el usuario registrado 
		 */
		boolean esPropiaLaTarea = tareaBD.getTldUsuarioByUsuarioCreadorOid().getOid().equals(oidUsuarioRegistrado);
		
		
		String nombreCandidato = "Copia " + tareaBD.getNombre();
		
		int i = 1;
		while(tareaDao.existeTareaConEsteNombre(nombreCandidato, oidUsuarioRegistrado)){
			nombreCandidato = "Copia (" + i++ +") " + tareaBD.getNombre();
		}
		TldTarea copiaTarea = new TldTarea();
		
//		TldUsuario usuarioAsociado = null;
//		if(tareaBD.getTldUsuarioByUsuarioAsociadoOid() != null){
//			usuarioAsociado = new TldUsuario();
//			usuarioAsociado.setOid(tareaBD.getTldUsuarioByUsuarioAsociadoOid().getOid()); 
//		}				
//		copiaTarea.setTldUsuarioByUsuarioAsociadoOid(usuarioAsociado);
//		
		TldCategoria categoria = null;
		if(tareaBD.getTldCategoria() != null){
			categoria = new TldCategoria();
			categoria.setOid(tareaBD.getTldCategoria().getOid());
			
			
		}
		if(esPropiaLaTarea){
			copiaTarea.setTldCategoria(categoria);
		}
		
		
		
		
		TldUsuario usuarioCreador = new TldUsuario();		
		usuarioCreador.setOid(oidUsuarioRegistrado);
		
		copiaTarea.setTldUsuarioByUsuarioCreadorOid(usuarioCreador);
		
		TldTarea tareaPadre = null;
		if(tareaBD.getTldTarea() != null){
			tareaPadre = new TldTarea();
			tareaPadre.setOid(tareaBD.getTldTarea().getOid());			
		}
		
		if(esPropiaLaTarea){
			copiaTarea.setTldTarea(tareaPadre);
		}
		
		TldGrupo grupo = null;
		if(tareaBD.getTldGrupo() != null){
			grupo = new TldGrupo();
			grupo.setOid(tareaBD.getTldGrupo().getOid());
			
			
		}
		
		if(esPropiaLaTarea){
			copiaTarea.setTldGrupo(grupo);
		}
		
		
		TldPeriodicidad periodicidad = null;
		if(tareaBD.getTldPeriodicidad() != null){
			periodicidad = new TldPeriodicidad();
			periodicidad.setOid(tareaBD.getTldPeriodicidad().getOid());
			
		}
		copiaTarea.setTldPeriodicidad(periodicidad);
		
		copiaTarea.setDescripcion(tareaBD.getDescripcion());
		copiaTarea.setFechaCreacion(new Date() );
		
		copiaTarea.setHoraRealizacion(tareaBD.getHoraRealizacion());
		copiaTarea.setFechaInicio(tareaBD.getFechaInicio());
		copiaTarea.setHoraInicio(tareaBD.getHoraInicio());
		copiaTarea.setFechaFin(tareaBD.getFechaFin());
		copiaTarea.setHoraFin(tareaBD.getHoraFin());
		copiaTarea.setLocalizacionAsociada(tareaBD.getLocalizacionAsociada());
		copiaTarea.setAutorrealizable(tareaBD.getAutorrealizable());
		copiaTarea.setImportancia(tareaBD.getImportancia());
		
		
			
		copiaTarea.setNombre(nombreCandidato);
		copiaTarea.setFechaRealizacion(null);
		copiaTarea.setOid(null);
		
		tareaDao.save(copiaTarea);
		
		return nombreCandidato;
	}

	public List<TldTarea> finalizarTareas(List<String> listID) throws TimeLapseException {
		List<TldTarea> resul = new ArrayList<TldTarea>();
		TimeLapseException errores = new TimeLapseException();
		for(String oid : listID){
			TldTarea tareaBD = tareaDao.find(oid);			
			//Compruebo que no esté ya finalizada la tarea
			if(tareaBD.getFechaRealizacion() != null){
				Mensaje men = factoriaMens.getMensaje(ConstantesException.tareaYaMarcadaComoRealizada);
				errores.addMensaje(men);
			//Compruebo que no sea una taea autorrealizable
			}else if(tareaBD.getAutorrealizable() == 1){
				Mensaje men = factoriaMens.getMensaje(ConstantesException.tareaAutorrealizableNoSePuedeFinalizar);
				errores.addMensaje(men);
			}else{
				tareaBD.setFechaRealizacion(new Date());
				tareaBD.setHoraRealizacion(new Date());
				
				resul.add(tareaBD);
			}
			
		}
		//Si se producen errores los elevo, sino simplemente hago persistentes los cambios
		if(!errores.isEmpty()){
			throw errores;
		}else{
			//Recorro uno a uno todos las tareas y las voy persistiendo en la BBDD
			for(TldTarea tarea : resul){
				tareaDao.save(tarea);
			}
		}
		return resul;
		
	}

	@Override
	public List<TareaDTO> listaTareasPadre(String oidUsuario, boolean dejaSoloAsociadas) {
		List<TareaDTO> resul = new ArrayList<TareaDTO>();
		
		List<TldTarea> listBD = tareaDao.findAllPadres(oidUsuario);
		//Primero añado las 3 por defecto
		if(!dejaSoloAsociadas){
			TareaDTO tarea1 = new TareaDTO();
			tarea1.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_TODAS_EN_LISTAS"));
			tarea1.setOid(VALOR_TODAS_EN_LISTAS);		
			resul.add(tarea1);
			TareaDTO tarea2 = new TareaDTO();
			tarea2.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_NO_ASOCIADAS_EN_LISTAS"));
			tarea2.setOid(VALOR_NO_ASOCIADAS_EN_LISTAS);		
			resul.add(tarea2);
		}
		
		TareaDTO tarea3 = new TareaDTO();
		tarea3.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_ASOCIADAS_EN_LISTAS"));
		tarea3.setOid(VALOR_ASOCIADAS_EN_LISTAS);		
		resul.add(tarea3);	
		
		//Transformo las TldTareas a TareasDTO
		for(TldTarea tareaIterada : listBD){
			resul.add(new TareaDTO(tareaIterada));
		}
		
		return resul;
	}
	@Override
	public List<TareaDTO> listaTareasAAsociarComoPadres(String oidUsuario, String oidTareasPadre) {
		List<TareaDTO> resul = new ArrayList<TareaDTO>();

		List<TldTarea> listBD = tareaDao.findTareasAAsociarComoPadres(oidUsuario, oidTareasPadre);
		//Primero añado las 2 por defecto
		TareaDTO tarea1 = new TareaDTO();
		tarea1.setNombre(VALOR_NO_ASOCIADAS_EN_LISTAS);
		tarea1.setOid(VALOR_NO_ASOCIADAS_EN_LISTAS);		
		resul.add(tarea1);
		//Transformo las TldTareas a TareasDTO
		for(TldTarea tareaIterada : listBD){
			resul.add(new TareaDTO(tareaIterada));
		}
		
		return resul;
	}

	@Override
	public TldTarea encuentra(String oid) {
		return tareaDao.find(oid);
	}

	@Override
	public void vinculaTareaTareas(String oidTareaPadre, List<String> listOidTareasHijas) throws TimeLapseException {
		TldTarea tareaPadre = tareaDao.find(oidTareaPadre);
		TimeLapseException errores = new TimeLapseException();

		if(tareaPadre == null){
			String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaPadreInexistente,cadIdTarea + oidTareaPadre);				
			errores.addMensaje(mensaje);
		}else{
			for(String oidTareasHijas: listOidTareasHijas){
				TldTarea tareaHijaIterada = tareaDao.find(oidTareasHijas);
				if(tareaHijaIterada == null){
					String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaHijaInexistente,cadIdTarea + oidTareasHijas);				
					errores.addMensaje(mensaje);
				}else{
					tareaHijaIterada.setTldTarea(tareaPadre);
					tareaDao.save(tareaHijaIterada);
				}
				
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
		
		
	}

	@Override
	public void desvinculaTareaTareas(List<String> listaOIdTareasHijas) throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();

		for(String oidTareaHija: listaOIdTareasHijas){
			TldTarea tareaHijaIterada = tareaDao.find(oidTareaHija);
			if(tareaHijaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaHijaInexistente,cadIdTarea + oidTareaHija);				
				errores.addMensaje(mensaje);
			}else{
				tareaHijaIterada.setTldTarea(null);
				tareaDao.save(tareaHijaIterada);
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void vinculaTareasTiempo(Date dateFechaInicio, Date dateHoraInicio,
			Date dateFechaFin, Date dateHoraFin, List<String> listOidEntidades)
			throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();
		for(String oidTareaIterada : listOidEntidades){
			TldTarea tareaIterada = tareaDao.find(oidTareaIterada);
			if(tareaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTareaIterada);				
				errores.addMensaje(mensaje);
				
			}
			tareaIterada.setFechaInicio(dateFechaInicio);
			tareaIterada.setHoraInicio(dateHoraInicio);
			tareaIterada.setFechaFin(dateFechaFin);
			tareaIterada.setHoraFin(dateHoraFin);
			
			tareaDao.save(tareaIterada);
			
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void desvinculaTareasTiempo(List<String> listaOIdTareas)
			throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();

		for(String oidTarea: listaOIdTareas){
			TldTarea tareaIterada = tareaDao.find(oidTarea);
			if(tareaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTarea);				
				errores.addMensaje(mensaje);
			}else{
				tareaIterada.setFechaInicio(null);
				tareaIterada.setHoraInicio(null);
				tareaIterada.setFechaFin(null);
				tareaIterada.setHoraFin(null);
				tareaDao.save(tareaIterada);
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}



	@Override
	public void vinculaTareasCategoria(String oidCategoria,
			List<String> listOidTareas) throws TimeLapseException {
		TldCategoria categoria = categoriaDAO.find(oidCategoria);
		TimeLapseException errores = new TimeLapseException();

		if(categoria == null){
			String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Categoria");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.categoriaInexistente,cadIdTarea + oidCategoria);				
			errores.addMensaje(mensaje);
		}else{
			for(String oidTareaIterada: listOidTareas){
				TldTarea tareaIterada = tareaDao.find(oidTareaIterada);
				if(tareaIterada == null){
					String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTareaIterada);				
					errores.addMensaje(mensaje);
				}else{
					tareaIterada.setTldCategoria(categoria);
					tareaDao.save(tareaIterada);
				}
				
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}
	@Override
	public void desvinculaTareasCategoria(List<String> listaOidTareas)
			throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();

		for(String oidTarea: listaOidTareas){
			TldTarea tareaIterada = tareaDao.find(oidTarea);
			if(tareaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTarea);				
				errores.addMensaje(mensaje);
			}else{
				tareaIterada.setTldCategoria(null);
				tareaDao.save(tareaIterada);
			}
			
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void vinculaTareasGrupo(String oidGrupo, List<String> listOidTareas)
			throws TimeLapseException {
		TldGrupo grupo = grupoDAO.find(oidGrupo);
		TimeLapseException errores = new TimeLapseException();

		if(grupo == null){
			String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Grupo");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.grupoInexistente,cadIdTarea + oidGrupo);				
			errores.addMensaje(mensaje);
		}else{
			for(String oidTareaIterada: listOidTareas){
				TldTarea tareaIterada = tareaDao.find(oidTareaIterada);
				if(tareaIterada == null){
					String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTareaIterada);				
					errores.addMensaje(mensaje);
				}else{
					tareaIterada.setTldGrupo(grupo);
					tareaDao.save(tareaIterada);
				}
				
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void desvinculaTareasGrupo(List<String> listaOidTareas)
			throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();
		for(String oidTarea: listaOidTareas){
			TldTarea tareaIterada = tareaDao.find(oidTarea);
			if(tareaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTarea);				
				errores.addMensaje(mensaje);
			}else{
				
				//También elimino los usuarios vinculados a esta tarea
				tareaIterada.setTldUsuarioByUsuarioAsociadoOid(null);
				tareaIterada.setTldGrupo(null);
				tareaDao.save(tareaIterada);
			}
			
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void desvinculaUsuarioTareas(List<String> listaOidTareas)
			throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();
		for(String oidTarea: listaOidTareas){
			TldTarea tareaIterada = tareaDao.find(oidTarea);
			if(tareaIterada == null){
				String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
				Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTarea);				
				errores.addMensaje(mensaje);
			}else{
				tareaIterada.setTldUsuarioByUsuarioAsociadoOid(null);
				tareaDao.save(tareaIterada);
			}
			
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

	@Override
	public void vinculaUsuarioTareas(String oidUsuario,
			List<String> listOidTareas) throws TimeLapseException {
		TldUsuario usuario = usuarioDao.findById(oidUsuario);
		TimeLapseException errores = new TimeLapseException();

		if(usuario == null){
			String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Usuario");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.usuarioInexistente,cadIdTarea + oidUsuario);				
			errores.addMensaje(mensaje);
		}else{
			for(String oidTareaIterada: listOidTareas){
				TldTarea tareaIterada = tareaDao.find(oidTareaIterada);
				if(tareaIterada == null){
					String cadIdTarea = bibliotecaI18n.traduce("mensajes.Identificador_Tarea");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.tareaInexistente,cadIdTarea + oidTareaIterada);				
					errores.addMensaje(mensaje);
				}else{
					tareaIterada.setTldUsuarioByUsuarioAsociadoOid(usuario);
					tareaDao.save(tareaIterada);
				}
				
			}
			
		}
		if(!errores.isEmpty()){
			throw errores;
		}
		
	}

}
