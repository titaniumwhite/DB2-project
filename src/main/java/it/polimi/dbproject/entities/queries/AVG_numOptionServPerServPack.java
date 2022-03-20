package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "AVG_numOptionServPerServPack.findByPackageId",
        query = "SELECT n FROM AVG_numOptionServPerServPack n " +
                "WHERE n.servicePack_id = :package_id"
)

@Table(name = "avg_numoptionservperservpack", schema = "dbproject2022")
public class AVG_numOptionServPerServPack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicePack_id", nullable = false)
    private int servicePack_id;

    @OneToOne
    @JoinColumn(name = "servicePack_id")
    private ServicePackEntity servicePack;

    @Column(name = "avgNum", nullable = false)
    private float average;


    public AVG_numOptionServPerServPack() {
    }

    public AVG_numOptionServPerServPack(int servicePack_id, ServicePackEntity servicePack) {
        this.servicePack_id = servicePack_id;
        this.servicePack = servicePack;
        this.average = 0;
    }

    public int getPackage_id() {
        return servicePack_id;
    }

    public void setPackage_id(int servicePack_id) {
        this.servicePack_id = servicePack_id;
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