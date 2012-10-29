package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.Producto;


public interface ProductoDAO {

	public Long guardarProducto(Producto producto);
	public void actualizarProducto(Producto producto);
	public void borrarProducto(Long id);
	public Producto buscarProductoPorId(Long id);
	public List<Producto> recuperarProductos();

	
}
