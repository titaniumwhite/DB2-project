package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@Table(name = "order", schema = "dbproject2022")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @Column(name = "timestamp_creation", nullable=false)
    private Timestamp timestampCreation;

    public void setOwner(UserEntity user_order) {
        this.user_order = user_order;
    }

    public ServicePackEntity getChosenServicePackage() {
        return service_package_order;
    }

    public void setChosenServicePackage(ServicePackEntity service_package_order) {
        this.service_package_order = service_package_order;
    }

    @Column(name = "total_cost", nullable=false)
    private int totalCost;

    @Column(name = "isPlaceable", nullable=false)
    private boolean isPlaceable;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "user_order")
    private UserEntity user_order;

    @OneToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "service_package_order")
    private ServicePackEntity service_package_order;


    public OrderEntity(){
    }

    public OrderEntity(Timestamp timestampCreation,
                       UserEntity user_order,
                       ServicePackEntity service_package_order) {
        this.timestampCreation = timestampCreation;
        this.user_order = user_order;
        this.service_package_order = service_package_order;
    }

    public Long getOrder_id() {
        return orderId;
    }

    public void setOrder_id(Long orderId) {
        this.orderId = orderId;
    }

    public Timestamp getCreation_ts() {
        return timestampCreation;
    }

    public void setCreation_ts(Timestamp timestampCreation) {
        this.timestampCreation = timestampCreation;
    }

    public int getTotal_cost() {
        return totalCost;
    }

    public void setTotal_cost(int totalCost) {
        this.totalCost = totalCost;
    }

    public boolean isPlaceable() {
        return isPlaceable;
    }

    public void setPlaceable(boolean placeable) {
        isPlaceable = placeable;
    }

    public UserEntity getOwner() {
        return user_order;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "order_id=" + orderId +
                '}';
    }
}
