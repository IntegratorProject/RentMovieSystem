package controle;

import dao.GenericDao;
import entidade.Cliente;
import entidade.Dependente;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import util.validadores.ValidadorCPF;

@ManagedBean
@ViewScoped
public class ClienteMB extends DefaultMB implements Serializable{

    private GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
    GenericDao<Dependente> daoDep = new GenericDao<>(Dependente.class);
    private Cliente cliente = new Cliente();
    private List<Cliente> fullListClientes = new ArrayList<>();
    private List<Cliente> clientes = new ArrayList<>();

    
    public ClienteMB() {
        updateList();
    }

    public void cadastrar() {

        ValidadorCPF vCPF = new ValidadorCPF();
        cliente.setCpf(vCPF.removeMask(cliente.getCpf()));

        if (cliente.getId() == 0) {

            try {

                dao.salvar(cliente);
                Dependente d = new Dependente();
                d.setCliente(cliente);
                d.setDataNascimento(cliente.getDataNascimento());
                d.setNome(cliente.getNome());
                daoDep.salvar(d);
                cliente = new Cliente();
                updateList();
                
                showInformationMessage("Sucesso!", "Cadastro concluído.");

            } catch (Exception e) {
                connetionError();
                e.printStackTrace();
            }

        } else {

            try {

                Cliente oldCliente = dao.buscarId(cliente.getId());
                dao.editar(cliente);

                if (!oldCliente.getNome().equals(cliente.getNome())
                        || !oldCliente.getDataNascimento().equals(cliente.getDataNascimento())) {

                    List<Dependente> d = daoDep.buscarCondicao("cliente_id = " + oldCliente.getId()
                            + " and nome = '" + oldCliente.getNome() + "'");

                    if (!d.isEmpty()) {
                        Dependente oldDependente = d.get(0);
                        oldDependente.setNome(cliente.getNome());
                        oldDependente.setDataNascimento(cliente.getDataNascimento());
                        daoDep.editar(oldDependente);
                    }

                }

                cliente = new Cliente();
                updateList();
                
                showInformationMessage("Sucesso!", "Alteração concluída.");

            } catch (Exception e) {
                connetionError();
                e.printStackTrace();
            }

        }

    }

    public void changeStatus(Cliente c, boolean status) {

        try {

            c.setEnable(status);
            dao.editar(c);

            List<Dependente> list = daoDep.buscarCondicao("cliente_id = " + c.getId());

            for (Dependente d : list) {
                d.setEnable(status);
                daoDep.editar(d);
            }
            
            if(status){
                showInformationMessage("Sucesso!", "Cliente ativado.");
            }else{
                showInformationMessage("Sucesso!", "Cliente desativado.");
            }

        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    private void updateList() {

        try {
            fullListClientes = dao.buscarTodos();
        } catch (Exception e) {
            e.printStackTrace();
            connetionError();
        }

    }

    public String redirectDependente() {

        FacesContext.getCurrentInstance()
                .getExternalContext()
                .getFlash()
                .put("cliente", cliente);

        return "dependente?faces-redirect=true";

    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    public GenericDao<Cliente> getDao() {
        return dao;
    }

    public void setDao(GenericDao<Cliente> dao) {
        this.dao = dao;
    }

    public List<Cliente> getFullListClientes() {
        return fullListClientes;
    }

    public void setFullListClientes(List<Cliente> fullListClientes) {
        this.fullListClientes = fullListClientes;
    }
    
}
