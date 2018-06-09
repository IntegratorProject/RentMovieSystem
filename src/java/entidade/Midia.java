
package entidade;

public class Midia {

    private String tipo;
    private double preco_locacao;
    private Acervo acervo;

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getPreco_locacao() {
        return preco_locacao;
    }

    public void setPreco_locacao(double preco_locacao) {
        this.preco_locacao = preco_locacao;
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }
    
}
