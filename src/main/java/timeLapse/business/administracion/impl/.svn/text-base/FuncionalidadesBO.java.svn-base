package timeLapse.business.administracion.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.administracion.IAccionesBO;
import timeLapse.business.administracion.IFuncionalidadesBO;
import timeLapse.resources.dao.IFuncionalidadDAO;
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
public class FuncionalidadesBO extends BusinessObjectTemplate implements IFuncionalidadesBO{
	
	@Resource
	private IFuncionalidadDAO funDao;
	
	@Resource
	private IAccionesBO accBO;

	
	public List<TlpFuncionalidad> listaFuncionalidad()throws Exception{
		
		
		List<TlpFuncionalidad> funcionalidad =  funDao.findAll();
		return funcionalidad;
		
		
	}

	public List<TlpFuncionalidad> listaFuncionalidades(String objetivoOid,  TlpRol rol) throws Exception{
		
		
		
		List<TlpFuncionalidad> funcionalidadesBD =  funDao.findAll(objetivoOid, rol);
		//Recorro las funcionalidades traidas de la base de datos, para cargarle las acciones para las que hay permisos
		for(TlpFuncionalidad funcionalidadBD: funcionalidadesBD){
			//Sobrescribo todas las acciones de la funcionalidad por las funcionalidades para las que tiene permisos 
			//el rol
			funcionalidadBD.setTlpAccions(accBO.findAll(funcionalidadBD.getOid(), rol));
				
		}
		
		
		for(TlpFuncionalidad funcIterada : funcionalidadesBD){
			
			funcIterada.setNombre(bibliotecaI18n.traduce(funcIterada.getNombre()));
			funcIterada.setDescripcion(bibliotecaI18n.traduce(funcIterada.getDescripcion()));
			
		}
		return funcionalidadesBD;
	}
	
	public void actualiza(TlpFuncionalidad obj) throws Exception{
		
		
    	funDao.save(obj);
		
	}
	
	public TlpFuncionalidad elimina(String oid) throws Exception{
		TlpFuncionalidad resul = null;
		try{
			resul = funDao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarFuncionalidadConAcciones);
			throw new TimeLapseException(men);
		}	
		
		resul.setNombre(bibliotecaI18n.traduce(resul.getNombre()));		
		return resul;
		
		
	}

	@Override
	public TlpFuncionalidad encuentra(String oid) throws Exception {
		return funDao.find(oid);
	}
	
	
	
	

}
