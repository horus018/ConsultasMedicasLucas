package servico;

import java.util.List;
import javax.ejb.Remote;
import modelo.Especialidade;

@Remote
public interface ServicoEspecialidade {
    public List<Especialidade> listar();
    public Especialidade findById( int id );
}
