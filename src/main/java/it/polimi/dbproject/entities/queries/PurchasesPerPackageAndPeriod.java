package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.PeriodEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity


@Table(name = "purchasesperpackageandperiod", schema = "dbproject2022")
public class PurchasesPerPackageAndPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchasesperpackageandperiod_id", nullable = false)
    private int purchasesperpackageandperiod;

    @OneToOne
    @JoinColumn(name = "package_id")
    private AvailableServicePackEntity availableServicePack;

    @Column(name = "period_id", nullable = false)
    private int period_id;

    @OneToOne
    @JoinColumn(name = "period_id")
    private PeriodEntity period;

    @Column(name = "purchases", nullable = false)
    private int purchases;

    public PurchasesPerPackageAndPeriod(int purchasesperpackageandperiod, AvailableServicePackEntity availableServicePack, int period_id, PeriodEntity period, int purchases) {
        this.purchasesperpackageandperiod = purchasesperpackageandperiod;
        this.availableServicePack = availableServicePack;
        this.period_id = period_id;
        this.period = period;
        this.purchases = purchases;
    }


    public PurchasesPerPackageAndPeriod() {

    }
}
