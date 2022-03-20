package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;


@Entity

@NamedQuery(
        name = "PurchasesPerPackageEntity.retrievePurchasesByPackId",
        query = "SELECT p FROM PurchasesPerPackageEntity p " +
                "WHERE p.servicePack_id = :package_id"
)

@Table(name = "totalOrderPackage", schema = "dbproject2022")
public class PurchasesPerPackageEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicePack_id", nullable = false)
    private int servicePack_id;

    @OneToOne
    @JoinColumn(name = "servicePack_id")
    private ServicePackEntity servicePack;


    @Column(name = "totalOrder", nullable = false)
    private int totalOrder;


    public PurchasesPerPackageEntity() {
    }

    public PurchasesPerPackageEntity(int servicePack_id, ServicePackEntity servicePack) {
        this.servicePack_id = servicePack_id;
        this.servicePack = servicePack;
        totalOrder=0;
    }


}
