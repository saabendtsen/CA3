package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.ImdbMovieDTO;
import facades.UserFacade;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import security.HttpClient;
import utils.EMF_Creator;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.io.Closeable;
import java.io.IOException;

@Path("/movie")
public class MovieResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();
    @Context
    private UriInfo context;
    private UserFacade facade = UserFacade.getUserFacade(EMF);
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    @Context
    SecurityContext securityContext;

    @GET
    @Path("title/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getTitle(@PathParam("title")String title) throws Exception {
        security.HttpClient obj = new HttpClient();
        String response = "";
        try {
            response = obj.sendGet(title);
        } finally {
            obj.close();
        }
        ImdbMovieDTO dto = gson.fromJson(response,ImdbMovieDTO.class);
        return gson.toJson(dto.toString());
    }
}