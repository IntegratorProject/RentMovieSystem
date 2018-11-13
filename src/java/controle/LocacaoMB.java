
package controle;

import dao.GenericDao;
import entidade.ItensLocacao;
import entidade.Locacao;
import entidade.Midia;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class LocacaoMB extends DefaultMB{
    
    private Locacao locacao = new Locacao();
    private ItensLocacao itemLocacao = new ItensLocacao();
    private List<Midia> midiasDisponiveis = new ArrayList<>();
    private List<Locacao> listLocacao = new ArrayList<>();
    private List<ItensLocacao> listItensLocacao = new ArrayList<>();

    private GenericDao<Locacao> daoLocacao = new GenericDao<>(Locacao.class);
    private GenericDao<ItensLocacao> daoItensLocacao = new GenericDao<>(ItensLocacao.class);
    private GenericDao<Midia> daoMidia = new GenericDao<>(Midia.class);

    public LocacaoMB() {
        updateList();
    }
    
    private void updateList(){
        try{
            listLocacao = daoLocacao.buscarTodos();
        }catch(Exception e){
            e.printStackTrace();
            connetionError();
        }
    }
    
    public void updateValidMidias(){
        try{
            midiasDisponiveis = daoMidia.buscarCondicao("disponibilidade = 'disponivel'");
            filtrarListaMidias();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void filtrarListaMidias(){
        //metodo q filtra a lista de midias por classificacao etaria
    }

    public Locacao getLocacao() {
        return locacao;
    }

    public void setLocacao(Locacao locacao) {
        this.locacao = locacao;
    }

    public ItensLocacao getItemLocacao() {
        return itemLocacao;
    }

    public void setItemLocacao(ItensLocacao itemLocacao) {
        this.itemLocacao = itemLocacao;
    }

    public List<Locacao> getListLocacao() {
        return listLocacao;
    }

    public void setListLocacao(List<Locacao> listLocacao) {
        this.listLocacao = listLocacao;
    }

    public List<ItensLocacao> getListItensLocacao() {
        return listItensLocacao;
    }

    public void setListItensLocacao(List<ItensLocacao> listItensLocacao) {
        this.listItensLocacao = listItensLocacao;
    }

    public List<Midia> getMidiasDisponiveis() {
        return midiasDisponiveis;
    }

    public void setMidiasDisponiveis(List<Midia> midiasDisponiveis) {
        this.midiasDisponiveis = midiasDisponiveis;
    }
    
}
