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
                "WHERE p.purchasesperpackageandperiod_id = :package_id AND " +
                "p.period_id =: period_id "
)

@Table(name = "purchasesperpackageandperiod", schema = "dbproject2022")
public class PurchasesPerPackageAndPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchasesperpackageandperiod_id", nullable = false)
    private int purchasesperpackageandperiod_id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePackEntity package_id;

    @Column(name = "period_id", nullable = false)
    private int period_id;

    @OneToOne
    @JoinColumn(name = "period_id")
    private PeriodEntity period;

    @Column(name = "purchases", nullable = false)
    private int purchases;

    public PurchasesPerPackageAndPeriod(int purchasesperpackageandperiod, ServicePackEntity package_id, int period_id, PeriodEntity period) {
        this.purchasesperpackageandperiod_id = purchasesperpackageandperiod;
        this.package_id = package_id;
        this.period_id = period_id;
        this.period = period;
        this.purchases = 0;
    }


    public PurchasesPerPackageAndPeriod() {

    }
}
