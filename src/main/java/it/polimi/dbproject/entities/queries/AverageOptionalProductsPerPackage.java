package it.polimi.dbproject.entities.queries;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "average_optional_products_perpackage", schema = "dbproject2022")
public class AverageOptionalProductsPerPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "average_optional_products_perpackage_id", nullable = false)
    private int package_id;

    @OneToOne
    @JoinColumn(name = "service_pack_id")
    private ServicePackEntity servicePackage;

    @Column(name = "average", nullable = false)
    private float average;


    public AverageOptionalProductsPerPackage() {
    }

    public AverageOptionalProductsPerPackage(int package_id, ServicePackEntity servicePackage, float average) {
        this.package_id = package_id;
        this.servicePackage = servicePackage;
        this.average = 0;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public ServicePackEntity getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(ServicePackEntity servicePackage) {
        this.servicePackage = servicePackage;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}