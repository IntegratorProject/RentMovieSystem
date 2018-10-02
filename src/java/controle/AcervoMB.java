package controle;

import dao.GenericDao;
import entidade.Acervo;
import entidade.Midia;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AcervoMB extends DefaultMB {

    private Acervo acervo = new Acervo();
    private Midia midia = new Midia();
    private List<Acervo> acervos = new ArrayList<>();
    private List<Midia> listMidia = new ArrayList<>();
    private int quantidadeMidia = 0;

    private GenericDao<Acervo> daoAcervo = new GenericDao<Acervo>(Acervo.class);
    private GenericDao<Midia> daoMidia = new GenericDao<Midia>(Midia.class);

    public AcervoMB() {
        updateList();
    }

    public void updateList() {
        try {
            acervos = daoAcervo.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }
    }

    public void cadastrar() {

        if (acervo.getId() == 0) {

            try {

                daoAcervo.salvar(acervo);
                acervo = new Acervo();
                updateList();

            } catch (Exception e) {
                connetionError();
            }

        } else {

            try {

                daoAcervo.editar(acervo);
                acervo = new Acervo();
                updateList();

            } catch (Exception e) {
                connetionError();
            }

        }

    }

    public void excluir(long id) {

        try {

            daoAcervo.delete(id);
            updateList();

        } catch (Exception e) {
            connetionError();
        }

    }

    public void adicionarMidia() {

        for (int i = 0; i < quantidadeMidia; i++) {

            listMidia.add(midia);

        }

        midia = new Midia();
        quantidadeMidia = 0;

    }

    public void removerMidia(Midia midia) {

        listMidia.remove(midia);

    }

    public String formatData(Date data) {
        DateFormat formataData = DateFormat.getDateInstance();
        return formataData.format(data);
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

}
