package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Especialidade;
import servico.ServicoEspecialidade;

@Stateless
public class EspecialidadeDAO implements ServicoEspecialidade {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Especialidade> listar() {
        return em.createQuery("SELECT e FROM Especialidade e ORDER BY e.descricao", Especialidade.class).getResultList();
    }

    @Override
    public Especialidade findById(int id) {
        return em.find(Especialidade.class, id);
    }

}
