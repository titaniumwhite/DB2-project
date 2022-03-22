package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.PeriodEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "PurchasesPerPackageAndPeriod.retrieveByPackageAndPeriod",
        query = "SELECT p FROM PurchasesPerPackageAndPeriod p " +
                "WHERE p.availableServicePack_id = :package_id AND " +
                "p.period_id =: period_id "
)

@Table(name = "purchases_per_package_and_period", schema = "dbproject2022")
public class PurchasesPerPackageAndPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "availableServicePack_id", nullable = false)
    private int availableServicePack_id;

    @OneToOne
    @JoinColumn(name = "availableServicePack_id")
    private AvailableServicePackEntity availableServicePack;

    @Column(name = "period_id", nullable = false)
    private int period_id;

    @OneToOne
    @JoinColumn(name = "period_id")
    private PeriodEntity period;

    @Column(name = "totalNumber", nullable = false)
    private int totalNumber;

    public PurchasesPerPackageAndPeriod(int availableServicePack_id, AvailableServicePackEntity availableServicePack, int period_id, PeriodEntity period) {
        this.availableServicePack_id = availableServicePack_id;
        this.availableServicePack = availableServicePack;
        this.period_id = period_id;
        this.period = period;
        this.totalNumber = 0;
    }


    public PurchasesPerPackageAndPeriod() {

    }

    public int getTotalNumber(){ return this.totalNumber; }
}
