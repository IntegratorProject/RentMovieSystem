
package controle;

import dao.GenericDao;
import entidade.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClienteMB {
    
    private GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteMB() {
    }
    
    public void excluir(long id){
        
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
    
    
    
}
