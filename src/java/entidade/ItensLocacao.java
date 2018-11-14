
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
@Table(name = "itensLocacao")
public class ItensLocacao {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    @ManyToOne
    private Locacao locacao;
    
    @ManyToOne
    private Midia midia;

    @Column(nullable = false)
    private Double preco;
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
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
        final ItensLocacao other = (ItensLocacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.locacao, other.locacao)) {
            return false;
        }
        if (!Objects.equals(this.midia, other.midia)) {
            return false;
        }
        if (!Objects.equals(this.preco, other.preco)) {
            return false;
        }
        return true;
    }
    
}
