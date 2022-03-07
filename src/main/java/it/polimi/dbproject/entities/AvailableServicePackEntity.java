package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(
        name = "AvailableServicePackage.findAll",
        query = "SELECT servicePackages FROM AvailableServicePackEntity servicePackages "
)

@Table(name = "available_service_package", schema = "dbproject2022")
public class AvailableServicePackEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "available_service_pack_id", nullable = false)
    private int availableServicePackId;

    @Column(name = "name", nullable = false)
    private int name;

    @OneToMany(mappedBy="availablePackages", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServicePackEntity> offeredToPackage;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceEntity> offeredServices;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PeriodEntity offeredPeriod;


    @ManyToMany(fetch=FetchType.LAZY)
    @JoinTable(
            name="services_to_offer",
            joinColumns={@JoinColumn(name="availableServicePackages")},
            inverseJoinColumns={@JoinColumn(name="service_id")}
    )
    private List<ServiceEntity> services;


    public AvailableServicePackEntity(){}

    public AvailableServicePackEntity(int name,
                                      List<ServiceEntity> services) {
        this.name = name;
        this.services = services;
    }

    // GETTER AND SETTER //

    public int getAvailableServicePack_id() {
        return availableServicePackId;
    }

    public void setAvailableServicePack_id(int availableServicePackId) { this.availableServicePackId = availableServicePackId; }

    public int getName() {
        return name;
    }

    public void setName(int name) {
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

    public int getAvailableServicePackId() {
        return availableServicePackId;
    }

    public void setAvailableServicePackId(int availableServicePackId) {
        this.availableServicePackId = availableServicePackId;
    }

    public PeriodEntity getOfferedPeriod() {
        return offeredPeriod;
    }

    public void setOfferedPeriod(PeriodEntity offeredPeriod) {
        this.offeredPeriod = offeredPeriod;
    }

    public List<ServiceEntity> getServices() {
        return services;
    }

    public void setServices(List<ServiceEntity> services) {
        this.services = services;
    }

}