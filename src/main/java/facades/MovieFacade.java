package facades;

import javax.persistence.EntityManagerFactory;

public class MovieFacade {


    private static EntityManagerFactory emf;
    private static MovieFacade facade;


    private MovieFacade(){
    }


    public static MovieFacade getMovieFacade(EntityManagerFactory _emf){
        if (facade == null){
            emf = _emf;
            facade = new MovieFacade();
        }
        return facade;
    }





}
