package facades;

import com.sun.tools.javac.comp.Todo;
import entities.MovieLikes;
import entities.RenameMe;
import entities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

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


    public String addlikeToMovie(String id){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            em.persist();
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return null;

    }


    public String addWatchLater(String username, String id){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class,username);
            //user.addWatchLater(id)

            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        return null;

    }









}
