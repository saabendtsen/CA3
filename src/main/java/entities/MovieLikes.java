package entities;

import javax.persistence.*;

@Table(name = "movie_likes")
@Entity
public class MovieLikes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "imdbId", nullable = false, unique = true)
    private String imdbId;
    @Column(name = "quantity", nullable = false)
    private Long quantity;

    public MovieLikes()  {}

    public MovieLikes(int id) {
        this.id = id;
    }

    public MovieLikes(int id, String imdbId, Long quantity) {
        this.id = id;
        this.imdbId = imdbId;
        this.quantity = quantity;
    }

    public String getImdbId() {
        return imdbId;
    }

    public void setImdbId(String imdbId) {
        this.imdbId = imdbId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}