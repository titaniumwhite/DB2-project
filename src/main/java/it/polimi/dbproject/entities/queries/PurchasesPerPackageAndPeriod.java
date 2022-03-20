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
                "WHERE p.servicePack_id = :package_id AND " +
                "p.period_id =: period_id "
)

@Table(name = "totalOrderPackageWithPeriod", schema = "dbproject2022")
public class PurchasesPerPackageAndPeriod implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicePack_id", nullable = false)
    private int servicePack_id;

    @OneToOne
    @JoinColumn(name = "servicePack_id")
    private ServicePackEntity servicePack;

    @Column(name = "period_id", nullable = false)
    private int period_id;

    @OneToOne
    @JoinColumn(name = "period_id")
    private PeriodEntity period;

    @Column(name = "totalNumber", nullable = false)
    private int totalNumber;

    public PurchasesPerPackageAndPeriod(int servicePack_id, ServicePackEntity servicePack, int period_id, PeriodEntity period) {
        this.servicePack_id = servicePack_id;
        this.servicePack = servicePack;
        this.period_id = period_id;
        this.period = period;
        this.totalNumber = 0;
    }


    public PurchasesPerPackageAndPeriod() {

    }
}
