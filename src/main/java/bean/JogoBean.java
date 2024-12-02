package bean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;
import org.primefaces.event.RowEditEvent;
import dao.JogoDAO;
import entidades.Campeonato;
import entidades.Jogo;
import util.MessageUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	/*EXCLUIR*/
	public String excluir(Jogo j) {
		try {
			JogoDAO.excluir(j);
			Listarjogos.remove(j);
			MessageUtil.addInfoMsg("Sucesso", "Usuário excluido com sucesso");
		} catch (Exception e) {
			MessageUtil.addErrorMsg("Erro", "Erro ao excluir o Usuário");
		}
		return null;
	}
	/*EDITAR*/
	
	public String onRowEdit(RowEditEvent<Jogo> event) {
	    try {
	        Jogo j = event.getObject();
	        
	        // Validação para evitar times iguais
	        if (j.getTime1().equals(j.getTime2())) {
	            throw new IllegalArgumentException("Os times não podem ser iguais.");
	        }

	        // Persistir o jogo se a validação passar
	        JogoDAO.editar(j);
	        MessageUtil.addInfoMsg("Sucesso", "Jogo editado com sucesso");
	        return null;
	    } catch (IllegalArgumentException e) {
	        MessageUtil.addErrorMsg("Erro", e.getMessage());
	        // Reverter edição
	        PrimeFaces.current().ajax().addCallbackParam("validationFailed", true);
	    } catch (Exception e) {
	        MessageUtil.addErrorMsg("Erro", "Erro ao editar o jogo.");
	    }
		return null;
	}

	
	public void onRowCancel(RowEditEvent<Jogo> event) {
    	MessageUtil.addInfoMsg("Cancelado", "Edição cancelada");
    }

	/* LISTAR */
	private List<Jogo> Listarjogos;

	public List<Jogo> getListarjogos() {
		if (Listarjogos == null) {
			Listarjogos = JogoDAO.listar();
		}
		return Listarjogos;
	}

	public void setListarjogos(List<Jogo> lista) {
		this.Listarjogos = lista;
	}

	public Jogo getJogo() {
		return jogo;
	}

	public void setJogo(Jogo jogo) {
		this.jogo = jogo;
	}
	
	private List<ResumoTime> resumoTimes; // Lista para exibir o resumo
	
	public void calcularResumo() {
        Map<String, ResumoTime> mapaResumo = new HashMap<>();

        // Processar cada jogo
        for (Jogo jogo : Listarjogos) {
            processarJogo(jogo, mapaResumo);
        }

        // Atualizar a lista de resumo
        resumoTimes = new ArrayList<>(mapaResumo.values());
    }

    private void processarJogo(Jogo jogo, Map<String, ResumoTime> mapaResumo) {
        // Adicionar ou atualizar time1
        ResumoTime time1 = mapaResumo.computeIfAbsent(jogo.getTime1(), ResumoTime::new);
        ResumoTime time2 = mapaResumo.computeIfAbsent(jogo.getTime2(), ResumoTime::new);

        time1.adicionarGols(jogo.getGolsTime1(), jogo.getGolsTime2());
        time2.adicionarGols(jogo.getGolsTime2(), jogo.getGolsTime1());

        // Atualizar pontos, vitórias, derrotas, empates
        if (jogo.getGolsTime1() > jogo.getGolsTime2()) {
            time1.adicionarVitoria();
            time2.adicionarDerrota();
        } else if (jogo.getGolsTime1() < jogo.getGolsTime2()) {
            time2.adicionarVitoria();
            time1.adicionarDerrota();
        } else {
            time1.adicionarEmpate();
            time2.adicionarEmpate();
        }
    }

    public List<ResumoTime> getResumoTimes() {
        return resumoTimes;
    }

	public void setResumoTimes(List<ResumoTime> resumoTimes) {
		this.resumoTimes = resumoTimes;
	}
    
	/* FILTRO */ 
	
	private List<Jogo> jogos;
    private String timeSelecionado;
    private List<String> times;
    
    public String getTimeSelecionado() {
        return timeSelecionado;
    }

    public void setTimeSelecionado(String timeSelecionado) {
        this.timeSelecionado = timeSelecionado;
    }
    
    public List<Jogo> getJogos() {
        return jogos;
    }

    public void setJogos(List<Jogo> jogos) {
        this.jogos = jogos;
    }
    
    public List<String> getTimes() {
		return times;
	}

	public void setTimes(List<String> times) {
		this.times = times;
	}

	
	
	public String buscarJogosPorTime() {
        if (timeSelecionado != null && !timeSelecionado.trim().isEmpty()) {
            // Chama o JogoDao para buscar os jogos pelo nome do time
            jogos = JogoDAO.buscarJogosPorTime(timeSelecionado);
        }
		return timeSelecionado;
    }
	
}
