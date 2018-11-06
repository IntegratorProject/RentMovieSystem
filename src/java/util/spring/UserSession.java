
package util.spring;

import dao.GenericDao;
import entidade.Funcionario;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class UserSession {
    
    public static Funcionario getCurrentUser(){
        
        GenericDao<Funcionario> dao = new GenericDao<>(Funcionario.class);
        String userName="";
        
        Authentication at = (Authentication) SecurityContextHolder
                .getContext()
                .getAuthentication();
        
        if(at != null){
            Object obj = at.getPrincipal();
            
            if(obj instanceof UserDetails) {
                userName = ((UserDetails) obj).getUsername();
            }else{
                userName = obj.toString();
            }
        }
        
        try{
            
            List<Funcionario> userList = dao.buscarCondicao("login = '"+userName+"'");
            
            if(userList.size() > 0){
                return userList.get(0);
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        
        Funcionario f = new Funcionario();
        f.setNome("Desconhecido");
        return f;
        
    }
    
}
