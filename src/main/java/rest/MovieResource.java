package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import datamappers.MovieMapper;
import dtos.AddInfoDTO;
import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import facades.MovieFacade;
import facades.UserFacade;
import security.HttpClient;
import utils.EMF_Creator;

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
        title = title.replace(" ","%20");
        System.out.println(title);
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
    @Produces(MediaType.APPLICATION_JSON)
    public String addLikeMovie(@PathParam("id") String id){
        facade.addlikeToMovie(id);

        return null;
    }

    @GET
    @Path("like")
    @Produces(MediaType.APPLICATION_JSON)
    public String getLikedMovies(){


        return null;
    }



    @GET
    @Path("watchLater")
    @Produces(MediaType.APPLICATION_JSON)
    public String getWatchLater(){
        String thisuser = securityContext.getUserPrincipal().getName();
        List<MovieDTO> movieDTOList = facade.getWatchLaterList(thisuser);
        return gson.toJson(movieDTOList);
    }

    @POST
    @Path("watchLater/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addWatchLater(@PathParam("id") String id){
        String thisuser = securityContext.getUserPrincipal().getName();
        facade.addWatchLater(thisuser,id);
        return null;
    }

    @DELETE
    @Path("watchLater/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteWatchLater(@PathParam("id") String id){
        String thisuser = securityContext.getUserPrincipal().getName();
        facade.deleteWatchLater(thisuser,id);
        return null;
    }

}