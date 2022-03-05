package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "error", schema = "dbproject2022")
public class ErrorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id", unique = true, nullable = false)
    private int errorId;

    @Column(name = "tot_number", unique = true, nullable = false)
    private int totNumber;

    @Column(name = "timestamp", nullable = false)
    private Timestamp timestamp;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_error")
    private UserEntity userError;

    public ErrorEntity() {
    }

    public ErrorEntity(Timestamp timestamp,
                       UserEntity owner) {
        this.userError = owner;
        this.totNumber = 0;
    }

    // GETTER AND SETTER //

    public int getError_id() {
        return errorId;
    }

    public void setError_id (int errorId) {
        this.errorId = errorId;
    }

    public int getTot_number() {
        return totNumber;
    }

    public void setTot_number(int totNumber) {
        this.totNumber = totNumber;
    }

    public Timestamp getTs() {
        return timestamp;
    }

    public void setTs(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public UserEntity getOwner() {
        return userError;
    }

    public void setOwner(UserEntity userError) {
        this.userError = userError;
    }

    // METHODS //

}


