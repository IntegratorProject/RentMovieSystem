
package entidade;

import java.util.Date;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "locacao")
public class Locacao {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataLocacao = new Date();
    
    @Column(nullable = false)
    private boolean reserva;
    
    @Column(nullable = false)
    private double precoLocacao;
    
    @Temporal(TemporalType.DATE)
    private Date dataDevolucao;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataPrevDevolucao;
    
    @Column(nullable = false)
    private double valorPago;
    
    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date dataPagamento;
    
    @Column(nullable = false)
    private String descricaoMulta;
    
    @Column(nullable = false)
    private double valorMulta;
    
    @ManyToOne
    private Funcionario funcionario;
    
    @ManyToOne
    private Dependente dependente;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataLocacao() {
        return dataLocacao;
    }

    public void setDataLocacao(Date dataLocacao) {
        this.dataLocacao = dataLocacao;
    }

    public boolean isReserva() {
        return reserva;
    }

    public void setReserva(boolean reserva) {
        this.reserva = reserva;
    }

    public double getPrecoLocacao() {
        return precoLocacao;
    }

    public void setPrecoLocacao(double precoLocacao) {
        this.precoLocacao = precoLocacao;
    }

    public Date getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(Date dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public Date getDataPrevDevolucao() {
        return dataPrevDevolucao;
    }

    public void setDataPrevDevolucao(Date dataPrevDevolucao) {
        this.dataPrevDevolucao = dataPrevDevolucao;
    }

    public double getValorPago() {
        return valorPago;
    }

    public void setValorPago(double valorPago) {
        this.valorPago = valorPago;
    }

    public Date getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(Date dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public String getDescricaoMulta() {
        return descricaoMulta;
    }

    public void setDescricaoMulta(String descricaoMulta) {
        this.descricaoMulta = descricaoMulta;
    }

    public double getValorMulta() {
        return valorMulta;
    }

    public void setValorMulta(double valorMulta) {
        this.valorMulta = valorMulta;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
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
        final Locacao other = (Locacao) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.reserva != other.reserva) {
            return false;
        }
        if (Double.doubleToLongBits(this.precoLocacao) != Double.doubleToLongBits(other.precoLocacao)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorPago) != Double.doubleToLongBits(other.valorPago)) {
            return false;
        }
        if (Double.doubleToLongBits(this.valorMulta) != Double.doubleToLongBits(other.valorMulta)) {
            return false;
        }
        if (!Objects.equals(this.descricaoMulta, other.descricaoMulta)) {
            return false;
        }
        if (!Objects.equals(this.dataLocacao, other.dataLocacao)) {
            return false;
        }
        if (!Objects.equals(this.dataDevolucao, other.dataDevolucao)) {
            return false;
        }
        if (!Objects.equals(this.dataPrevDevolucao, other.dataPrevDevolucao)) {
            return false;
        }
        if (!Objects.equals(this.dataPagamento, other.dataPagamento)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        if (!Objects.equals(this.dependente, other.dependente)) {
            return false;
        }
        return true;
    }

    
    
}
