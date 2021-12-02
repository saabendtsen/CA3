package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datamappers.MovieMapper;
import dtos.AddInfoDTO;
import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import entities.User;
import facades.MovieFacade;
import facades.UserFacade;
import security.HttpClient;
import utils.EMF_Creator;

import javax.annotation.security.RolesAllowed;
import javax.faces.component.html.HtmlOutputFormat;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.List;

@Path("/movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    private MovieMapper movieMapper = new MovieMapper();

    @Context
    private UriInfo context;
    private MovieFacade facade = MovieFacade.getMovieFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    SecurityContext securityContext;

    @GET
    @Path("title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitle(@PathParam("title") String title) throws Exception {
        security.HttpClient obj = new HttpClient();
        String response;
        try {
            response = obj.sendGet(title, false);
        } finally {
            obj.close();
        }
        ImdbResponseDTO dto = gson.fromJson(response, ImdbResponseDTO.class);
        return gson.toJson(movieMapper.getMovie(dto));
    }


    @POST
    @Path("like/{id}")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String addLikeMovie(@PathParam("id") String id){
        return gson.toJson(facade.addlikeToMovie(id));
    }

    @GET
    @Path("like")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikedMovies() throws Exception {
        List<MovieDTO> movieDTOList = facade.getTopLikedList();
        return gson.toJson(movieDTOList);
    }

    @GET
    @Path("watchLater")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWatchLater() throws Exception {
//        String thisuser = securityContext.getUserPrincipal().getName();
        List<MovieDTO> movieDTOList = facade.getWatchLaterList("user1");
        return gson.toJson(movieDTOList);
    }


    @POST
    @Path("watchLater/{id}")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String addWatchLater(@PathParam("id") String id){
//        String thisuser = securityContext.getUserPrincipal().getName();
        String thisuser = "user1";
        return gson.toJson(facade.addWatchLater(thisuser,id));
    }

    @DELETE
    @Path("watchLater/{id}")
    //@RolesAllowed("user")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteWatchLater(@PathParam("id") String id){
//        String thisuser = securityContext.getUserPrincipal().getName();

        return gson.toJson(facade.deleteWatchLater(id));
    }

    // TODO: 02-12-2021 Younes - Lav frontend Delete (Watchlater) - tilføj like til forside (liste) og knap til at like på begge sidder (Søgning og watchlater!)

}