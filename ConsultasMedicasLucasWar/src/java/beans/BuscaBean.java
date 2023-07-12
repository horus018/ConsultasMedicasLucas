package beans;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import modelo.Consulta;
import modelo.Especialidade;
import modelo.Medico;
import servico.ServicoConsulta;
import servico.ServicoEspecialidade;
import servico.ServicoMedico;

@Named(value = "buscaBean")
@SessionScoped
public class BuscaBean implements Serializable {

    public BuscaBean() {
    }

    Especialidade especialidadeSelecionada;
    Medico medicoSelecionado;
    Consulta consulta;

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/EspecialidadeDAO!servico.ServicoEspecialidade")
    ServicoEspecialidade servicoEspecialidade;

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/MedicoDAO!servico.ServicoMedico")
    ServicoMedico servicoMedico;

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/ConsultaDAO!servico.ServicoConsulta")
    ServicoConsulta servicoConsulta;

    private List<Consulta> consultas;
    private List<SelectItem> especialidades;
    private List<SelectItem> medicos;
    private String nomePaciente;
    private String telefone;
    private Date dataHora;

    public Especialidade getEspecialidadeSelecionada() {
        return especialidadeSelecionada;
    }

    public void setEspecialidadeSelecionada(Especialidade especialidadeSelecionada) {
        this.especialidadeSelecionada = especialidadeSelecionada;
        
        medicos = null;
        medicoSelecionado = null;
        
        if (medicos == null) {
            medicos = new LinkedList<>();
            medicos.add(new SelectItem(null, "Selecione o médico"));
            for (Medico e : servicoMedico.listarPorEspecialidade(especialidadeSelecionada)) {
                medicos.add(new SelectItem(e, e.getNome()));
            }
        }

        if (true) {
            System.out.println("teste");
        }

    }

    public List<SelectItem> getEspecialidades() {
        if (especialidades == null) {
            especialidades = new LinkedList<>();
            especialidades.add(new SelectItem(null, "Selecione a especialidade"));
            for (Especialidade e : servicoEspecialidade.listar()) {
                especialidades.add(new SelectItem(e, e.getDescricao()));
            }
        }
        return especialidades;
    }

    public Medico getMedicoSelecionado() {
        return medicoSelecionado;
    }

    public void setMedicoSelecionado(Medico medicoSelecionado) {
        this.medicoSelecionado = medicoSelecionado;
    }

    public List<SelectItem> getMedicos() {
        return medicos;
    }

    public Date getDataHora() {
        return dataHora;
    }

    public void setDataHora(Date dataHora) {
        this.dataHora = dataHora;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public String getNomePaciente() {
        return nomePaciente;
    }

    public void setNomePaciente(String nomePaciente) {
        this.nomePaciente = nomePaciente;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String agendarConsulta() {
        Date hoje = new Date();
        if (dataHora.before(hoje)) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("A data deve ser futura"));
            return null;
        }

        consulta = new Consulta(medicoSelecionado, nomePaciente, telefone, dataHora);

        for (Consulta consultaAtual : servicoConsulta.listar(medicoSelecionado)) {
            if (consulta.equals(consultaAtual)) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Já existe uma consulta marcada nesse mesmo horario nesse mesmo dia"));
                return null;
            }
        }

        servicoConsulta.inserir(consulta);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Consulta cadastrada!"));
        return null;
    }
    
    public List<Consulta> getServicoConsulta(){
        return servicoConsulta.listar(medicoSelecionado);
    }

}
