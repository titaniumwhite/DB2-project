package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user", schema = "dbproject2022")

@NamedQuery(name = "User.checkUser", query = "SELECT u FROM UserEntity u WHERE u.username = :usn and u.password = :psw")

public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable=false)
    private String userId;

    @Column(name = "username", unique=true, nullable=false)
    private String username;

    @Column(name = "name", nullable=false)
    private String name;

    @Column(name = "surname", nullable=false)
    private String surname;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "totFailedAttempts")
    private int totFailedAttempts;

    @Column(name = "isInsolvent")
    private Boolean isInsolvent;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="ordersOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="servicePackOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServicePackEntity> servicePackages;

    @OneToMany(fetch = FetchType.LAZY, mappedBy="errorsOwner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ErrorEntity> errors;

    public UserEntity() {}

    public UserEntity(String username,
                      String name,
                      String surname,
                      String email,
                      String password) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.password = password;

        totFailedAttempts=0;
        isInsolvent=false;

    }

    // GETTER AND SETTER //

    public String getUser_id() {
        return userId;
    }

    public void setUser_id(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFirst_name() {
        return name;
    }

    public void setFirst_name(String name) {
        this.name = name;
    }

    public String getLast_name() {
        return surname;
    }

    public void setLast_name(String surname) {
        this.surname = surname;
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
        return totFailedAttempts;
    }

    public void setFailedPayments(int totFailedAttempts) {
        this.totFailedAttempts = totFailedAttempts;
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

    public List<ServicePackEntity> getServicePackages() {
        return servicePackages;
    }

    public void setServicePackages(List<ServicePackEntity> servicePackages) {
        this.servicePackages = servicePackages;
    }

    public List<ErrorEntity> getErrors() {
        return errors;
    }

    public void setErrors(List<ErrorEntity> errors) {
        this.errors = errors;
    }

    // METHODS //

    public void incrementFailedPayments() {
        totFailedAttempts++;
    }

}