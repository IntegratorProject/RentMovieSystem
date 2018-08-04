
package dao;

import banco.Fabrica;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class GenericDao<T> {
    
    private Class<T> classe;
    
    public GenericDao(Class<T> classe){
        
        this.classe = classe;
        
    }
    
    public T salvar(T object) throws Exception{
        
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
            throw new Exception("falha de conexao");
            
        }finally{
            
            if(manager != null){
                manager.close();
            }
            
        }
        
    }
    
    public T editar(T object) throws Exception{
        
        EntityManager manager = null;
        EntityTransaction transac = null;
        
        try{
            
            manager = Fabrica.get().createEntityManager();
            transac = manager.getTransaction();
            
            transac.begin();
            manager.merge(object);
            transac.commit();
            
        }catch(Exception e){
            
            if(transac != null){
                transac.rollback();
            }
            e.printStackTrace();
            
            throw new Exception("falha de conexao");
            
        }finally{
            
            if(manager != null){
                manager.close();
            }
            
        }
        
        return object;
        
    }
    
    public boolean delete(long id) throws Exception{
        
        EntityManager manager = null;
        EntityTransaction transc = null;
        
        try{
            
            manager = Fabrica.get().createEntityManager();
            T object = manager.find(classe, id);
            
            if(object != null){
                
                transc = manager.getTransaction();
                transc.begin();
                
                manager.remove(object);
                
                transc.commit();
                
                return true;
                
            }else{
                return false;
            }
            
        }catch(Exception e){
            
            if(transc != null){
                transc.rollback();
            }
            e.printStackTrace();
            
            throw new Exception("falha de conexao");
            
        }finally{
            
            if(manager != null){
                manager.close();
            }
            
        }
        
    }
    
    public T buscarId(long id) throws Exception{
        
        EntityManager manager = null;
        
        try{
        
            manager = Fabrica.get().createEntityManager();
            return manager.find(classe, id);
            
        }catch(Exception e){
            
            e.printStackTrace();
            throw new Exception("falha de conexao");
            
        }finally{
            if(manager != null){
                manager.close();
            }
        }
        
    }
    
    public List<T> buscarTodos() throws Exception{
        
        EntityManager manager = null;
        
        try{
            
            manager = Fabrica.get().createEntityManager();
            Query query = manager.createQuery(" from "+classe.getName());
            return query.getResultList();
            
        }catch(Exception e){
            
            e.printStackTrace();
            throw new Exception("falha de conexao");
            
        }finally{
            if(manager != null){
                manager.close();
            }
        }
        
    }
    
}
