package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.AvailableServicePackEntity;
import it.polimi.dbproject.entities.ServicePackEntity;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@NamedQuery(
        name = "AVG_numOptionServPerServPack.findByPackageId",
        query = "SELECT n FROM AVG_numOptionServPerServPack n " +
                "WHERE n.availableServicePack_id = :package_id"
)

@Table(name = "avg_numoptionservperservpack", schema = "dbproject2022")
public class AVG_numOptionServPerServPack implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "availableServicePack_id", nullable = false)
    private int availableServicePack_id;

    @OneToOne
    @JoinColumn(name = "availableServicePack_id")
    private AvailableServicePackEntity availableServicePack;

    @Column(name = "avgNum", nullable = false)
    private float average;


    public AVG_numOptionServPerServPack() {
    }

    public AVG_numOptionServPerServPack(int availableServicePack_id, AvailableServicePackEntity availableServicePack) {
        this.availableServicePack_id = availableServicePack_id;
        this.availableServicePack = availableServicePack;
        this.average = 0;
    }


    public AvailableServicePackEntity getServicePackage() {
        return availableServicePack;
    }

    public void setServicePackage(AvailableServicePackEntity availableServicePack) {
        this.availableServicePack = availableServicePack;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
}