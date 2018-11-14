
package entidade;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "midia")
public class Midia {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @Column(nullable = false)
    private String tipo;
    
    @Column(nullable = false)
    private double precoLocacao;
    
    @Column(nullable = false)
    private String disponibilidade;
    
    @ManyToOne
    private Acervo acervo;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(double preco_locacao) {
        this.precoLocacao = preco_locacao;
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }

    public String getDisponibilidade() {
        return disponibilidade;
    }

    public void setDisponibilidade(String disponibilidade) {
        this.disponibilidade = disponibilidade;
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
        final Midia other = (Midia) obj;
        if (this.id != other.id) {
            return false;
        }
        if (Double.doubleToLongBits(this.precoLocacao) != Double.doubleToLongBits(other.precoLocacao)) {
            return false;
        }
        if (!Objects.equals(this.tipo, other.tipo)) {
            return false;
        }
        if (!Objects.equals(this.disponibilidade, other.disponibilidade)) {
            return false;
        }
        if (!Objects.equals(this.acervo, other.acervo)) {
            return false;
        }
        return true;
    }

    
    
}
