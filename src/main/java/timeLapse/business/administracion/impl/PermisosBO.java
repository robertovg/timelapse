package timeLapse.business.administracion.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.administracion.IPermisosBO;
import timeLapse.resources.dao.IPermisosDAO;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpRol;
@Service
@Scope("session")
public class PermisosBO implements IPermisosBO {
	@Resource
	IPermisosDAO permisosDao;
	public void desvinculaRolYAcciones(String rol, List<String> acciones) {
		TlpRol objRol = new TlpRol();
		objRol.setOid(rol);
		List<TlpAccion> listaAcciones = new ArrayList<TlpAccion>();
		for(String idAccion: acciones){
			TlpAccion objAccion = new TlpAccion();
			objAccion.setOid(idAccion);
			listaAcciones.add(objAccion);
		}
		permisosDao.desvinculaRolYAcciones(objRol, listaAcciones);
		
	}

	public void vinculaRolYAcciones(String rol, List<String> acciones) {
		//Creo los objetos.
		TlpRol objRol = new TlpRol();
		objRol.setOid(rol);
		List<TlpAccion> listaAcciones = new ArrayList<TlpAccion>();
		for(String idAccion: acciones){
			TlpAccion objAccion = new TlpAccion();
			objAccion.setOid(idAccion);
			listaAcciones.add(objAccion);
		}
		permisosDao.vinculaRolYAcciones(objRol, listaAcciones);
		
		
		
		
	}


}
