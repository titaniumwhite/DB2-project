package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.AvailableServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity

@Table(name = "purchasesperpackage", schema = "dbproject2022")
public class PurchasesPerPackageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "purchasesperpackage_id", nullable = false)
    private int purchasesperpackage_id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private AvailableServicePackEntity availableServicePack;


    @Column(name = "totalPurchases", nullable = false)
    private int totalPurchases;


    public PurchasesPerPackageEntity() {
    }

    public PurchasesPerPackageEntity(int purchasesperpackage_id, AvailableServicePackEntity availableServicePack) {
        this.purchasesperpackage_id = purchasesperpackage_id;
        this.availableServicePack = availableServicePack;
        totalPurchases=0;
    }


}
