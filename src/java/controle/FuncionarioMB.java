package controle;

import dao.GenericDao;
import entidade.Funcionario;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.spring.UserSession;
import util.validadores.ValidadorCPF;

@ManagedBean
@ViewScoped
public class FuncionarioMB extends DefaultMB implements Serializable{

    private GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class);
    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> listFuncionario = new ArrayList<>();
    private List<Funcionario> fullListFuncionario = new ArrayList<>();

    public FuncionarioMB() {
        updateList();
    }

    private void updateList() {
        try {
            fullListFuncionario.clear();
            listFuncionario.clear();
            fullListFuncionario = dao.buscarTodos();
            for(Funcionario f : fullListFuncionario){
                listFuncionario.add(f);
            }
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }
    }

    public void cadastrar() {

        ValidadorCPF vCpf = new ValidadorCPF();
        funcionario.setCpf(vCpf.removeMask(funcionario.getCpf()));

        if (funcionario.getId() == 0) {

            try {

                dao.salvar(funcionario);
                funcionario = new Funcionario();
                updateList();
                
                showInformationMessage("Sucesso!", "Cadastro concluído.");

            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }

        } else {
            
            try {

                dao.editar(funcionario);
                funcionario = new Funcionario();
                updateList();

                showInformationMessage("Sucesso!", "Alteração concluída.");
                
            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }
        
        }

    }

    public void statusChange(Funcionario funcionario, boolean status) {

        if (funcionario.equals(UserSession.getCurrentUser())) {

            showWarningMessage("Atenção", "Para alterar o status desta conta, faça login com uma conta diferente.");
            
        } else {
            try {

                funcionario.setEnable(status);
                dao.editar(funcionario);
                updateList();

                if(status){
                    showInformationMessage("Sucesso!", "Funcionario ativado.");
                }else{
                    showInformationMessage("Sucesso!", "Funcionario desativado.");
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }
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

    public List<Funcionario> getFullListFuncionario() {
        return fullListFuncionario;
    }

    public void setFullListFuncionario(List<Funcionario> fullListFuncionario) {
        this.fullListFuncionario = fullListFuncionario;
    }

}
