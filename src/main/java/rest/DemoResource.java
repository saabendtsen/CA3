package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.UserDTO;
import entities.Role;
import entities.User;

import java.io.IOException;
import java.util.List;
import javax.annotation.security.RolesAllowed;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;

import entities.WatchList;
import facades.UserFacade;
import utils.EMF_Creator;
import utils.HttpUtils;

@Path("info")
public class DemoResource {
    
    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    private UserFacade facade = UserFacade.getUserFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    @Context
    SecurityContext securityContext;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getInfoForAll() {
        return "{\"msg\":\"Hello anonymous\"}";
    }

    //Create Users on Endpoint
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("createusers")
    public void createUsers() {

        EntityManager em = EMF.createEntityManager();

        User user = new User("user", "user1");
        User user1 = new User("user1", "user1");
        User admin = new User("admin", "admin1");


        try {
            em.getTransaction().begin();
            Role userRole = new Role("user");
            Role adminRole = new Role("admin");
            user.addRole(userRole);
            user1.addRole(userRole);
            admin.addRole(adminRole);
            WatchList watchList = new WatchList("tt4972582"); // Split
            WatchList watchList1 = new WatchList("tt4972583"); // idk
            user.addToWatchList(watchList);
            user1.addToWatchList(watchList);
            user1.addToWatchList(watchList1);
            em.persist(userRole);
            em.persist(adminRole);
            em.persist(user);
            em.persist(user1);
            em.persist(admin);
            em.persist(watchList);
            em.persist(watchList1);
            em.getTransaction().commit();
            System.out.println("Users Created!");
        } catch (Exception e){
            System.out.println(e.getMessage());
        } finally {
        em.close();
    }
    }



    @GET
    @Path("crypto")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCrypto() throws IOException {
        String catFact = HttpUtils.fetchData("https://api.coindesk.com/v1/bpi/currentprice.json");
        return catFact;
    }

    @GET
    @Path("catfact")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCatFacts() throws IOException {
        String catFact = HttpUtils.fetchData("https://catfact.ninja/fact");
        return catFact;
    }

    String thisuserrole = "";

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("user")
    @RolesAllowed("user")
    public String getFromUser() {
        String thisuser = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("user")) {
            thisuserrole = "user";
        }
        return "{\"msg\": \"Hello: " + thisuser +  "   -   Role: " + thisuserrole +"\"}";
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin")
    @RolesAllowed("admin")
    public String getFromAdmin() {
        String thisuser = securityContext.getUserPrincipal().getName();
        if (securityContext.isUserInRole("admin")) {
            thisuserrole = "admin";
        }
        return "{\"msg\": \"Hello: " + thisuser +  "   -   Role: " + thisuserrole +"\"}";
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Path("admin/createUser")
    @RolesAllowed("admin")
    @Consumes(MediaType.APPLICATION_JSON)
    public String createUser(String newUser) {
        String thisuser = securityContext.getUserPrincipal().getName();
        UserDTO userDTO = gson.fromJson(newUser, UserDTO.class);
        userDTO = facade.createUser(userDTO);

        return gson.toJson(userDTO);
    }

    @DELETE
    @Path("admin/deleteUser/{name}")
    @RolesAllowed("admin")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("name")String name){
        UserDTO userDTO = facade.deleteUser(name);

        return "Deleted user " + gson.toJson(userDTO);
    }
}