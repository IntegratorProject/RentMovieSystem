package controle;

import dao.GenericDao;
import entidade.Acervo;
import entidade.Midia;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AcervoMB extends DefaultMB implements Serializable{

    private Acervo acervo = new Acervo();
    private Midia midia = new Midia();
    private Midia oldMidia = new Midia();
    private List<Acervo> acervos = new ArrayList<>();
    private List<Acervo> fullListAcervo = new ArrayList<>();
    private List<Midia> listMidia = new ArrayList<>();
    private int quantidadeMidia = 0;

    private GenericDao<Acervo> daoAcervo = new GenericDao<Acervo>(Acervo.class);
    private GenericDao<Midia> daoMidia = new GenericDao<Midia>(Midia.class);

    public AcervoMB() {
        updateList();
    }

    public void updateList() {
        try {
            fullListAcervo.clear();
            acervos.clear();
            fullListAcervo = daoAcervo.buscarTodos();
            for(Acervo a : fullListAcervo){
                acervos.add(a);
            }
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }
    }

    public void cadastrar() {

        if (acervo.getId() == 0) {

            try {

                acervo = daoAcervo.salvar(acervo);

                for (Midia m : listMidia) {

                    Midia tempMidia = new Midia();
                    tempMidia.setAcervo(acervo);
                    tempMidia.setPrecoLocacao(m.getPrecoLocacao());
                    tempMidia.setTipo(m.getTipo());
                    tempMidia.setDisponibilidade(m.getDisponibilidade());
                    daoMidia.salvar(tempMidia);

                }

                limparCadastro();
                updateList();
                
                showInformationMessage("Sucesso!", "Cadastro concluído.");

            } catch (Exception e) {
                connetionError();
                e.printStackTrace();
            }

        } else {

            try {

                acervo = daoAcervo.editar(acervo);
                for(Midia m : listMidia){
                    m.setAcervo(acervo);
                    daoMidia.editar(m);
                }
                
                acervo = new Acervo();
                updateList();
                
                showInformationMessage("Sucesso!", "Alteração concluída.");

            } catch (Exception e) {
                connetionError();
                e.printStackTrace();
            }

        }

    }

    public void limparCadastro() {

        acervo = new Acervo();
        midia = new Midia();
        listMidia.clear();
        fullListAcervo.clear();
        quantidadeMidia = 0;

    }

    public void adicionarMidia() {

        midia.setDisponibilidade("disponivel");
        for (int i = 0; i < quantidadeMidia; i++) {

            listMidia.add(midia);

        }

        midia = new Midia();
        quantidadeMidia = 0;

    }

    public void removerMidia(Midia midia) {

        listMidia.remove(midia);

    }

    public List<Midia> getMidiasBySelectedAcervo() {

        try {
            return daoMidia.buscarCondicao("acervo_id = " + this.acervo.getId());
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
            return null;
        }

    }

    public void alterarMidia() {

        listMidia.remove(oldMidia);
        listMidia.add(midia);
        midia = new Midia();
        oldMidia = new Midia();

    }

    public void loadMidiasBySelectedAcervo() {
        listMidia = getMidiasBySelectedAcervo();
    }

    public Acervo getAcervo() {
        return acervo;
    }

    public void setAcervo(Acervo acervo) {
        this.acervo = acervo;
    }

    public List<Acervo> getAcervos() {
        return acervos;
    }

    public void setAcervos(List<Acervo> acervos) {
        this.acervos = acervos;
    }

    public List<Midia> getListMidia() {
        return listMidia;
    }

    public void setListMidia(List<Midia> listMidia) {
        this.listMidia = listMidia;
    }

    public Midia getMidia() {
        return midia;
    }

    public void setMidia(Midia midia) {
        this.midia = midia;
    }

    public int getQuantidadeMidia() {
        return quantidadeMidia;
    }

    public void setQuantidadeMidia(int quantidadeMidia) {
        this.quantidadeMidia = quantidadeMidia;
    }

    public Midia getOldMidia() {
        return oldMidia;
    }

    public void setOldMidia(Midia oldMidia) {
        this.oldMidia = oldMidia;
    }

    public List<Acervo> getFullListAcervo() {
        return fullListAcervo;
    }

    public void setFullListAcervo(List<Acervo> fullListAcervo) {
        this.fullListAcervo = fullListAcervo;
    }

}
