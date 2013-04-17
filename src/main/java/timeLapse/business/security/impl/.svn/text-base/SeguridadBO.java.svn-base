package timeLapse.business.security.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.servlet.http.HttpSession;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.security.ISeguridadBO;
import timeLapse.controller.interceptors.i18n.IBibliotecaInternacional;
import timeLapse.resources.dao.IPermisosDAO;
import timeLapse.resources.dao.IUsuarioDAO;
import timeLapse.resources.dto.UsuarioDTO;
import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlpRol;
import timeLapse.resources.persistenceDTO.TlrPermiso;
import timeLapse.util.Utiles;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.IFactoriaMensaje;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;
@Service
@Scope("session")
public class SeguridadBO  extends BusinessObjectTemplate implements ISeguridadBO {
	@Resource
	private IUsuarioDAO usuDao;
	
	@Resource
	private IPermisosDAO permisoDao;

	public UsuarioDTO login(String nombreUsuario, String passwordMD5) throws TimeLapseException{
		UsuarioDTO resul = null;
		//Pido al Dao el usuario de la base de datos 
		TldUsuario usuarioBD = null;
		try{
			usuarioBD = usuDao.findByNombreUsuarioYPassword(
					nombreUsuario, passwordMD5);
		}catch (NonUniqueResultException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.masDeUnUsuarioMismoNombrePasswd, nombreUsuario);
			throw new TimeLapseException(men);
		}catch (NoResultException e) {
			
			Mensaje men = factoriaMens.getMensaje(ConstantesException.nombreUsuarioPasswordIncorrecto);
			throw new TimeLapseException(men);
				
		}
		
		//Si el usuario viene de la base de datos, lo devuelvo 
		if(usuarioBD != null){
			resul = new UsuarioDTO();
			resul.setNombre(usuarioBD.getNombreUsuario());
			resul.setPasswordMD5(usuarioBD.getContrasenna());
			resul.setOid(usuarioBD.getOid());
			TlpRol rolBD = usuarioBD.getTlpRol();
			TlpRol rolAux = new TlpRol(rolBD.getOid(), bibliotecaI18n.traduce(rolBD.getNombre()), bibliotecaI18n.traduce(rolBD.getDescripcion()), rolBD.getGrado(),null, null);
			
			resul.setRol(rolAux);
		}
		

		return resul;

	}
	
	
	public boolean procesadoLogin (HttpSession session, String nombreUsuario, String password){

	    
	    boolean resul = false;
	    if(session != null && nombreUsuario != null && password != null ){	    	
	    	// Use the security manager to validate the user's username and password.
	    	String passwordMD5 = Utiles.codificaMD5(password);
		    
	    	UsuarioDTO user = null;
	    		try{
	    			user = login(nombreUsuario, passwordMD5);
	    		}catch (TimeLapseException e) {
					//No hago nada por que siemplemente se devuelve false en el método
				}

		    if (user != null) {
		    	//El usuario se hace login
		    	session.setAttribute(NOMBRE_USUARIO_SESSION, user);
		        resul = true;
		    } else {
		    	//El usuario no hace login
		    	resul = false;
		    }

	    }
	    	    
	    return resul;
	}

	public void setUsuDao(IUsuarioDAO usuDao) {
		this.usuDao = usuDao;
	}
	
	/**
	 * @param permisoDao the permisoDao to set
	 */
	public void setPermisoDao(IPermisosDAO permisoDao) {
		this.permisoDao = permisoDao;
	}
	

	public void setBibliotecaI18n(IBibliotecaInternacional bibliotecaI18n) {
		this.bibliotecaI18n = bibliotecaI18n;
	}


	public void setFactoriaMens(IFactoriaMensaje factoriaMens) {
		this.factoriaMens = factoriaMens;
	}


	public boolean tienePermisos(TlpRol rol, List<String> acciones) {
		
		List<TlrPermiso> permiso = permisoDao.findAllPorNombre(rol, acciones);
		
		boolean resul = !permiso.isEmpty();
		
		return resul;
	}


	




	

}
