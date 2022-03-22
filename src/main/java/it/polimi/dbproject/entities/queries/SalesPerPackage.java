package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "SalesPerPackage.retrieveByPackageId",
        query = "SELECT n FROM SalesPerPackage n " +
                "WHERE n.availableServicePack_id = :package_id"
)


@Table(name = "sales_per_package", schema = "dbproject2022")
public class SalesPerPackage implements Serializable {
    @Id
    @Column(name = "availableServicePack_id", nullable = false)
    private int availableServicePack_id;

    @OneToOne
    @JoinColumn(name = "availableServicePack_id")
    private AvailableServicePackEntity availableServicePack;

    @Column(name = "total_sales_with_optional", nullable = false)
    private float totalSalesWithOptional;

    @Column(name = "total_sales_no_optional", nullable = false)
    private float totalSalesNoOptional;

    public SalesPerPackage(int availableServicePack_id, AvailableServicePackEntity availableServicePack) {
        this.availableServicePack_id = availableServicePack_id;
        this.availableServicePack = availableServicePack;
        this.totalSalesWithOptional = 0;
        this.totalSalesNoOptional = 0;
    }

    public SalesPerPackage() {

    }

    public float getTotalSalesWithOptional(){ return this.totalSalesWithOptional; }

    public float getTotalSalesNoOptional(){ return this.totalSalesNoOptional; }
}
