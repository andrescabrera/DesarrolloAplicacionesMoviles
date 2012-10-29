package edu.curso.java.spring.dao;

import java.util.List;

import edu.curso.java.spring.bo.CategoriaProducto;


public interface CategoriaProductoDAO {
	public List<CategoriaProducto> recuperarCategoriasProducto();
	public Long guardarCategoriaProducto(CategoriaProducto categoriaProducto);
	public void actualizarCategoriaProducto(CategoriaProducto categoriaProducto);
	public void borrarCategoriaProducto(Long id);
	public CategoriaProducto buscarCategoriaProductoPorId(long id);
}
