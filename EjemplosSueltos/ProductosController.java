package edu.curso.java.spring.mvc;


import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import edu.curso.java.spring.bo.*;
import edu.curso.java.spring.mvc.form.ProductoForm;
import edu.curso.java.spring.service.ProductoService;

@Controller
@RequestMapping(value="/productos")
public class ProductosController {

	@Autowired
	private ProductoService productoService;


	@RequestMapping
	public String listar(Model model)  {

		List<Producto> productos = productoService.buscarProductos();
	 	model.addAttribute("productos", productos);
	 	return null;
	}

	@RequestMapping
	public String ver(@RequestParam("id") long id, Model model)  {
		Producto producto = productoService.buscarProducto(id);

	 	model.addAttribute("producto", producto);
	 	return null;
	}


	@RequestMapping
	public String editar(@RequestParam("id") long id, Model model)  {
		Producto producto = productoService.buscarProducto(id);
		ProductoForm productoForm = new ProductoForm();
		productoForm.setIdCategoriaProducto(producto.getCategoriaProducto().getId());
		BeanUtils.copyProperties(producto, productoForm);
		model.addAttribute("productoForm", productoForm);
		return "/productos/form";
	}

	@RequestMapping
	public String borrar(@RequestParam("id") long id, Model model)  {
		productoService.borrarProducto(id);
		return "redirect:/productos/listar.html";
	}

	@RequestMapping
	public String nuevo(Model model) {
		ProductoForm productoForm = new ProductoForm();
		model.addAttribute("productoForm", productoForm);
		return "/productos/form";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String guardar(@ModelAttribute("productoForm") @Valid ProductoForm productoForm, BindingResult result, Model model) {

		System.out.println(result.hasErrors());

		if (result.hasErrors()) {
			return "/productos/form";
		}

		for(Long x : productoForm.getCategoriasIds()) {
			System.out.println(x);
		}


		Long id = productoForm.getId();

		Producto producto = null;

		if(id == null) {
			producto = new Producto();
			BeanUtils.copyProperties(productoForm, producto);
			id = productoService.guardarProducto(producto, productoForm.getIdCategoriaProducto());
		} else {
			producto = productoService.buscarProducto(id);
			BeanUtils.copyProperties(productoForm, producto);
			productoService.actualizarProducto(producto, productoForm.getIdCategoriaProducto());
		}

		return "redirect:/productos/ver.html?id=" + id;
	}



	@ModelAttribute("categorias")
	public List<CategoriaProducto> getCategoriasProducto(){
		return productoService.buscarCategoriasProducto();
	}





}



