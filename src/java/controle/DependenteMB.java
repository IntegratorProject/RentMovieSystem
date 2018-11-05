package controle;

import dao.GenericDao;
import entidade.Cliente;
import entidade.Dependente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class DependenteMB extends DefaultMB {

    private Dependente dependente = new Dependente();
    private Cliente clienteAtual = new Cliente();
    private List<Dependente> listDependente = new ArrayList<>();
    private List<Cliente> listClientesAtivos = new ArrayList<>();
    private GenericDao<Dependente> dao = new GenericDao<>(Dependente.class);

    public DependenteMB() {
        updateList();
        updateListClientes();
    }

    public void cadastrar() {

        dependente.setCliente(clienteAtual);

        if (dependente.getNome().trim().toLowerCase().equals(
                dependente.getCliente().getNome().trim().toLowerCase())) {
            showErrorMessage("Erro!", "O dependente não pode ter o mesmo nome que o dono da conta!");
        } else {
            if (dependente.getId() == 0) {

                try {

                    dao.salvar(dependente);
                    dependente = new Dependente();
                    updateList();

                } catch (Exception e) {
                    e.printStackTrace();
                    connetionError();
                }

            } else {

                try {

                    dao.editar(dependente);
                    dependente = new Dependente();
                    updateList();

                } catch (Exception e) {
                    e.printStackTrace();
                    connetionError();
                }

            }
        }

    }

    public void updateList() {

        try {
            listDependente = dao.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public void updateListClientes() {

        try {

            GenericDao<Cliente> daoCliente = new GenericDao<Cliente>(Cliente.class);
            listClientesAtivos = daoCliente.buscarCondicao("enable = 1");

        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public void statusChange(Dependente d, boolean status) {

        try {
            d.setEnable(status);
            dao.editar(d);
            updateList();
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public Dependente getDependente() {
        return dependente;
    }

    public void setDependente(Dependente dependente) {
        this.dependente = dependente;
    }

    public List<Dependente> getListDependente() {
        return listDependente;
    }

    public void setListDependente(List<Dependente> listDependente) {
        this.listDependente = listDependente;
    }

    public List<Cliente> getListClientesAtivos() {
        return listClientesAtivos;
    }

    public void setListClientesAtivos(List<Cliente> listClientesAtivos) {
        this.listClientesAtivos = listClientesAtivos;
    }

    public Cliente getClienteAtual() {
        return clienteAtual;
    }

    public void setClienteAtual(Cliente clienteAtual) {
        this.clienteAtual = clienteAtual;
    }

}
