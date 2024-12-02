package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import entidades.Jogo;
import util.JPAUtil;

public class JogoDAO {

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

	@SuppressWarnings("unchecked")
	public static List<Jogo> buscarJogosPorTime(String timeId) {
		EntityManager em = JPAUtil.criarEntityManager();
        // Adiciona % no início e no final do nome do time para o LIKE
        String timePattern = "%" + timeId + "%"; // Usando o LIKE para buscar parcialmente
        Query query = em.createNamedQuery("Jogo.buscarPorTime");
        query.setParameter("timeId", timePattern);  // Passando o nome do time com o padrão LIKE
        return query.getResultList();
    }
}
