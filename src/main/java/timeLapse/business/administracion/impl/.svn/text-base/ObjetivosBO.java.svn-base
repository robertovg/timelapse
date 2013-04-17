package timeLapse.business.administracion.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.administracion.IObjetivosBO;
import timeLapse.resources.dao.IObjetivoDAO;
import timeLapse.resources.persistenceDTO.TlpObjetivo;
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
public class ObjetivosBO extends BusinessObjectTemplate implements IObjetivosBO{
	@Resource
	private IObjetivoDAO objDao;

	public List<TlpObjetivo> listaObjetivos()throws Exception{
		
		List<TlpObjetivo> objetivos =  objDao.findAll();
		return objetivos;
		
		
	}
	
	public List<TlpObjetivo> listaObjetivos(TlpRol usuario) throws Exception {
		
		List<TlpObjetivo> objetivos =  objDao.findAll(usuario);
		
		for(TlpObjetivo objIterado : objetivos){
			objIterado.setNombre(bibliotecaI18n.traduce(objIterado.getNombre()));
			objIterado.setDescripcion(bibliotecaI18n.traduce(objIterado.getDescripcion()));			
		}
		
		return objetivos;
	}
	

	public void actualiza(TlpObjetivo obj) throws Exception{
		
		objDao.save(obj);
		
	}
	
	public TlpObjetivo elimina(String oid) throws Exception{
		TlpObjetivo resul = null; 
		try{
			resul = objDao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarObjetivosConFuncionalidades);
			throw new TimeLapseException(men);
		}		
		resul.setNombre(bibliotecaI18n.traduce(resul.getNombre()));		
		return resul;
		
	}

	@Override
	public TlpObjetivo encuentra(String oid) throws Exception {
		return objDao.find(oid);
	}
	
		
	

}
