package consultasMedicas;

import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import modelo.Consulta;
import servico.ServicoConsulta;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Stateless
@Path("/consultas")
public class ConsultaResource {

    
    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/ConsultaDAO!servico.ServicoConsulta")
    ServicoConsulta servicoConsulta;

    public ConsultaResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Consulta> getJson() {
        return servicoConsulta.listarTodasConstulas();
    }
}
