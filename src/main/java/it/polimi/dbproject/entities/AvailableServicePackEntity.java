package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(
        name = "AvailableServicePackage.findAll",
        query = "SELECT asp FROM AvailableServicePackEntity asp"
)
@NamedQuery(
        name = "AvailableServicePackage.findByID",
        query = "SELECT asp " +
                "FROM AvailableServicePackEntity asp " +
                "WHERE asp.availableServicePackId = :availableServicePackId"
)

@Table(name = "available_service_package", schema = "dbproject2022")
public class AvailableServicePackEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_service_pack_id", nullable = false)
    private int availableServicePackId;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy="availablePackages", fetch = FetchType.EAGER, cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH})
    private List<ServicePackEntity> offeredToPackage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceEntity> offeredServices;

    @ManyToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="services_to_offer",
            joinColumns={@JoinColumn(name="available_service_pack_id")},
            inverseJoinColumns={@JoinColumn(name="service_id")}
    )
    private List<ServiceEntity> services;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="period_to_offer",
            joinColumns={@JoinColumn(name="available_service_pack_id")},
            inverseJoinColumns={@JoinColumn(name="period_id")}
    )
    private List<PeriodEntity> periods;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
            name="optional_services_to_offer",
            joinColumns={@JoinColumn(name="available_service_pack_id")},
            inverseJoinColumns={@JoinColumn(name="optional_service_id")}
    )
    private List<OptionalServiceEntity> optionalServices;


    public AvailableServicePackEntity(){}

    public AvailableServicePackEntity(String name,
                                      List<ServiceEntity> services,
                                      List<PeriodEntity> periods,
                                      List<OptionalServiceEntity> optionalServices) {
        this.name = name;
        this.services = services;
        this.periods = periods;
        this.optionalServices = optionalServices;
    }

    // GETTER AND SETTER //

    public int getAvailableServicePack_id() {
        return availableServicePackId;
    }

    public void setAvailableServicePack_id(int availableServicePackId) { this.availableServicePackId = availableServicePackId; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ServicePackEntity> getOfferedToPackage() {
        return offeredToPackage;
    }

    public void setOfferedToPackage(List<ServicePackEntity> offeredToPackage) { this.offeredToPackage = offeredToPackage; }

    public List<ServiceEntity> getOfferedServices() {
        return offeredServices;
    }

    public void setOfferedServices(List<ServiceEntity> offeredServices) {
        this.offeredServices = offeredServices;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

    public List<PeriodEntity> getPeriods() {
        return periods;
    }

    public void setPeriods(List<PeriodEntity> periods) {
        this.periods = periods;
    }

    public List<OptionalServiceEntity> getOptionalServices() {
        return optionalServices;
    }

    public void setOptionalServices(List<OptionalServiceEntity> optionalServices) {
        this.optionalServices = optionalServices;
    }

    @Override
    public String toString() {
        return "AvailableServicePackEntity{" +
                "availableServicePackId=" + availableServicePackId +
                ", name='" + name + '\'' +
                '}';
    }
}