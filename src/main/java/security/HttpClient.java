package security;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

public class HttpClient {

    // one instance, reuse
    private final CloseableHttpClient httpClient = HttpClients.createDefault();
    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();


    String ApiID = "https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=id";

    public void close() throws IOException {
        httpClient.close();
    }

    public String sendGet(String search, Boolean isID) throws Exception {
        String res = gson.toJson("NotFound : Movie not Found!");
            HttpGet request;

        if(isID){
            request = new HttpGet("https://movie-database-imdb-alternative.p.rapidapi.com/?r=json&i=" + search );


        } else {

            request = new HttpGet("https://utelly-tv-shows-and-movies-availability-v1.p.rapidapi.com/lookup?term=%22" + search + "&country=us");
        }
        // request headers
        request.addHeader("x-rapidapi-key", "7bdff84145msh4480520f958f4ebp1f543fjsnda49759e8066");
        try (CloseableHttpResponse response = httpClient.execute(request)) {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                res =  EntityUtils.toString(entity);
            }
        }
            return res;
    }

}