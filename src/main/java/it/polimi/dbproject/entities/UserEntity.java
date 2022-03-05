package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Table(name = "user", schema = "dbproject2022")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable=false)
    private String user_id;

    @Column(name = "username", unique=true, nullable=false)
    private String username;

    @Column(name = "first_name", nullable=false)
    private String first_name;

    @Column(name = "last_name", nullable=false)
    private String last_name;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "failedPayments")
    private int failedPayments;

    @Column(name = "isInsolvent")
    private Boolean isInsolvent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="ordersOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="servicePackOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicePackEntity> servicePackages;

    public UserEntity() {
    }

    public UserEntity(String username,
                      String first_name,
                      String last_name,
                      String email,
                      String password) {

        this.username = username;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;

        failedPayments=0;
        isInsolvent=false;

    }

    // GETTER AND SETTER //

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getFailedPayments() {
        return failedPayments;
    }

    public void setFailedPayments(int failedPayments) {
        this.failedPayments = failedPayments;
    }

    public Boolean getInsolvent() {
        return isInsolvent;
    }

    public void setInsolvent(Boolean insolvent) {
        isInsolvent = insolvent;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    // METHODS //

    public void incrementFailedPayments() {
        failedPayments++;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "username='" + username + '\'' +
                '}';
    }
}