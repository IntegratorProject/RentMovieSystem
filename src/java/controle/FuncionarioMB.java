
package controle;

import dao.GenericDao;
import entidade.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class FuncionarioMB extends DefaultMB{
    
    private GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class);
    private Funcionario funcionario;
    private List<Funcionario> listFuncionario = new ArrayList<>();

    public FuncionarioMB(){
        updateList();
    }
    
    private void updateList(){
        try{
            listFuncionario = dao.buscarTodos();
        }catch(Exception e){
            e.printStackTrace();
            connetionError();
        }
    }
    
    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getListFuncionario() {
        return listFuncionario;
    }

    public void setListFuncionario(List<Funcionario> listFuncionario) {
        this.listFuncionario = listFuncionario;
    }
    
}
