package br.com.unip.dao;

import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JOptionPane;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

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
			mostraErroValidacaoDosCampos(yourObject);
			System.out.println("DELETE: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	public static List<? extends Object> retornaTodosDados(EntityManager entityManager, Object yourObject) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			return entityManager.createQuery("from " + yourObject.getClass().getName(), yourObject.getClass())
					.getResultList();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			mostraErroValidacaoDosCampos(yourObject);
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
			mostraErroValidacaoDosCampos(yourObject);
			System.out.println("DELETE: " + e.getMessage());
			return null;
		} finally {
			entityManager.close();
		}
	}
	
	public static void atualizaDados(EntityManager entityManager, Object yourObject) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			entityManager.merge(yourObject);
			entityManager.getTransaction().commit();
			
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			mostraErroValidacaoDosCampos(yourObject);
			System.out.println("DELETE: " + e.getMessage());
		} finally {
			entityManager.close();
		}
	}

	public static Object retornaDadosUsandoDuasCondicoes(EntityManager entityManager, Object yourObject,
			String condicao1, String condicao2, String respostaCondicao1, String respostaCondicao2) {
		try {
			entityManager = entityManagerFactory.createEntityManager();
			entityManager.getTransaction().begin();
			return entityManager
					.createQuery("from " + yourObject.getClass().getSimpleName() + " c where c." + condicao1 + " = :"
							+ condicao1 + " AND c." + condicao2 + " = :" + condicao2)
					.setParameter(condicao1, respostaCondicao1).setParameter(condicao2, respostaCondicao2)
					.getSingleResult();

		} catch (Exception e) {
			entityManager.getTransaction().rollback();
			mostraErroValidacaoDosCampos(yourObject);
			System.out.println("DELETE: " + e.getMessage());
			return null;
		} finally {
			entityManager.close();
		}
	}

	private static void mostraErroValidacaoDosCampos(Object yourObject) {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();

		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(yourObject);
		StringBuilder erroValidacao = new StringBuilder();
		if (!constraintViolations.isEmpty()) {
			for (ConstraintViolation<Object> contraints : constraintViolations) {
				erroValidacao.append(contraints.getRootBeanClass().getSimpleName()).append(": ")
						.append(contraints.getPropertyPath()).append(" -> ").append(contraints.getMessage()).append("\n");
			}
		}
		JOptionPane.showMessageDialog(null, erroValidacao.toString(), "Error validação", JOptionPane.ERROR_MESSAGE);
	}

}
