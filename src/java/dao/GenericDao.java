
package dao;

import banco.Fabrica;
import entidade.Cliente;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GenericDao<T> {
    
    private Class<T> classe;
    
    public GenericDao(Class<T> classe){
        
        this.classe = classe;
        
    }
    
    public T salvar(T object){
        
        EntityManager manager = null;
        EntityTransaction transc = null;
        
        try{
            
            manager = Fabrica.get().createEntityManager();
            transc = manager.getTransaction();
            transc.begin();

            manager.persist(object);
            
            transc.commit();
            return object;
            
        }catch(Exception e){
         
            e.printStackTrace();
            if(transc != null){
                transc.rollback();
            }
            return null;
            
        }finally{
            
            if(manager != null){
                manager.close();
            }
            
        }
        
    }
    
}
