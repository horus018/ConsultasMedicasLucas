package modelo;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Medico implements Serializable {
    
    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY )    
    private int id;
    
    private String nome;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_espec", referencedColumnName = "id")
    private Especialidade especialidade;

    public Medico() {
    }

    public Medico(int id, String nome, Especialidade especialidade) {
        this.id = id;
        this.nome = nome;
        this.especialidade = especialidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + this.id;
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
        final Medico other = (Medico) obj;
        return this.id == other.id;
    }
    
}
