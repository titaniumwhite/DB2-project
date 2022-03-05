package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

@Table(name = "error", schema = "dbproject2022")
public class ErrorEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "error_id", unique = true, nullable = false)
    private String error_id;

    @Column(name = "tot_number", unique = true, nullable = false)
    private int tot_number;

    @Column(name = "ts", nullable = false)
    private Timestamp ts;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "errorsOwner")
    private UserEntity owner;

    public ErrorEntity() {
    }

    public ErrorEntity(Timestamp ts,
                       UserEntity owner) {
        this.owner = owner;
        this.tot_number = 0;
    }

    // GETTER AND SETTER //

    public String getError_id() {
        return error_id;
    }

    public void setError_id(String error_id) {
        this.error_id = error_id;
    }

    public int getTot_number() {
        return tot_number;
    }

    public void setTot_number(int tot_number) {
        this.tot_number = tot_number;
    }

    public Timestamp getTs() {
        return ts;
    }

    public void setTs(Timestamp ts) {
        this.ts = ts;
    }

    public UserEntity getOwner() {
        return owner;
    }

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    // METHODS //

}


