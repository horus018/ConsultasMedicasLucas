package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import modelo.Consulta;
import servico.ServicoConsulta;

@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable {

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/ConsultaDAO!servico.ServicoConsulta")
    ServicoConsulta servicoConsulta;

    private boolean isAdmin = false;
    private boolean isLogado = false;
    private String usuario;
    private String senha;

    public UserBean() {
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public boolean getIsLogado() {
        return isLogado;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String fazerLogin() {
        if ("admin".equals(usuario) && "admin1234".equals(senha)) {
            isLogado = true;
            isAdmin = true;
            return "/admin/cadConsultas.xhtml";
        }

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login ou senha incorretos", "Login ou senha incorretos"));
        return null;

    }

    public String fazerLogout() {
        isLogado = false;
        isAdmin = false;
        return "index.xhtml";
    }

    public String acessarPaginaCadastroConsultas() {
        if (isLogado) {
            return "/admin/cadConsultas.xhtml";
        }
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Voce precisa fazer login para acessar esta tela"));
        return null;
    }

    public String cancelarConsulta(Consulta consulta) {
        if (isLogado) {
            servicoConsulta.remover(consulta);
        } else {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Voce precisa fazer login para remover uma consulta"));
        }
        return null;
    }

}
