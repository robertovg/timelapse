package timeLapse.business.grupos.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.grupos.IGrupoBO;
import timeLapse.resources.dao.IGrupoDAO;
import timeLapse.resources.dao.ITareaDAO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dao.IUsuarioGrupoDAO;
import timeLapse.resources.persistenceDTO.TldGrupo;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlrUsuarioGrupo;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;
@Service
@Scope("session")
public class GrupoBO extends BusinessObjectTemplate implements IGrupoBO {

	@Resource
	IGrupoDAO grupoDao;
	
	@Resource
	IUsuarioGrupoDAO usuarioGrupoDao;
	
	@Resource
	IUsuarioDAO usuarioDao;
	
	@Resource
	ITareaDAO tareaDao;
	
	public void setAccDao(IGrupoDAO grupoDao) {
		this.grupoDao = grupoDao;
	}

	public void actualiza(TldGrupo grupo) throws Exception {
		grupoDao.save(grupo);

	}

	public TldGrupo elimina(String oid) throws Exception {
		TldGrupo grupoEliminado = null;
		
		try{
			grupoEliminado = grupoDao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarGruposConTareas);
			throw new TimeLapseException(men);
		}
		
		return grupoEliminado;

	}

	public List<TldGrupo> listaGruposCreados(String oidUsuario)
			throws Exception {
		List<TldGrupo> resul = new ArrayList<TldGrupo>();
		List<TldGrupo> gruposBD = grupoDao.findAllCreados(oidUsuario);
		
		for(TldGrupo grupoBD : gruposBD){
			
			TldGrupo grupo = new TldGrupo();
			grupo.setOid(grupoBD.getOid());
			grupo.setNombre(grupoBD.getNombre());
			grupo.setDescripcion(grupoBD.getDescripcion());
			
			
			resul.add(grupo);
		}
		return resul;
	}

	public List<TldGrupo> listaGruposPertenecientes(String oidUsuario)
			throws Exception {
		List<TldGrupo> resul = new ArrayList<TldGrupo>();
		List<TldGrupo> gruposBD = grupoDao.findAllPertenecientes(oidUsuario);
		
		for(TldGrupo grupoBD : gruposBD){
			
			TldGrupo grupo = new TldGrupo();
			grupo.setOid(grupoBD.getOid());
			grupo.setNombre(grupoBD.getNombre());
			grupo.setDescripcion(grupoBD.getDescripcion());
			
			
			resul.add(grupo);
		}
		
		return resul;
		}

	@Override
	public List<TldGrupo> listaGruposUtilizados(String oidUsuario, boolean dejaSoloAsociadas)
			throws Exception {
		List<TldGrupo> resul = new ArrayList<TldGrupo>();
		//Primero añado las 3 por defecto
		if(!dejaSoloAsociadas){
			TldGrupo grup1 = new TldGrupo();
			grup1.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_TODAS_EN_LISTAS"));
			grup1.setOid(VALOR_TODAS_EN_LISTAS);		
			resul.add(grup1);
			TldGrupo grupo2 = new TldGrupo();
			grupo2.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_NO_ASOCIADAS_EN_LISTAS"));
			grupo2.setOid(VALOR_NO_ASOCIADAS_EN_LISTAS);		
			resul.add(grupo2);
		}
		TldGrupo grupo3 = new TldGrupo();
		grupo3.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_ASOCIADAS_EN_LISTAS"));
		grupo3.setOid(VALOR_ASOCIADAS_EN_LISTAS);		
		resul.add(grupo3);	

		List<TldGrupo> gruposBD = grupoDao.findAllUtilizados(oidUsuario);
		//Añado el resto
		for(TldGrupo grupoBD : gruposBD) {			
			TldGrupo grupo = new TldGrupo();
			grupo.setOid(grupoBD.getOid());
			grupo.setNombre(grupoBD.getNombre());
			grupo.setDescripcion(grupoBD.getDescripcion());
			
			
			resul.add(grupo);
		}
		
		
		return resul;
	}

	@Override
	public TldGrupo encuentra(String oid) throws Exception {
		
		return grupoDao.find(oid);
	}

	@Override
	public void desvinculaUsuarioGrupo(String oidGrupo,
			List<String> listOidUsuarios) throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();
		//Compruebo que exitan tanto el grupo como los usuarios
		TldGrupo grupo = grupoDao.find(oidGrupo);
		List<TldUsuario> listaUsuariosADesasociar = new ArrayList<TldUsuario>();

		if(grupo == null){
			String cadIdGrupo = bibliotecaI18n.traduce("mensajes.Identificador_Grupo");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.grupoInexistente,cadIdGrupo + oidGrupo);				
			errores.addMensaje(mensaje);
		}else{
			//Recorro los identificadores de usuarios a vincular
			for(String oidUsuarioIterado: listOidUsuarios){
				TldUsuario usuarioIterado = usuarioDao.findById(oidUsuarioIterado);
				if(usuarioIterado == null){
					String cadIdUsuario = bibliotecaI18n.traduce("mensajes.Identificador_Usuario");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.usuarioInexistente,cadIdUsuario + oidUsuarioIterado);				
					errores.addMensaje(mensaje);
				}else{
					listaUsuariosADesasociar.add(usuarioIterado);
				}				
			}
			//Si no se han producido errores todavía
			if(errores.isEmpty()){
				//Desvinculo las tareas asociadas a esa persona ya que va a dejar de estar en el grupo
				tareaDao.desvinculaTareasUsuariosDelGrupoADesvinculado(grupo, listaUsuariosADesasociar);
				//Llamo a la función que se encarga de desvincular las tareas vinculadas con el grupo al que pertenece
				tareaDao.desvinculaTareasGrupoDesvinculado(grupo, listaUsuariosADesasociar);
				
				//Llamo al método que se encarga de eliminarlos de forma transaccional
				usuarioGrupoDao.desvinculaUsuarioGrupo(grupo, listaUsuariosADesasociar);
			}
		}
		//Si se ha producido alguna excepción, informo de ello elebando la misma
		if(!errores.isEmpty()){
			throw errores;
		}
	}

	@Override
	public void vinculaUsuariosGrupo(String oidGrupo,
			List<String> listOidUsuarios) throws TimeLapseException {
		TimeLapseException errores = new TimeLapseException();
		//Compurebo que existen los grupos y usuarios
		TldGrupo grupo = grupoDao.find(oidGrupo);
		List<TldUsuario> listaUsuariosAAsociar = new ArrayList<TldUsuario>();
		if(grupo == null){
			String cadIdGrupo = bibliotecaI18n.traduce("mensajes.Identificador_Grupo");
			Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.grupoInexistente,cadIdGrupo + oidGrupo);				
			errores.addMensaje(mensaje);
		}else{
			//Recorro los identificadores de usuarios a vincular
			for(String oidUsuarioIterado: listOidUsuarios){
				TldUsuario usuarioIterado = usuarioDao.findById(oidUsuarioIterado);
				if(usuarioIterado == null){
					String cadIdUsuario = bibliotecaI18n.traduce("mensajes.Identificador_Usuario");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.usuarioInexistente,cadIdUsuario + oidUsuarioIterado);				
					errores.addMensaje(mensaje);
				}else{
					listaUsuariosAAsociar.add(usuarioIterado);
				}				
			}
			//Si no se han producido errores todavía
			if(errores.isEmpty()){
				// Luego compruebo que no existan alguna de las relaciones que voy a crear
				List<TlrUsuarioGrupo> relacionesYaExistentes = usuarioGrupoDao.findAll(grupo, listaUsuariosAAsociar); 
				/*
				 * Si ya existen relaciones existentes elevo la excepción pertinente, ya que no se pueden crera 
				 * más de una relación entre el mismo grupo y usuario 
				 */				
				if(!relacionesYaExistentes.isEmpty()){
					
					String cadIdGrupo = bibliotecaI18n.traduce("mensajes.Identificador_Grupo");
					Mensaje mensaje = factoriaMens.getMensaje(ConstantesException.relacionUsuarioGrupoYaExistente,cadIdGrupo + relacionesYaExistentes.get(0).getTldGrupo().getNombre());				
					errores.addMensaje(mensaje);
					
				}else{
					//Hago persistentes las relaciones
					usuarioGrupoDao.vinculaUsuarioGrupo(grupo, listaUsuariosAAsociar);
				}

			}
		
			
		}
		
		//Si se produce un error, elebo la excepción		
		if(!errores.isEmpty()){
			throw errores;
		}
		

	}



}
