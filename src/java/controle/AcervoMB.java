
package controle;

import dao.GenericDao;
import entidade.Acervo;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class AcervoMB extends DefaultMB{
    
    private Acervo acervo = new Acervo();
    private List<Acervo> acervos = new ArrayList<>();
    private GenericDao<Acervo> dao = new GenericDao<Acervo>(Acervo.class);

    public AcervoMB() {
        updateList();
    }
    
    public void updateList(){
        try{
            acervos = dao.buscarTodos();
        }catch(Exception e){
            e.printStackTrace();
            connetionError();
        }
    }
    
    public void cadastrar() {
        
        if (acervo.getId() == 0) {

            try {

                dao.salvar(acervo);
                acervo = new Acervo();
                updateList();

            } catch (Exception e) {
                connetionError();
            }

        } else {

            try {

                dao.editar(acervo);
                acervo = new Acervo();
                updateList();

            } catch (Exception e) {
                connetionError();
            }

        }

    }
    
    public void excluir(long id) {

        try {

            dao.delete(id);
            updateList();

        } catch (Exception e) {
            connetionError();
        }

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
    
}
