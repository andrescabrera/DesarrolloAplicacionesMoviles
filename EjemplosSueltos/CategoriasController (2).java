package edu.curso.java.spring.mvc;


import java.beans.PropertyEditorSupport;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.xml.ws.BindingType;


import org.springframework.beans.factory.annotation.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import edu.curso.java.spring.bo.*;
import edu.curso.java.spring.dao.CategoriaProductoDAO;
import edu.curso.java.spring.service.ProductoService;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

	
	
	@ResponseBody
	@RequestMapping(value="/ver/{id}", method=RequestMethod.GET)
	public CategoriaProducto ver(@PathVariable long id)  {
		CategoriaProducto categoriaProducto = categoriaProductoDAO.buscarCategoriaProductoPorId(id);
	 	return categoriaProducto;
	}
	
	@ResponseBody
	@RequestMapping(value="/listar", method=RequestMethod.GET)
	public List<CategoriaProducto> listar()  {
		List<CategoriaProducto> categorias = categoriaProductoDAO.recuperarCategoriasProducto();
	 	return categorias;
	}
	
	
	@ResponseBody
	@RequestMapping(value="/guardar", method=RequestMethod.POST)
	public Long guardar(@RequestParam("nombre") String nombre)  {
		CategoriaProducto categoriaProducto = new CategoriaProducto();
		categoriaProducto.setNombre(nombre);
		categoriaProductoDAO.guardarCategoriaProducto(categoriaProducto);
		return categoriaProducto.getId();
	}
	
	
	
	
}



