package utils;


import entities.Role;
import entities.User;
import entities.WatchList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class SetupTestUsers {

  public static void main(String[] args) {

    EntityManagerFactory emf = EMF_Creator.createEntityManagerFactory();
    EntityManager em = emf.createEntityManager();

    User user = new User("user", "user1");
    User user1 = new User("user1", "user1");

    User admin = new User("admin", "admin1");
    WatchList watchList = new WatchList("TT2131231231");

    try {
      em.getTransaction().begin();
      Role userRole = new Role("user");
      Role adminRole = new Role("admin");
      user.addRole(userRole);
      user1.addRole(userRole);
      admin.addRole(adminRole);
      user.addToWatchList(watchList);
      user1.addToWatchList(watchList);
      em.persist(userRole);
      em.persist(adminRole);
      em.persist(user);
      em.persist(user1);
      em.persist(admin);
      em.getTransaction().commit();
      System.out.println("Users Created!");
    } catch (Exception e){
      System.out.println(e.getMessage());
    }
  }
}
