package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Especialidade;
import modelo.Medico;
import servico.ServicoMedico;

@Stateless
public class MedicoDAO implements ServicoMedico{

    @PersistenceContext
    EntityManager em;
    
    @Override
    public List<Medico> listarPorEspecialidade(Especialidade especialidade) {
        return em.createQuery("select m from Medico m where m.especialidade = :especialidade order by m.nome")
                .setParameter("especialidade", especialidade)
                .getResultList();
    }

    @Override
    public List<Medico> listar() {
       return em.createQuery("SELECT m FROM Medico m ORDER BY m.nome", Medico.class).getResultList(); 
    }
    
    public Medico findById( int id ) {
        return em.find(Medico.class, id);
    }
    
}
