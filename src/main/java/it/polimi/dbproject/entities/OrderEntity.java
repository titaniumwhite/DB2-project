package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.sql.Timestamp;

@Entity
@NamedQuery(
        name = "Order.retrieveAllUserOrderThroughID",
        query = " SELECT o FROM OrderEntity o" +
                " WHERE o.owner = :user and o.isPlaceable = true "
)
@NamedQuery(
        name = "Order.retrieveThroughID",
        query = " SELECT o FROM OrderEntity o " +
                " WHERE o.orderId = :orderId "
)
@NamedQuery(
        name = "Order.retrieveFailedUserOrder",
        query = " SELECT o FROM OrderEntity o " +
                " WHERE o.owner =: user AND o.isPlaceable = false"
)
@NamedQuery(
        name = "Order.retrievePendingOrder",
        query = " SELECT distinct o FROM OrderEntity o "+
                " JOIN o.servicePackageOrder s " +
                " WHERE o.owner = :user AND o.isPlaceable = false"
)

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
        this.owner = owner;
    }


    @Column(name = "total_cost", nullable=false)
    private int totalCost;

    @Column(name = "isPlaceable", nullable=false)
    private boolean isPlaceable;

    @ManyToOne (targetEntity = UserEntity.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "owner")
    private UserEntity owner;

    @OneToOne (fetch = FetchType.LAZY, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}, optional = false)
    @JoinColumn(name = "service_package_order")
    private ServicePackEntity servicePackageOrder;


    public OrderEntity(){
    }

    public OrderEntity(Timestamp timestampCreation,
                       int totalCost,
                       boolean isPlaceable,
                       UserEntity owner,
                       ServicePackEntity servicePackageOrder) {
        this.timestampCreation = timestampCreation;
        this.totalCost = totalCost;
        this.isPlaceable = isPlaceable;
        this.owner = owner;
        this.servicePackageOrder = servicePackageOrder;
    }

    public ServicePackEntity getChosenServicePackage() {
        return this.servicePackageOrder;
    }

    public void setChosenServicePackage(ServicePackEntity service_package_order) {
        this.servicePackageOrder = servicePackageOrder;
    }


    public Long getOrder_id() {
        return this.orderId;
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
        return owner;
    }

    @Override
    public String toString() {
        return "OrderEntity{" +
                "order_id=" + orderId +
                '}';
    }
}
