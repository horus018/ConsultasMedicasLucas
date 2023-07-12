package servico;

import java.util.List;
import javax.ejb.Remote;
import modelo.Especialidade;
import modelo.Medico;

@Remote
public interface ServicoMedico {
    public List<Medico> listarPorEspecialidade(Especialidade especialidade);
    public List<Medico> listar();
    public Medico findById( int id );
}
