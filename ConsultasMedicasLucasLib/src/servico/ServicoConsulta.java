package servico;


import java.util.List;
import javax.ejb.Remote;
import modelo.Consulta;
import modelo.Medico;

@Remote
public interface ServicoConsulta {
    public void inserir(Consulta consulta);
    public void remover(Consulta consulta);
    public List<Consulta> listar(Medico medico);
    public List<Consulta> listarTodasConstulas();
}
