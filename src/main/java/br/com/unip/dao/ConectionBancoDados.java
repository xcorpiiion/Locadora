package br.com.unip.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public final class ConectionBancoDados {
	
	private static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("locacao");
	
	private ConectionBancoDados() {

	}

	public static void insereDadosNoBanco(EntityManager entityManager, Object yourObject) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.persist(yourObject);
			entityManager.getTransaction().commit();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();

			System.out.println("DELETE: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}
	
	public static List<? extends Object> retornaTodosDados(EntityManager entityManager, Object yourObject) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			return entityManager.createQuery("from " + yourObject.getClass().getName(), yourObject.getClass()).getResultList();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			
			System.out.println("DELETE: " + e.getMessage());
			return null;
		} finally {
			entityManager.close();
		}
	}
	
	public static Object retornaDadoPorId(EntityManager entityManager, Object yourObject, Long id) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			return entityManager.find(yourObject.getClass(), id);
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			
			System.out.println("DELETE: " + e.getMessage());
			return null;
		} finally {
			entityManager.close();
		}
	}

}
