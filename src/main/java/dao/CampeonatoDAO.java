package dao;

import java.util.List;

import javax.persistence.EntityManager;

import entidades.Campeonato;
import util.JPAUtil;

public class CampeonatoDAO {

	public static void salvar(Campeonato campeonato) throws Exception {
		EntityManager em = JPAUtil.criarEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(campeonato);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e;
        } finally {
            em.close();
        }
    }
	
	// Método para listar todos os campeonatos
    public static List<Campeonato> listarCampeonatos() {
    	EntityManager em = JPAUtil.criarEntityManager();
        try {
            return em.createQuery("SELECT c FROM Campeonato c", Campeonato.class)
                                .getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Erro ao listar campeonatos", e);
        }
    }

    public Campeonato buscarPorId(Long id) {
    	EntityManager em = JPAUtil.criarEntityManager();
        return em.find(Campeonato.class, id);
    }
}
