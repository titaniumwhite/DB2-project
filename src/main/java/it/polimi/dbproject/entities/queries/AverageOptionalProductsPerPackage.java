package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "AverageOptionalProductsPerPackage.findByPackageId",
        query = "SELECT n FROM AverageOptionalProductsPerPackage n " +
                "WHERE n.package_id = :package_id"
)

@Table(name = "average_optional_products_perpackage", schema = "dbproject2022")
public class AverageOptionalProductsPerPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "average_optional_products_perpackage_id", nullable = false)
    private int package_id;

    @OneToOne
    @JoinColumn(name = "service_pack_id")
    private ServicePackEntity servicePack;

    @Column(name = "average", nullable = false)
    private float average;


    public AverageOptionalProductsPerPackage() {
    }

    public AverageOptionalProductsPerPackage(int package_id, ServicePackEntity servicePack) {
        this.package_id = package_id;
        this.servicePack = servicePack;
        this.average = 0;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public ServicePackEntity getServicePackage() {
        return servicePack;
    }

    public void setServicePackage(ServicePackEntity servicePack) {
        this.servicePack = servicePack;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}