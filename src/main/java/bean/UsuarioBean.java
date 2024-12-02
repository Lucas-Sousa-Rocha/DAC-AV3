package bean;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import entidades.Usuario;
import util.MessageUtil;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario(); // Usuário para login
    private UsuarioDAO usuarioDao = new UsuarioDAO();

    public UsuarioDAO getUsuarioDao() {
		return usuarioDao;
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
	}
	/* LISTAGEM DE USUARIOS */
	private List<Usuario> usuarios;
	
	public List<Usuario> getUsuarios() {
		if (usuarios == null) {
			usuarios = UsuarioDAO.listar();
		}
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	

	// Getter e Setter para o usuário
    public Usuario getUsuario() {
        // Verifica se o usuário está na sessão
        if (usuario == null) {
            usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        }
        return usuario;
    }


    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    

    // Método para obter o nome do usuário logado
    public String getNomeUsuario() {
        Usuario usuarioLogado = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuarioLogado");
        System.out.println("Usuário Logado: " + (usuarioLogado != null ? usuarioLogado.getNome() : "Não Logado"));
        return usuarioLogado != null ? usuarioLogado.getNome() : "Usuário não logado";
    }

    // Método de login
    public String login() {
        Usuario autenticado = usuarioDao.autenticar(usuario.getLogin(), usuario.getSenha());

        if (autenticado != null) {
            // Coloca o usuário logado na sessão
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", autenticado);
            return "opcoes.xhtml?faces-redirect=true";  // Redireciona para a página de opções
        } else {
            // Caso o login falhe, exibe uma mensagem de erro
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha inválidos!", null));
            return null;
        }
    }

    // Método de logout
    public String logout() {
        try {
            // Remove o usuário da sessão
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
            
            // Invalida a sessão
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
            if (session != null) {
                session.invalidate();
            }
            
            // Redireciona para a página de login
            FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
            FacesContext.getCurrentInstance().responseComplete(); // Garantir que a resposta foi finalizada
            return null;  // Não precisa de navegação extra aqui

        } catch (Exception e) {
            e.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao realizar logout", null));
            return null;
        }
    }
    
    public String salvar() {
        try {
            // Salva o usuário no banco de dados
            UsuarioDAO.salvar(usuario);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", null));
            usuario = new Usuario(); // Limpa o formulário após salvar
            return  null/* "login.xhtml?faces-redirect=true"*/;  // Redireciona para a página de login
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Usuario já cadastrado !", null));
            e.printStackTrace();
            return null;  // Permanece na página de cadastro
        }
    }
    
    /*EXCLUIR*/
	public String excluir(Usuario usuario) {
		try {
			UsuarioDAO.excluir(usuario);
			usuarios.remove(usuario);
			MessageUtil.addInfoMsg("Sucesso", "Usuário excluido com sucesso");
		} catch (Exception e) {
			MessageUtil.addErrorMsg("Erro", "Erro ao excluir o Usuário");
		}
		return null;
	}

}
