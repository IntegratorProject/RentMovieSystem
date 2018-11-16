package controle;

import dao.GenericDao;
import entidade.Funcionario;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.spring.UserSession;
import util.validadores.ValidadorCPF;

@ManagedBean
@ViewScoped
public class FuncionarioMB extends DefaultMB {

    private GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class);
    private Funcionario funcionario = new Funcionario();
    private List<Funcionario> listFuncionario = new ArrayList<>();

    public FuncionarioMB() {
        updateList();
    }

    private void updateList() {
        try {
            listFuncionario = dao.buscarTodos();
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

            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }

        } else {
            try {

                dao.editar(funcionario);
                funcionario = new Funcionario();
                updateList();

            } catch (Exception e) {
                e.printStackTrace();
                connetionError();
            }
        }

    }

    public void statusChange(Funcionario funcionario, boolean status) {

        if (funcionario.equals(UserSession.getCurrentUser())) {

            showErrorMessage("Erro!", "Para alterar o status desta conta, faça login com uma conta diferente.");
            
        } else {
            try {

                funcionario.setEnable(status);
                dao.editar(funcionario);
                updateList();

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

}
