package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "watch_list")
@Entity
public class WatchList {
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "imdbId", nullable = false)
    private int id;

    @ManyToOne
    private User user;

    public WatchList() {
    }

    public WatchList(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}