package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.OptionalServiceEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "BestOptionalProduct.retrieveBest",
        query = "SELECT bop FROM BestOptionalProduct bop "
)

@Table(name = "best_optional_product", schema = "dbproject2022")
public class BestOptionalProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "best_optional_product_id", nullable = false)
    private int bestOptionalProduct_id;

    @OneToOne
    @JoinColumn(name = "optional_service_id")
    private OptionalServiceEntity optionalService;

    @Column(name = "number_of_sales", nullable = false)
    private int numberOfSales;

    public BestOptionalProduct(int bestOptionalProduct_id, OptionalServiceEntity optionalService, int numberOfSales) {
        this.bestOptionalProduct_id = bestOptionalProduct_id;
        this.optionalService = optionalService;
        this.numberOfSales = numberOfSales;
    }

    public BestOptionalProduct() {

    }
}