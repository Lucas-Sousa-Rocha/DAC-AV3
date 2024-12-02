package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

import dao.CampeonatoDAO;
import entidades.Campeonato;

@ManagedBean
@ViewScoped
public class CampeonatoBean {

	private Campeonato campeonato;
	private CampeonatoDAO campeonatoDao = new CampeonatoDAO();

	public CampeonatoDAO getCampeonatoDao() {
		return campeonatoDao;
	}

	public void setCampeonatoDao(CampeonatoDAO campeonatoDao) {
		this.campeonatoDao = campeonatoDao;
	}

	public CampeonatoBean() {
		campeonato = new Campeonato(); // Inicializa o objeto campeonato
	}

	public Campeonato getCampeonato() {
		return campeonato;
	}

	public void setCampeonato(Campeonato campeonato) {
		this.campeonato = campeonato;
	}

	public String salvar() {
		try {
			// Simulação do salvamento do campeonato no banco de dados
			CampeonatoDAO.salvar(campeonato);

			// Mensagem de sucesso
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Campeonato cadastrado com sucesso!", null));

			// Limpar o objeto campeonato após o cadastro
			campeonato = new Campeonato(); // Reinicia o formulário

			// Redireciona para a página de listagem de campeonatos ou outra página desejada
			return "listagem_campeonatos.xhtml?faces-redirect=true";

		} catch (Exception e) {
			// Mensagem de erro
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro campeonato já cadastrado!", null));
			e.printStackTrace();
			return null;
		}
	}
	
	 // Método para listar todos os campeonatos
	/* LISTAR */
	private List<Campeonato> Listarjogos;

	public List<Campeonato> getListarjogos() {
		if (Listarjogos == null) {
			Listarjogos = CampeonatoDAO.listarCampeonatos();
		}
		return Listarjogos;
	}

	public void setListarjogos(List<Campeonato> lista) {
		this.Listarjogos = lista;
	}
	
	// Buscar campeonato por ID
	public Campeonato getCampeonatoPorId(Long id) {
        return campeonatoDao.buscarPorId(id);  // Chamada direta ao método do DAO
    }

    // Getters e Setters
    

}