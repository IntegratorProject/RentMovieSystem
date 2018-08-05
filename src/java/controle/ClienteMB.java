
package controle;

import dao.GenericDao;
import entidade.Cliente;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import util.ValidadorCPF;

@ManagedBean
@ViewScoped
public class ClienteMB extends DefaultMB{
    
    private GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
    private Cliente cliente = new Cliente();
    private List<Cliente> clientes = new ArrayList<>();

    public ClienteMB() {
        updateList();
    }
    
    public void cadastrar(){
        
        ValidadorCPF vCPF = new ValidadorCPF();
        cliente.setCpf(vCPF.removeMask(cliente.getCpf()));
        
        if(vCPF.isCPF(cliente.getCpf())){
            
            if(cliente.getId() == 0){
                
                try{
                    
                    dao.salvar(cliente);
                    cliente = new Cliente();
                    updateList();
                    
                }catch(Exception e){
                    connetionError();
                }
                
            }else{
                
                try{
                    
                    dao.editar(cliente);
                    cliente = new Cliente();
                    updateList();
                    
                }catch(Exception e){
                    connetionError();
                }
                
            }
            
        }else{
            showErrorMessage("Erro!", "CPF inválido!");
        }
        
    }
    
    public void excluir(long id){
        
    }
    
    private void updateList(){
        
        try{
            clientes = dao.buscarTodos();
        }catch(Exception e){
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
    
    
    
}
