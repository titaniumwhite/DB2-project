package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "SalesPerPackage.retrieveByPackageId",
        query = "SELECT n FROM SalesPerPackage n " +
                "WHERE n.salesperpackage_id = :package_id"
)


@Table(name = "sales_per_package", schema = "dbproject2022")
public class SalesPerPackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_per_package_id", nullable = false)
    private int salesperpackage_id;


    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePackEntity servicePack;

    @Column(name = "total_sales_with_optional", nullable = false)
    private float totalSalesWithOptional;

    @Column(name = "total_sales_no_optional", nullable = false)
    private float totalSalesNoOptional;

    public SalesPerPackage(int salesperpackage_id, AvailableServicePackEntity availableServicePack, float totalSalesWithOptional, float totalSalesNoOptional) {
        this.salesperpackage_id = salesperpackage_id;
        this.servicePack = servicePack;
        this.totalSalesWithOptional = 0;
        this.totalSalesNoOptional = 0;
    }

    public SalesPerPackage() {

    }
}
