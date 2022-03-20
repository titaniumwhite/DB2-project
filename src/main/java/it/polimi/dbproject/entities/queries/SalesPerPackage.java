package it.polimi.dbproject.entities.queries;


import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "SalesPerPackage.retrieveByPackageId",
        query = "SELECT n FROM SalesPerPackage n " +
                "WHERE n.salesperpackage_id = :package_id"
)


@Table(name = "salesperpackage", schema = "dbproject2022")
public class SalesPerPackage implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "salesperpackage_id", nullable = false)
    private int salesperpackage_id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private ServicePackEntity servicePack;

    @Column(name = "totalSalesWithOptional", nullable = false)
    private float totalSalesWithOptional;

    @Column(name = "totalSalesNoOptional", nullable = false)
    private float totalSalesNoOptional;

    public SalesPerPackage(int salesperpackage_id, ServicePackEntity servicePack) {
        this.salesperpackage_id = salesperpackage_id;
        this.servicePack = servicePack;
        this.totalSalesWithOptional = 0;
        this.totalSalesNoOptional = 0;
    }

    public SalesPerPackage() {

    }
}
