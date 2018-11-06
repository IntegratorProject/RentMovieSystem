
package principal;

import dao.GenericDao;
import entidade.Funcionario;

public class Startup {
    
    public static void main(String[] args){
        
        GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class);
        
        Funcionario f = new Funcionario();
        f.setNome("AdminUser");
        f.setCpf("91364304040");
        f.setLogin("admin");
        f.setSenha("123");
        f.setCargo("Gerente");
        
        Funcionario f2 = new Funcionario();
        f2.setNome("BasicUser");
        f2.setCpf("81112394087");
        f2.setLogin("user");
        f2.setSenha("123");
        f2.setCargo("Atendente");
        
        
        try{
            dao.salvar(f);
            dao.salvar(f2);
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

}
