package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "sales_per_package", schema = "dbproject2022")
public class SalesPerPackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sales_per_package_id", nullable = false)
    private int salesperpackage_id;


    @OneToOne
    @JoinColumn(name = "package_id")
    private AvailableServicePackEntity availableServicePack;

    @Column(name = "total_sales_with_optional", nullable = false)
    private float totalSalesWithOptional;

    @Column(name = "total_sales_no_optional", nullable = false)
    private float totalSalesNoOptional;

    public SalesPerPackage(int salesperpackage_id, AvailableServicePackEntity availableServicePack, float totalSalesWithOptional, float totalSalesNoOptional) {
        this.salesperpackage_id = salesperpackage_id;
        this.availableServicePack = availableServicePack;
        this.totalSalesWithOptional = totalSalesWithOptional;
        this.totalSalesNoOptional = totalSalesNoOptional;
    }

    public SalesPerPackage() {

    }
}
