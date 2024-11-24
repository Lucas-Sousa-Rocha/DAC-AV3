package dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import entidades.Jogo;
import util.JPAUtil;

public class JogoDAO  {

	public static void salvar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.persist(j);
		em.getTransaction().commit();
		em.close();
	}

    public static void editar(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		em.merge(j);
		em.getTransaction().commit();
		em.close();
	}

    public static void excluir(Jogo j) {
		EntityManager em = JPAUtil.criarEntityManager();
		em.getTransaction().begin();
		j = em.find(Jogo.class, j.getId());
		em.remove(j);
		em.getTransaction().commit();
		em.close();
	}

    @SuppressWarnings("unchecked")
	public static List<Jogo> listar() {
    	EntityManager em = JPAUtil.criarEntityManager();
		Query q = em.createQuery("select j from Jogo j order by j.id");
		List<Jogo> lista = q.getResultList();
		em.close();
		return lista;
    }
    
}
