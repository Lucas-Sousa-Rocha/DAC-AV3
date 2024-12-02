package bean;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import dao.UsuarioDAO;
import entidades.Usuario;

@ManagedBean
@SessionScoped
public class UsuarioBean {

    private Usuario usuario = new Usuario(); // Usuário para login
    private UsuarioDAO usuarioDao = new UsuarioDAO();
    private List<Usuario> usuarios;

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Usuario> getUsuarioDao() {
		return usuarioDao.listar();
	}

	public void setUsuarioDao(UsuarioDAO usuarioDao) {
		this.usuarioDao = usuarioDao;
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
    
    /*public String salvar() {
	    try {
	        UsuarioDAO.salvar(usuario);
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuário cadastrado com sucesso!", null));
	        usuario = new Usuario(); // Limpa o formulário após salvar
	        return "login.xhtml?faces-redirect=true"; // Redireciona para a página de login
	    } catch (Exception e) {
	        FacesContext.getCurrentInstance().addMessage(null,
	            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro ao cadastrar usuário!", null));
	        e.printStackTrace();
	        return null;
	    }
	}*/
    
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
    
    /*public void verificarLogin() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            // Verifica se o usuário está logado
            Usuario usuarioLogado = (Usuario) context.getExternalContext().getSessionMap().get("usuarioLogado");

            if (usuarioLogado == null) {
                // Redireciona para a página de login
                context.getExternalContext().redirect("login.xhtml");
                context.responseComplete(); // Garante que a resposta será encerrada
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/


    
    


}
