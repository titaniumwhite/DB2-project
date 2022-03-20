package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "InolventUsers.retrieveInsolventUser",
        query = "SELECT iu FROM InsolventUsers iu "
)

@Table(name = "insolventUsers", schema = "dbproject2022")
public class InsolventUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private int user_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public InsolventUsers(UserEntity user) {
        this.user = user;
    }

    public InsolventUsers() {

    }
}
