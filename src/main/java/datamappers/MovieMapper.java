package datamappers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dtos.AddInfoDTO;
import dtos.ImdbResponseDTO;
import dtos.MovieDTO;
import rest.MovieResource;
import security.HttpClient;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {


    public List<MovieDTO> getMovie(ImdbResponseDTO imdbResponseDTO) throws Exception {
        List<MovieDTO> list = new ArrayList<>();
        for (ImdbResponseDTO.Result result : imdbResponseDTO.results) {
            List<String> movieDTOList = new ArrayList<>();
            for (ImdbResponseDTO.Location location : result.locations) {
                movieDTOList.add(location.display_name);
            }
            list.add(new MovieDTO(result.external_ids.imdb.id,result.name, movieDTOList));
        }
        addInfo(list);

        return list;
    }
    
    public MovieDTO getMovieById(String id){

        // TODO: 11/30/2021 needs to be implemented 
        return null;
    }

    public List<MovieDTO> addInfo (List<MovieDTO> list) throws Exception {
        for (MovieDTO dto : list){
            AddInfoDTO addInfoDTO = getAddInfo(dto);
            System.out.println(addInfoDTO.toString());
            dto.setYear(addInfoDTO.getYear());
            dto.setGenre(addInfoDTO.getGenre());
            dto.setRated(addInfoDTO.getRated());
            dto.setRuntime(addInfoDTO.getRuntime());
            dto.setImdbRating(addInfoDTO.getImdbRating());
            dto.setPoster(addInfoDTO.getPoster());
            dto.setBoxOffices(addInfoDTO.getBoxOffice());
        }
        return list;
    }



    public AddInfoDTO getAddInfo(MovieDTO dto) throws Exception {
        final Gson gson = new GsonBuilder().setPrettyPrinting().create();
        security.HttpClient obj = new HttpClient();
        String response = "";
        try {
            response = obj.sendGet(dto.getId(),true);
            System.out.println(response);
        } finally {
            obj.close();
        }

        return gson.fromJson(response, AddInfoDTO.class);
    }
    
    
}
