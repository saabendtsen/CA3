package datamappers;

import dtos.ImdbResponseDTO;
import dtos.MovieDTO;

import java.util.ArrayList;
import java.util.List;

public class MovieMapper {


    public List<MovieDTO> getMovie(ImdbResponseDTO imdbResponseDTO) {
        List<MovieDTO> list = new ArrayList<>();
        for (ImdbResponseDTO.Result result : imdbResponseDTO.results) {
            List<String> movieDTOList = new ArrayList<>();
            for (ImdbResponseDTO.Location location : result.locations) {
                movieDTOList.add(location.display_name);
            }
            list.add(new MovieDTO(result.external_ids.imdb.id,result.name, movieDTOList));
        }
        return list;
    }
}
