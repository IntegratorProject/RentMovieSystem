package banco;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Fabrica {

    private static EntityManagerFactory factory = null;

    public static EntityManagerFactory get() {

        if (factory == null) {

            try {

                factory = Persistence.createEntityManagerFactory("mysqlPU");
                
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        
        return factory;

    }

}
