package it.polimi.dbproject.entities.queries;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "avgoptionalservicesoldperpackage", schema = "dbproject2022")
public class AvgOptionalServiceSoldPerPackage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "avgoptionalservicesoldperpackage_id", nullable = false)
    private int package_id;

    @OneToOne
    @JoinColumn(name = "package_id")
    private AvgOptionalServiceSoldPerPackage servicePackage;

    @Column(name = "average", nullable = false)
    private float average;


    public AvgOptionalServiceSoldPerPackage() {
    }

    public AvgOptionalServiceSoldPerPackage(int package_id, AvgOptionalServiceSoldPerPackage servicePackage, float average) {
        this.package_id = package_id;
        this.servicePackage = servicePackage;
        this.average = average;
    }

    public int getPackage_id() {
        return package_id;
    }

    public void setPackage_id(int package_id) {
        this.package_id = package_id;
    }

    public AvgOptionalServiceSoldPerPackage getServicePackage() {
        return servicePackage;
    }

    public void setServicePackage(AvgOptionalServiceSoldPerPackage servicePackage) {
        this.servicePackage = servicePackage;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}