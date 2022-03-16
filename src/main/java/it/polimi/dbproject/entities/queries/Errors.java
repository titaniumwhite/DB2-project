package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.ErrorEntity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "errors", schema = "dbproject2022")
public class Errors implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "errors_id", nullable = false)
    private int errors_id;

    @OneToOne
    @JoinColumn(name = "error_id")
    private ErrorEntity alert;

    public Errors(int errors_id, ErrorEntity alert) {
        this.errors_id = errors_id;
        this.alert = alert;
    }
}