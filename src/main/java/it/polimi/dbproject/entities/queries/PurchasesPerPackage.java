package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.AvailableServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity

@NamedQuery(
        name = "PurchasesPerPackageEntity.retrievePurchasesByPackId",
        query = "SELECT p FROM PurchasesPerPackage p " +
                "WHERE p.availableServicePack_id = :package_id"
)

@Table(name = "purchases_per_package", schema = "dbproject2022")
public class PurchasesPerPackage implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "availableServicePack_id", nullable = false)
    private int availableServicePack_id;

    @OneToOne
    @JoinColumn(name = "availableServicePack_id")
    private AvailableServicePackEntity availableServicePack;


    @Column(name = "totalOrder", nullable = false)
    private int totalOrder;


    public PurchasesPerPackage() {
    }

    public PurchasesPerPackage(int availableServicePack_id, AvailableServicePackEntity availableServicePack) {
        this.availableServicePack_id = availableServicePack_id;
        this.availableServicePack = availableServicePack;
        totalOrder=0;
    }


}
