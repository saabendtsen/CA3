package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.UserFacade;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
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
    @Path("movietitle/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCatFacts(@PathParam("title")String title) throws IOException {
        HttpClient client = HttpClients.custom().build();
        HttpUriRequest request = RequestBuilder.get()
                .setUri("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+ title + "&country=us")
                .setHeader(HttpHeaders.CONTENT_TYPE, "application/json")
                .setHeader("x-rapidapi-key", "7bdff84145msh4480520f958f4ebp1f543fjsnda49759e8066")
                .build();
        client.execute(request);

//        CloseableHttpClient httpClient = new CloseableHttpClient();
////        String catFact = HttpUtils.fetchData("https://catfact.ninja/fact");
//        HttpGet httpGet = new HttpGet("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term="+ title + "&country=us");
//        httpGet.addHeader("x-rapidapi-key", "7bdff84145msh4480520f958f4ebp1f543fjsnda49759e8066");
//        HttpResponse response = httpclient.execute(httpGet);
        return gson.toJson(request);
    }
}