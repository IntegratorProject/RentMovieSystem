
package dao;

import entidade.Cliente;

public class DaoClienteTest {
    
    public static void main(String[] args){
        
        Cliente c = new Cliente();
        c.setNome("Eduardo");
        c.setEmail("eryeskurblrsh");
        
        GenericDao<Cliente> dao = new GenericDao<>(Cliente.class);
        dao.salvar(c);
        
    }
    
}
