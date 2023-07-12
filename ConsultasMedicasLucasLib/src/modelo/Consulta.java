package modelo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Consulta implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )
    private int id;
    
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="id_medico", referencedColumnName = "id")  
    private Medico medico;
    
    private String nome_paciente;
    private String telefone_paciente;
    private Date data_hora;

    public Consulta() { }

    public Consulta(Medico medico, String nome_paciente, String telefone_paciente, Date data_hora) {
        this.medico = medico;
        this.nome_paciente = nome_paciente;
        this.telefone_paciente = telefone_paciente;
        this.data_hora = data_hora;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Medico getMedico() {
        return medico;
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public String getNome_paciente() {
        return nome_paciente;
    }

    public void setNome_paciente(String nome_paciente) {
        this.nome_paciente = nome_paciente;
    }

    public String getTelefone_paciente() {
        return telefone_paciente;
    }

    public void setTelefone_paciente(String telefone_paciente) {
        this.telefone_paciente = telefone_paciente;
    }

    public Date getData_hora() {
        return data_hora;
    }

    public void setData_hora(Date data_hora) {
        this.data_hora = data_hora;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.medico);
        hash = 97 * hash + Objects.hashCode(this.data_hora);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Consulta other = (Consulta) obj;
        if (!Objects.equals(this.medico, other.medico)) {
            return false;
        }
        return Objects.equals(this.data_hora, other.data_hora);
    }
}
