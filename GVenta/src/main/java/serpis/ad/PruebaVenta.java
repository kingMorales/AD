package serpis.ad;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Random;


import org.w3c.dom.events.Event;
import org.jboss.*;


public class PruebaVenta {
	
private static EntityManagerFactory entityManagerFactory; 
	
	public static void main(String[] args) {		
		entityManagerFactory =
				Persistence.createEntityManagerFactory("serpis.ad.gventa");
		
		//showCategorias();
		//showArticulos();
		//showCliente();
		//showPedidos();
		//newArticulo();
		newPedido();
		showPedidos();

		
		
		
		//showCliente();
		//newPedido();		
		entityManagerFactory.close();
		

	}
	
	private static void showCategorias() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Categoria> categorias = entityManager
				.createQuery("from Categoria order by id", Categoria.class).getResultList();
		for (Categoria categoria : categorias)
			System.out.println(categoria);
		entityManager.getTransaction().commit();
	}
	
	private static void showArticulos() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Articulo> articulos= entityManager
				.createQuery("from Articulo order by id", Articulo.class).getResultList();
		for (Articulo articulo: articulos)
			System.out.println(articulo);
		entityManager.getTransaction().commit();
	}
	
	
	private static void newArticulo() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		Categoria categoria = entityManager.getReference(Categoria.class, 1L);
		Articulo articulo = new Articulo();
		articulo.setNombre("Nuevo " + new Date());
		articulo.setPrecio(new BigDecimal(6));
		articulo.setCategoria(categoria);
		entityManager.persist(articulo);
		System.out.println("Creado " + articulo);
		entityManager.getTransaction().commit();

	}
	
	private static void showCliente() {
	EntityManager entityManager = entityManagerFactory.createEntityManager();
	entityManager.getTransaction().begin();
	List<Cliente> clientes = entityManager
			.createQuery("from Cliente order by id", Cliente.class).getResultList();
	for (Cliente cliente: clientes)
		System.out.println(cliente);
	entityManager.getTransaction().commit();
	}
	
	private static void showPedidos() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		List<Pedido> pedidos = entityManager
				.createQuery("from Pedido order by id", Pedido.class).getResultList();
		for (Pedido pedido : pedidos)
			System.out.println(pedido);
		entityManager.getTransaction().commit();
	} 
	
	private static void newPedido() {
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		
		
		Pedido pedido = new Pedido();
		Cliente cliente = entityManager.getReference(Cliente.class, 1L);
		pedido.setCliente(cliente);
		Pedidolinea pedidolinea1 = new Pedidolinea();
		
		pedido.getPedidoLineas().add(pedidolinea1);
		pedidolinea1.setPedido(pedido);
		Articulo articulo = entityManager.getReference(Articulo.class, 1L);
		pedidolinea1.setArticulo(articulo);
		
		entityManager.persist(pedido);
		entityManager.getTransaction().commit();

	}
	
	
	
	
	/*
	private static void newPedido() {
		System.out.println("Creando pedido nuevo ");
		Pedido pedido = new Pedido();
		pedido.setFecha(new Date());
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(pedido);
		System.out.println("Creada " + pedido);
		entityManager.getTransaction().commit();
	}*/
}
