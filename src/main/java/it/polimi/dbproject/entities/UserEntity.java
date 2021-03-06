package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity

@NamedQuery(
        name = "User.retrieveUserThroughID",
        query = "SELECT u FROM UserEntity u " +
                "WHERE u.id = :id"
)

@NamedQuery(
        name = "User.retrieveInsolventUser",
        query = "SELECT u FROM UserEntity u " +
                "WHERE u.isInsolvent = true"
)

@NamedQuery(
        name = "User.loginUser",
        query = "SELECT u FROM UserEntity u " +
                "WHERE u.username = :usn and u.password = :psw"
)

@NamedQuery(
        name = "User.retrieveInsolventUsers",
        query = "SELECT u FROM UserEntity u " +
                "WHERE u.isInsolvent = true"
)

@Table(name = "user", schema = "dbproject2022")
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique=true, nullable=false)
    private int id;

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

    @OneToMany(targetEntity = OrderEntity.class, fetch = FetchType.LAZY, mappedBy="owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderEntity> orders;

    @OneToMany(targetEntity = ServicePackEntity.class, fetch = FetchType.LAZY, cascade = {  CascadeType.PERSIST,
                                                                                            CascadeType.MERGE,
                                                                                            CascadeType.REFRESH,
                                                                                            CascadeType.DETACH})
    private List<ServicePackEntity> servicePackages;

    @OneToMany(targetEntity = ErrorEntity.class, fetch = FetchType.EAGER, mappedBy="owner", cascade = CascadeType.ALL, orphanRemoval = true)
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

    public int getUser_id() {
        return id;
    }

    public void setUser_id(int id) {
        this.id = id;
    }

    public String getUsername() {
        return this.username;
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




    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totFailedAttempts=" + totFailedAttempts +
                ", isInsolvent=" + isInsolvent +
                ", orders=" + orders +
                ", servicePackages=" + servicePackages +
                ", errors=" + errors +
                '}';
    }
}