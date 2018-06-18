
package dao;

import entidade.Cliente;

public class DaoClienteTest {
    
    public static void main(String[] args){
        
        /*
        Cliente c = new Cliente();
        c.setId(1);
        c.setNome("Eduaweefw");
        c.setEmail("eryeskurblrsh");
        */
        GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
        
        for(Cliente c : dao.buscarTodos()){
            
            System.out.println(c.getNome());
            
        }
        
        
    }
    
}
