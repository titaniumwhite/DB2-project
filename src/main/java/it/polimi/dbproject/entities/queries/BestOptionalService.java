package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.OptionalServiceEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "BestOptionalService.retrieveBest",
        query = "SELECT bop FROM BestOptionalService bop "
)

@Table(name = "best_optional_service", schema = "dbproject2022")
public class BestOptionalService implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "optional_service_id", nullable = false)
    private int optional_service_id;

    @OneToOne
    @JoinColumn(name = "optional_service_id")
    private OptionalServiceEntity optionalService;

    @Column(name = "sales", nullable = false)
    private int sales;

    public BestOptionalService(int optional_service_id, OptionalServiceEntity optionalService, int sales) {
        this.optional_service_id = optional_service_id;
        this.optionalService = optionalService;
        this.sales = 0;
    }

    public BestOptionalService() {

    }

    public int getOptional_service_id() {
        return optional_service_id;
    }

    public void setOptional_service_id(int optional_service_id) {
        this.optional_service_id = optional_service_id;
    }

    public OptionalServiceEntity getOptionalService() {
        return optionalService;
    }

    public void setOptionalService(OptionalServiceEntity optionalService) {
        this.optionalService = optionalService;
    }

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }
}