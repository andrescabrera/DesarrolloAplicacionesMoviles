package edu.curso.java.spring.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import edu.curso.java.spring.bo.Producto;

@Repository
public class ProductoDAOImp implements ProductoDAO {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public Long guardarProducto(Producto producto) {
		sessionFactory.getCurrentSession().save(producto);
		return producto.getId();
	}

	@Override
	public void actualizarProducto(Producto producto) {
		sessionFactory.getCurrentSession().update(producto);
	}

	@Override
	public void borrarProducto(Long id) {
		Producto producto = this.buscarProductoPorId(id);
		sessionFactory.getCurrentSession().delete(producto);
		
	}

	@Override
	public Producto buscarProductoPorId(Long id) {
		return (Producto) sessionFactory.getCurrentSession().get(Producto.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Producto> recuperarProductos() {
		return  sessionFactory.getCurrentSession().createQuery("from Producto").list();
	}

}
