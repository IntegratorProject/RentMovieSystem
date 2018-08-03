
package controle;

import dao.GenericDao;
import entidade.Cliente;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ClienteMB {
    
    private GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
    private Cliente cliente = new Cliente();

    public void imprimir(){
        
        System.out.println(cliente.getNome());
        
    }
    
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    
}
