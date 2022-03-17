package it.polimi.dbproject.entities.queries;

import it.polimi.dbproject.entities.OrderEntity;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "suspendedorders", schema = "dbproject2022")
public class SuspendedOrders implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suspendedorders_id", nullable = false)
    private int suspendedorders_id;

    @OneToOne
    @JoinColumn(name = "order_id")
    private OrderEntity order;

    public SuspendedOrders() {
    }

    public SuspendedOrders(OrderEntity order) {
        this.order = order;
    }
}
