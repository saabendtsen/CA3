package facades;

import entities.MovieLikes;
import entities.RenameMe;
import entities.Role;
import entities.User;
import org.junit.jupiter.api.*;
import utils.EMF_Creator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import static org.junit.jupiter.api.Assertions.*;

class MovieFacadeTest {

    private static EntityManagerFactory emf;
    private static MovieFacade facade;



    public MovieFacadeTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = MovieFacade.getMovieFacade(emf);
    }

    @AfterAll
    public static void tearDownClass() {
//        Clean up database after test is done or use a persistence unit with drop-and-create to start up clean on every test
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            User user = new User("user", "user1");
            User admin = new User("admin", "admin1");
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user.addRole(userRole);
            admin.addRole(adminRole);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(adminRole);
            em.getTransaction().commit();
        } finally {
            em.close();
        }

    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
//        Remove any data after each test was run
    }

    @Test
    void addlikeToMovie() {


        MovieLikes movieLikes = facade.addlikeToMovie("tt4972582");

        assertEquals(movieLikes.getQuantity(),1);


    }

    @Test
    void addWatchLater() {
    }

    @Test
    void deleteWatchLater() {
    }

    @Test
    void getWatchLaterList() {
    }

    @Test
    void getTopLikedList() {
    }
}