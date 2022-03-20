package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.UserEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "InolventUsers.retrieveInsolventUser",
        query = "SELECT iu FROM InsolventUsers iu "
)

@Table(name = "insolvent_users", schema = "dbproject2022")
public class InsolventUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "insolventusers_id", nullable = false)
    private int insolventusers_id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public InsolventUsers(int insolventusers_id, UserEntity user) {
        this.insolventusers_id = insolventusers_id;
        this.user = user;
    }

    public InsolventUsers() {

    }
}
