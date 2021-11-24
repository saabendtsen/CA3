package dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AddInfoDTO {

    public String title;
    public String year;
    public String rated;
    public String released;
    public String runtime;
    public String genre;
    public String director;
    public String writer;
    public String actors;
    public String plot;
    public String language;
    public String country;
    public String awards;
    public String poster;
    public List<Rating> ratings;
    public String metascore;
    public String imdbRating;
    public String imdbVotes;
    public String imdbID;
    public String type;
    public String dVD;
    public String boxOffice;
    public String production;
    public String website;
    public String response;

    public AddInfoDTO(String title, String year, String rated, String released, String runtime, String genre, String director, String writer, String actors, String plot, String language, String country, String awards, String poster, List<Rating> ratings, String metascore, String imdbRating, String imdbVotes, String imdbID, String type, String dVD, String boxOffice, String production, String website, String response) {
        this.title = title;
        this.year = year;
        this.rated = rated;
        this.released = released;
        this.runtime = runtime;
        this.genre = genre;
        this.director = director;
        this.writer = writer;
        this.actors = actors;
        this.plot = plot;
        this.language = language;
        this.country = country;
        this.awards = awards;
        this.poster = poster;
        this.ratings = ratings;
        this.metascore = metascore;
        this.imdbRating = imdbRating;
        this.imdbVotes = imdbVotes;
        this.imdbID = imdbID;
        this.type = type;
        this.dVD = dVD;
        this.boxOffice = boxOffice;
        this.production = production;
        this.website = website;
        this.response = response;
    }


    public String getTitle() {
        return title;
    }

    public String getYear() {
        return year;
    }

    public String getRated() {
        return rated;
    }

    public String getReleased() {
        return released;
    }

    public String getRuntime() {
        return runtime;
    }

    public String getGenre() {
        return genre;
    }

    public String getDirector() {
        return director;
    }

    public String getWriter() {
        return writer;
    }

    public String getActors() {
        return actors;
    }

    public String getPlot() {
        return plot;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getAwards() {
        return awards;
    }

    public String getPoster() {
        return poster;
    }

    public List<Rating> getRatings() {
        return ratings;
    }

    public String getMetascore() {
        return metascore;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public String getImdbVotes() {
        return imdbVotes;
    }

    public String getImdbID() {
        return imdbID;
    }

    public String getType() {
        return type;
    }

    public String getdVD() {
        return dVD;
    }

    public String getBoxOffice() {
        return boxOffice;
    }

    public String getProduction() {
        return production;
    }

    public String getWebsite() {
        return website;
    }

    public String getResponse() {
        return response;
    }

    public class Rating{
        public String source;
        public String value;

        public Rating(String source, String value) {
            this.source = source;
            this.value = value;
        }

        public String getSource() {
            return source;
        }

        public String getValue() {
            return value;
        }
    }

}
