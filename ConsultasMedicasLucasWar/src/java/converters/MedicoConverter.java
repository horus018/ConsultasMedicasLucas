package converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import modelo.Medico;
import servico.ServicoMedico;

@Named("medicoConverter")
@ApplicationScoped
public class MedicoConverter implements Converter<Medico> {

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/MedicoDAO!servico.ServicoMedico")
    ServicoMedico servicoMedico;
    
    @Override
    public Medico getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt( value );
            return servicoMedico.findById( id );
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Medico value) {
        if ( value == null) {
            return null;
        }
        return String.valueOf( value.getId() );
    }
    
}
