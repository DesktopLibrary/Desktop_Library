package library.dataBroker;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Manager {

    private static EntityManagerFactory emf;
    private static EntityManager em;

    private Manager(){}

    public static EntityManager getInstance() {
        if(em != null){
            return em;
        }else{
            emf = Persistence.createEntityManagerFactory("lib_demo");
            em = emf.createEntityManager();
            return em;
        }
    }
}
