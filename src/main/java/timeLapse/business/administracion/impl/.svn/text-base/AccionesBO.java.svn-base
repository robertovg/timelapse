package timeLapse.business.administracion.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.administracion.IAccionesBO;
import timeLapse.resources.dao.IAccionDAO;
import timeLapse.resources.persistenceDTO.TlpAccion;
import timeLapse.resources.persistenceDTO.TlpFuncionalidad;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;

/**
 * 
 * @author robe
 */
@Service
@Scope("session")
public class AccionesBO extends BusinessObjectTemplate implements IAccionesBO{
	@Resource
	IAccionDAO accDao;
	

	public List<TlpAccion> listaAcciones()throws Exception{
		IAccionDAO dao = getAccDao();
		
		List<TlpAccion> acciones =  dao.findAll();
		
		return acciones;
		
		
	}

	public void actualiza(TlpAccion obj) throws Exception{
		
		IAccionDAO dao = getAccDao();
		dao.save(obj);
		
	}

	public TlpAccion elimina(String oid) throws Exception{
		IAccionDAO dao = getAccDao();
		//Traduzco el nombre que posteriormente se mostrará como mensaje al usuario
		TlpAccion resul = null;
		try{
			resul = dao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarAccionesConRoles);
			throw new TimeLapseException(men);
		}	
		 
		resul.setNombre(bibliotecaI18n.traduce(resul.getNombre()));	

		return resul;
	}
	
	public IAccionDAO getAccDao() {
		return accDao;
	}
	
	public void setAccDao(IAccionDAO dao) {
			
		this.accDao = dao;
	}

	public List<TlpAccion> listaAccionesAsociadas(String oidRol) {
		TlpRol rol = new TlpRol();
		rol.setOid(oidRol);
		
		List<TlpAccion> listResul = accDao.findAsociadas(rol);
		
		for(TlpAccion accionIterada : listResul){
			accionIterada.setNombre(bibliotecaI18n.traduce(accionIterada.getNombre()));
			accionIterada.setDescripcion(bibliotecaI18n.traduce(accionIterada.getDescripcion()));
			
			TlpFuncionalidad fun = accionIterada.getTlpFuncionalidad();
			fun.setNombre(bibliotecaI18n.traduce(fun.getNombre()));
			fun.setDescripcion(bibliotecaI18n.traduce(fun.getDescripcion()));
		}
		return listResul;
	}

	public List<TlpAccion> listaAccionesNoAsociadas(String oidRol) {
		TlpRol rol = new TlpRol();
		rol.setOid(oidRol);
		
		List<TlpAccion> listResul = accDao.findNoAsociadas(rol);
		
		for(TlpAccion accionIterada : listResul){
			accionIterada.setNombre(bibliotecaI18n.traduce(accionIterada.getNombre()));
			accionIterada.setDescripcion(bibliotecaI18n.traduce(accionIterada.getDescripcion()));
			
			TlpFuncionalidad fun = accionIterada.getTlpFuncionalidad();
			fun.setNombre(bibliotecaI18n.traduce(fun.getNombre()));
			fun.setDescripcion(bibliotecaI18n.traduce(fun.getDescripcion()));
		}
		
		return listResul;
	}

	public List<TlpAccion> findAll(String funcionalidadOID, TlpRol rol) {
		List<TlpAccion> acciones = accDao.findAll(funcionalidadOID, rol);
		for(TlpAccion accionIterada : acciones){
			accionIterada.setNombre(bibliotecaI18n.traduce(accionIterada.getNombre()));
			accionIterada.setDescripcion(bibliotecaI18n.traduce(accionIterada.getDescripcion()));
		}
		return acciones;
	}

	@Override
	public TlpAccion encuentra(String oid) throws Exception {
		
		return accDao.find(oid);
	}


	
	
	

}
