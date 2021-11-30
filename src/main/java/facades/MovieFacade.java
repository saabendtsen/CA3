package facades;

import datamappers.MovieMapper;
import dtos.MovieDTO;
import entities.MovieLikes;
import entities.User;
import entities.WatchList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;

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


    public void addWatchLater(String username, String id) {
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            user.addToWatchList(new WatchList(id));
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }


    public void deleteWatchLater(String username, String id){
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();
            User user = em.find(User.class, username);
            user.getWatchList().removeIf(w -> w.getWatchLaterImdbId().equals(id));
            em.merge(user);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    public List<MovieDTO> getWatchLaterList(String username){
        EntityManager em = emf.createEntityManager();
        List<MovieDTO> movieDTOList = new ArrayList<>();
        MovieMapper movieMapper = new MovieMapper();
        User user;

        try{
            em.getTransaction().begin();
            user = em.find(User.class, username);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

        for(WatchList w : user.getWatchList()){
            movieDTOList.add(movieMapper.getMovieById(w.getWatchLaterImdbId()));
        }
        return movieDTOList;
    }

}


