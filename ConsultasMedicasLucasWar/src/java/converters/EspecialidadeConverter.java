package converters;

import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Named;
import modelo.Especialidade;
import servico.ServicoEspecialidade;

@Named("especialidadeConverter")
@ApplicationScoped
public class EspecialidadeConverter implements Converter<Especialidade> {

    @EJB(lookup = "java:global/ConsultasMedicasLucasEJB/EspecialidadeDAO!servico.ServicoEspecialidade")
    ServicoEspecialidade servicoEspecialidade;
    
    @Override
    public Especialidade getAsObject(FacesContext context, UIComponent component, String value) {
        try {
            int id = Integer.parseInt( value );
            return servicoEspecialidade.findById( id );
        } catch(Exception ex) {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Especialidade value) {
        if ( value == null) {
            return null;
        }
        return String.valueOf( value.getId() );
    }
    
}
