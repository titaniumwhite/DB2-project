package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "available_service_pack", schema = "dbproject2022")
public class AvailableServicePackEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "availableServicePack_id", nullable = false)
    private int availableServicePack_id;

    @Column(name = "name", nullable = false)
    private int name;

    @OneToMany(mappedBy="offeredToPackage", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServicePackEntity> offeredToPackage;

    @OneToMany(mappedBy="offeredServices", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ServiceEntity> offeredServices;

    @OneToOne(mappedBy="offeredPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PeriodEntity offeredPeriod;

    @ManyToMany(mappedBy="offeredOptionalServiceToAllPackages", fetch=FetchType.LAZY)
    // VA MESSA LA JOIN TABLE? //
    /*@JoinTable(
            name="offeredOptionalServiceToAllPackages",
            joinColumns={@JoinColumn(name="id")},
            inverseJoinColumns={@JoinColumn(name="id")}
    )*/
    private List<OptionalServiceEntity> optionalServices;

    public AvailableServicePackEntity(){}

    public AvailableServicePackEntity(int name) {
        this.name = name;
    }

    // GETTER AND SETTER //

    public int getAvailableServicePack_id() {
        return availableServicePack_id;
    }

    public void setAvailableServicePack_id(int availableServicePack_id) { this.availableServicePack_id = availableServicePack_id; }

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


}