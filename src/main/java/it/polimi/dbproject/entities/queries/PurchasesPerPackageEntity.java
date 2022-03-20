package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity

@NamedQuery(
        name = "PurchasesPerPackageEntity.retrievePurchasesByPackId",
        query = "SELECT p FROM PurchasesPerPackageEntity p " +
                "WHERE p.purchasesperpackage_id = :package_id"
)

@Table(name = "purchases_per_package", schema = "dbproject2022")
public class PurchasesPerPackageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "purchases_per_package_id", nullable = false)
    private int purchasesperpackage_id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePackEntity servicePack;


    @Column(name = "total_purchases", nullable = false)
    private int totalPurchases;


    public PurchasesPerPackageEntity() {
    }

    public PurchasesPerPackageEntity(int purchasesperpackage_id, ServicePackEntity servicePack) {
        this.purchasesperpackage_id = purchasesperpackage_id;
        this.servicePack = servicePack;
        totalPurchases=0;
    }


}
