package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import dao.JogoDAO;
import entidades.Campeonato;
import entidades.Jogo;

import java.util.Date;
import java.util.List;

@ManagedBean
@ViewScoped
public class JogoBean {
	private Jogo jogo = new Jogo();
	// private JogoDAO jogoDAO = new JogoDAO();
	private Long campeonatoId;

	private CampeonatoBean campeonatoBean = new CampeonatoBean();

	public CampeonatoBean getCampeonatoBean() {
		return campeonatoBean;
	}

	public void setCampeonatoBean(CampeonatoBean campeonatoBean) {
		this.campeonatoBean = campeonatoBean;
	}

	public Long getCampeonatoId() {
		return campeonatoId;
	}

	public void setCampeonatoId(Long campeonatoId) {
		this.campeonatoId = campeonatoId;
	}

	public String salvar() {
		// Verifica se os times são iguais (com tratamento para maiúsculas e espaços em branco
		if (jogo.getTime1() != null && jogo.getTime2() != null
				&& jogo.getTime1().trim().equalsIgnoreCase(jogo.getTime2().trim())) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,
					"Não é permitido salvar um jogo com os mesmos times.", null));
			System.out.println("Não é permitido salvar um jogo com os mesmos times");
			return null; } // Não prossegue com o salvamento 
		// Preenche a dataCadastro com a data e hora atual
		jogo.setDataCadastro(new Date());
		// Atribui o campeonato ao jogo com o ID selecionado
		Campeonato campeonato = campeonatoBean.getCampeonatoPorId(campeonatoId);
		jogo.setCampeonato(campeonato);
		// Persiste o objeto jogo no banco de dados
		JogoDAO.salvar(jogo);
		// Mensagem de sucesso
		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso !!", null));
		System.out.println("Jogo cadastrado com sucesso");
		// Limpa os campos ou redireciona conforme necessário
		jogo = new Jogo(); // Limpa o objeto Jogo
		// Retorna a página com redirecionamento
		return null; // Redireciona para a página de cadastro
	}

	public List<Jogo> listar() {
		return JogoDAO.listar();
	}

	// Getters e Setters
	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
}
