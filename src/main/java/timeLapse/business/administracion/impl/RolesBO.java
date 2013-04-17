package timeLapse.business.administracion.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.administracion.IRolesBO;
import timeLapse.resources.dao.IRolesDAO;
import timeLapse.resources.persistenceDTO.TlpRol;
@Service
@Scope("session")
public class RolesBO extends BusinessObjectTemplate implements IRolesBO {
	@Resource
	IRolesDAO rolDao;
	
	public List<TlpRol> listaRoles() {

		List<TlpRol> listResul = rolDao.findAll(); 
		for(TlpRol rolIterado : listResul){
			rolIterado.setNombre(bibliotecaI18n.traduce(rolIterado.getNombre()));
			rolIterado.setDescripcion(bibliotecaI18n.traduce(rolIterado.getDescripcion()));
		}
		return listResul;
	}

}
