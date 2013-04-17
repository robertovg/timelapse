package timeLapse.business.categorias.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import timeLapse.business.abs.BusinessObjectTemplate;
import timeLapse.business.categorias.ICategoriaBO;
import timeLapse.resources.dao.ICategoriaDAO;
import timeLapse.resources.persistenceDTO.TldCategoria;
import timeLapse.util.constantes.ConstantesException;
import timeLapse.util.exceptions.Mensaje;
import timeLapse.util.exceptions.TimeLapseException;
@Service
@Scope("session")
public class CategoriaBO extends BusinessObjectTemplate implements ICategoriaBO {
	@Resource
	ICategoriaDAO categoriaDao;
	
	public void setAccDao(ICategoriaDAO categoriaDao) {
		this.categoriaDao = categoriaDao;
	}

	
	public void actualiza(TldCategoria obj) throws Exception {
		
		categoriaDao.save(obj);

	}

	public TldCategoria elimina(String oid) throws Exception {
		TldCategoria categoriaEliminada = null;
		
		try{
			categoriaEliminada = categoriaDao.remove(oid);
		}catch(DataIntegrityViolationException e){
			Mensaje men = factoriaMens.getMensaje(ConstantesException.noSePuedeEliminarCategoriasConTareas);
			throw new TimeLapseException(men);
		}
		return categoriaEliminada;
		

	}

	public List<TldCategoria> listaCategorias(String oidUsuario)
			throws Exception {
		
		List <TldCategoria> resul = new ArrayList<TldCategoria>();
		List <TldCategoria> categoriasBD = categoriaDao.findAll(oidUsuario);
		
		for(TldCategoria categoriaBD : categoriasBD) {
			
			TldCategoria categoria = new TldCategoria();
			categoria.setOid(categoriaBD.getOid());
			categoria.setNombre(categoriaBD.getNombre());
			categoria.setDescripcion(categoriaBD.getDescripcion());
			
			resul.add(categoria);
		}
		
		return resul;
	}


	@Override
	public List<TldCategoria> listaCategoriasUtilizadas(String oidUsuario, boolean dejaSoloAsociadas)
			throws Exception {

		List<TldCategoria> resul = new ArrayList<TldCategoria>();
		//Primero añado las 3 por defecto
		if(!dejaSoloAsociadas){
			TldCategoria categoria1 = new TldCategoria();
			categoria1.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_TODAS_EN_LISTAS"));
			categoria1.setOid(VALOR_TODAS_EN_LISTAS);		
			resul.add(categoria1);
			TldCategoria categoria2 = new TldCategoria();
			categoria2.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_NO_ASOCIADAS_EN_LISTAS"));
			categoria2.setOid(VALOR_NO_ASOCIADAS_EN_LISTAS);		
			resul.add(categoria2);
		}		
		TldCategoria categoria3 = new TldCategoria();
		categoria3.setNombre(bibliotecaI18n.traduce("mensajes.TEXTOS_ASOCIADAS_EN_LISTAS"));
		categoria3.setOid(VALOR_ASOCIADAS_EN_LISTAS);		
		resul.add(categoria3);	
		List<TldCategoria> categoriasBD = categoriaDao.findAllUtilizados(oidUsuario);
		//Añado el resto
		for(TldCategoria categoriaBD : categoriasBD) {			
			TldCategoria grupo = new TldCategoria();
			grupo.setOid(categoriaBD.getOid());
			grupo.setNombre(categoriaBD.getNombre());
			grupo.setDescripcion(categoriaBD.getDescripcion());			
			
			resul.add(grupo);
		}
		
		
		return resul;
	}


	@Override
	public TldCategoria encuentra(String oid) throws Exception {
		return categoriaDao.find(oid);
	}

	
}
