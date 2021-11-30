package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "watch_list")
@Entity
public class WatchList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private int id;

    private String imdbid;

    @ManyToOne
    private User user;

    public WatchList() {
    }

    public WatchList(String imdbid) {
        this.imdbid = imdbid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImdbid() {
        return imdbid;
    }

    public void setImdbid(String imdbid) {
        this.imdbid = imdbid;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}