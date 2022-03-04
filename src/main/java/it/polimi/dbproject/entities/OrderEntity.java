package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "order", schema = "dbproject2022")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "creation_date", nullable=false)
    private Timestamp creation_date;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "owner")
    private UserEntity owner;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "chosenServicePack")
    private ServicePackEntity chosenServicePackage;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "chosenPeriod")
    private PeriodEntity chosenPeriod;

    public OrderEntity(){
    }

    public OrderEntity(Timestamp creation_date,
                       UserEntity owner,
                       ServicePackEntity chosenServicePackage,
                       PeriodEntity chosenPeriod) {
        this.creation_date = creation_date;
        this.owner = owner;
        this.chosenServicePackage = chosenServicePackage;
        this.chosenPeriod = chosenPeriod;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Timestamp creation_date) {
        this.creation_date = creation_date;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "order_id=" + id +
                '}';
    }
}
