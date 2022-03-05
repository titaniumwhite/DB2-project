package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Table(name = "order", schema = "dbproject2022")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long order_id;

    @Column(name = "creation_ts", nullable=false)
    private Timestamp creation_ts;

    public void setOwner(UserEntity owner) {
        this.owner = owner;
    }

    public ServicePackEntity getChosenServicePackage() {
        return chosenServicePackage;
    }

    public void setChosenServicePackage(ServicePackEntity chosenServicePackage) {
        this.chosenServicePackage = chosenServicePackage;
    }

    @Column(name = "total_cost", nullable=false)
    private int total_cost;

    @Column(name = "isPlaceable", nullable=false)
    private boolean isPlaceable;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "ordersOwner")
    private UserEntity owner;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "chosenServicePack")
    private ServicePackEntity chosenServicePackage;


    public OrderEntity(){
    }

    public OrderEntity(Timestamp creation_ts,
                       UserEntity owner,
                       ServicePackEntity chosenServicePackage) {
        this.creation_ts = creation_ts;
        this.owner = owner;
        this.chosenServicePackage = chosenServicePackage;
    }

    public Long getOrder_id() {
        return order_id;
    }

    public void setOrder_id(Long order_id) {
        this.order_id = order_id;
    }

    public Timestamp getCreation_ts() {
        return creation_ts;
    }

    public void setCreation_ts(Timestamp creation_ts) {
        this.creation_ts = creation_ts;
    }

    public int getTotal_cost() {
        return total_cost;
    }

    public void setTotal_cost(int total_cost) {
        this.total_cost = total_cost;
    }

    public boolean isPlaceable() {
        return isPlaceable;
    }

    public void setPlaceable(boolean placeable) {
        isPlaceable = placeable;
    }

    public UserEntity getOwner() {
        return owner;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "order_id=" + order_id +
                '}';
    }
}
