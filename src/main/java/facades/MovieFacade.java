package facades;

import entities.MovieLikes;
import entities.User;

import javax.persistence.EntityManager;
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


    public MovieLikes addlikeToMovie(String id){
        EntityManager em = emf.createEntityManager();
        MovieLikes like;
        try {
            em.getTransaction().begin();
            like = em.find(MovieLikes.class,id);

            if(like == null){
                em.persist(like);
            } else {
                like.setQuantity(like.getQuantity() + 1);
                em.merge(like);
            }
            em.getTransaction().commit();
        } finally {
            em.close();
        }
        return like;
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
