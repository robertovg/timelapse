package timeLapse.resources.dto;

import timeLapse.resources.persistenceDTO.TldUsuario;
import timeLapse.resources.persistenceDTO.TlpRol;

/**
 * Clase que sirve para representar al usuario de la aplicación
 * @author robe
 */
public class UsuarioDTO {
	private String oid;
	private String nombre;
	private String nombrePropio;
	private String apellido1;
	private String apellido2;
	private String passwordMD5;
	private TlpRol rol;
	/**
	 * Constructor vacío
	 */
	public UsuarioDTO(){
		super();
	}
	
	/**
	 * Usuario utilizado para crear los usuario y guardarlos para mantenerlo en sesión
	 * @param nombreUsuario
	 * @param passwordMD5
	 */
	public UsuarioDTO(String nombreUsuario, String passwordMD5) {
		this();
		this.nombre = nombreUsuario;
		this.passwordMD5 = passwordMD5;
	}
	/**
	 * Constructor usado para insertar los valores a mostrar en el grid de usuario, los valores
	 * necesarios
	 * @param usuarioBD
	 */
	public UsuarioDTO(TldUsuario usuarioBD){
		this.oid = usuarioBD.getOid();
		this.nombre = usuarioBD.getNombreUsuario();
		this.nombrePropio = usuarioBD.getNombrePropio();
		this.apellido1 = usuarioBD.getApellido1();
		this.apellido2 = usuarioBD.getApellido2();
	}
	
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombreUsuario) {
		this.nombre = nombreUsuario;
	}
	/**
	 * @return the nombrePropio
	 */
	public String getNombrePropio() {
		return nombrePropio;
	}


	/**
	 * @param nombrePropio the nombrePropio to set
	 */
	public void setNombrePropio(String nombrePropio) {
		this.nombrePropio = nombrePropio;
	}


	/**
	 * @return the apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}


	/**
	 * @param apellido1 the apellido1 to set
	 */
	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}


	/**
	 * @return the apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}


	/**
	 * @param apellido2 the apellido2 to set
	 */
	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}


	public String getPasswordMD5() {
		return passwordMD5;
	}
	public void setPasswordMD5(String passwordMD5) {
		this.passwordMD5 = passwordMD5;
	}


	public TlpRol getRol() {
		return rol;
	}


	public void setRol(TlpRol rol) {
		this.rol = rol;
	}


	public String getOid() {
		return oid;
	}


	public void setOid(String oidUsuario) {
		this.oid = oidUsuario;
	}
	
	

}
