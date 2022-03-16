package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.OptionalServiceEntity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "bestoptionalproduct", schema = "dbproject2022")
public class BestOptionalProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "bestoptionalproduct_id", nullable = false)
    private int bestOptionalProduct_id;

    @OneToOne
    @JoinColumn(name = "optionalService_id")
    private OptionalServiceEntity optionalService;

    @Column(name = "numberofsales", nullable = false)
    private int numberOfSales;

    public BestOptionalProduct(int bestOptionalProduct_id, OptionalServiceEntity optionalService, int numberOfSales) {
        this.bestOptionalProduct_id = bestOptionalProduct_id;
        this.optionalService = optionalService;
        this.numberOfSales = numberOfSales;
    }
}