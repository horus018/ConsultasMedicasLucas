package ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import modelo.Consulta;
import modelo.Medico;
import servico.ServicoConsulta;

@Stateless
public class ConsultaDAO implements ServicoConsulta {

    @PersistenceContext
    EntityManager em;

    @Override
    public void inserir(Consulta consulta) {
        em.persist(consulta);
    }

    @Override
    public void remover(Consulta consulta) {
        consulta = em.merge(consulta); // Anexar a entidade ao contexto de persistência
        em.remove(consulta); // Remover a entidade
    }

    @Override
    public List<Consulta> listar(Medico medico) {
        return em.createQuery("select c from Consulta c where c.medico = :medico order by c.data_hora")
                .setParameter("medico", medico)
                .getResultList();
    }

    @Override
    public List<Consulta> listarTodasConstulas() {
       return em.createQuery("select c from Consulta c order by c.data_hora")
                .getResultList();
    }

}
