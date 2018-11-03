package controle;

import dao.GenericDao;
import entidade.Cliente;
import entidade.Dependente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.validadores.ValidadorCPF;

@ManagedBean
@ViewScoped
public class ClienteMB extends DefaultMB {

    private GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
    GenericDao<Dependente> daoDep = new GenericDao<>(Dependente.class);
    private Cliente cliente = new Cliente();
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

            } catch (Exception e) {
                connetionError();
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

    private void updateList() {

        try {
            clientes = dao.buscarTodos();
        } catch (Exception e) {
            connetionError();
        }

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

}
