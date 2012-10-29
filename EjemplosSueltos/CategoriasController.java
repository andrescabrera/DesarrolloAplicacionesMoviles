package edu.curso.java.spring.mvc;


import java.util.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import edu.curso.java.spring.bo.*;
import edu.curso.java.spring.dao.CategoriaProductoDAO;

@Controller
@RequestMapping(value="/categorias")
public class CategoriasController {

	@Autowired
	private CategoriaProductoDAO categoriaProductoDAO;


	@RequestMapping
	public String principal(Model model)  {
		return null;
	}

	@ResponseBody
	@RequestMapping(value="/ver/{id}", method=RequestMethod.GET)
	public CategoriaProducto ver(@PathVariable long id)  {
		CategoriaProducto categoriaProducto = categoriaProductoDAO.buscarCategoriaProductoPorId(id);
	 	return categoriaProducto;
	}

	//@ResponseBody
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



