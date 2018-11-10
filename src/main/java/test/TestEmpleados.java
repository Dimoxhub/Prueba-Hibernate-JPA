package test;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import es.dimox.hibernate.modelo.Direccion;
import es.dimox.hibernate.modelo.Empleado;

public class TestEmpleados {	
		
	/* Crear el gestor de persistencia (EM). */
	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("Persistencia");

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		
		insertInicial();
		
		imprimirTodo();
		
		EntityManager manager = emf.createEntityManager();
		manager.getTransaction().begin();
		Empleado e = manager.find(Empleado.class, 10L);
		e.setNombre("David");
		manager.getTransaction().commit();
		
		imprimirTodo();
		manager.close();

	}

	private static void insertInicial() {
		EntityManager manager = emf.createEntityManager();
		Empleado e = new Empleado(10L,"Pérez","Pepito", LocalDate.of(1979, 6, 6));	
		Direccion d = new Direccion(12L,"DIEZ","ESPAÑA","MADRID","PAIS");
		e.setDireccion(d);
		manager.getTransaction().begin();
		manager.persist(d);
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
	}
	
	

	@SuppressWarnings({ "unchecked", "unused" })
	private static void imprimirTodo() {
		
		EntityManager manager = emf.createEntityManager();
		List<Empleado> emps = (List<Empleado>) manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay " + emps.size() + " empelados en el sistema.");
		
		for (Empleado empleado : emps) {
			System.out.println(emps.toString());
		}
		manager.close();
		
	}

}
