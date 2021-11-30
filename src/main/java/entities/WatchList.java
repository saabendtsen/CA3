package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "watch_list")
@Entity
public class WatchList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @NotNull
    @Column(name = "watchlater_imdb_id")
    private String watchLaterImdbId;

    @ManyToMany(mappedBy = "watchList")
    private List<User> userList;

    public WatchList(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}