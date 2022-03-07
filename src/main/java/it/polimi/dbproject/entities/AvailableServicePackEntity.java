package it.polimi.dbproject.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
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

    // TO DO: DA AGGIUSTARE A TEMPO DEBITO //
    /*
    @ManyToMany(mappedBy="offeredOptionalServiceToAllPackages", fetch=FetchType.LAZY)
    // VA MESSA LA JOIN TABLE? //
    /*@JoinTable(
            name="offeredOptionalServiceToAllPackages",
            joinColumns={@JoinColumn(name="id")},
            inverseJoinColumns={@JoinColumn(name="id")}
    )
    private List<OptionalServiceEntity> optionalServices;
*/

    public AvailableServicePackEntity(){}

    public AvailableServicePackEntity(int name) {
        this.name = name;
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


}