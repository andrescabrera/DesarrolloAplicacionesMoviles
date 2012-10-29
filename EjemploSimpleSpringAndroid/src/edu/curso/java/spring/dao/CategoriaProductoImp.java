package edu.curso.java.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.curso.java.spring.bo.CategoriaProducto;

@Repository
public class CategoriaProductoImp implements  CategoriaProductoDAO{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CategoriaProducto> recuperarCategoriasProducto() {
		return  sessionFactory.getCurrentSession().createQuery("from CategoriaProducto").list();
	}

	@Override
	public CategoriaProducto buscarCategoriaProductoPorId(
			long id) {
		// TODO Auto-generated method stub
		return (CategoriaProducto) sessionFactory.getCurrentSession().get(CategoriaProducto.class, id);
	}

	@Override
	public Long guardarCategoriaProducto(CategoriaProducto categoriaProducto) {
		sessionFactory.getCurrentSession().save(categoriaProducto);
		return categoriaProducto.getId();
	}

	@Override
	public void actualizarCategoriaProducto(CategoriaProducto categoriaProducto) {
		sessionFactory.getCurrentSession().update(categoriaProducto);
	}

	@Override
	public void borrarCategoriaProducto(Long id) {
		CategoriaProducto categoriaProducto = this.buscarCategoriaProductoPorId(id);
		sessionFactory.getCurrentSession().delete(id);
	}

}
