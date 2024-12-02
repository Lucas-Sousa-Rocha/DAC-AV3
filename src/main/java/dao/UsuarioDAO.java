package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import entidades.Usuario;
import util.JPAUtil;

public class UsuarioDAO {

  
    
	public Usuario autenticar(String login, String senha) {
		EntityManager em = JPAUtil.criarEntityManager();
	    try {
	        TypedQuery<Usuario> query = em.createQuery(
	            "SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", 
	            Usuario.class
	        );
	        query.setParameter("login", login);
	        query.setParameter("senha", senha);
	        query.setMaxResults(1);  // Limita os resultados para 1
	        return query.getSingleResult();  // Retorna o único resultado encontrado
	    } catch (NoResultException e) {
	        return null;  // Retorna null se não encontrar nenhum usuário
	    }
	}

	public static void salvar(Usuario usuario) throws Exception {
		EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(usuario);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
   
	
	public List<Usuario> listar() {
		EntityManager em = JPAUtil.criarEntityManager();
		try {
			TypedQuery<Usuario> query = em.createQuery("SELECT a FROM Aluno a", Usuario.class);
			return query.getResultList();
		} finally {
			em.close();
		}
	}

}

