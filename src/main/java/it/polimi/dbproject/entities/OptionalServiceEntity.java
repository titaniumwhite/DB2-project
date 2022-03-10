package it.polimi.dbproject.entities;


import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(
        name = "OptionalService.findAll",
        query = "SELECT os FROM OptionalServiceEntity os"
)
@NamedQuery(
        name = "OptionalService.findOptionalThroughPackage",
        query = "SELECT os FROM OptionalServiceEntity os " +
                "JOIN os.availableServicePackages a " +
                "WHERE a.availableServicePackId = :availableServicePackId "
)
@NamedQuery(
        name = "OptionalService.findServiceThroughID",
        query = "SELECT os FROM OptionalServiceEntity os " +
                "WHERE os.optionalService_id = :optionalService_id"
)

@Table(name = "optional_service", schema = "dbproject2022")
public class OptionalServiceEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "optional_service_id", unique = true, nullable = false)
    private String optionalService_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "monthly_fee", nullable = false)
    private int monthlyFee;

    @ManyToMany(mappedBy = "optional_services", fetch = FetchType.LAZY)
    private List<AvailableServicePackEntity> availableServicePackages;

    /*
    @ManyToMany(mappedBy = "offeredOptionalServiceToSinglePackages", fetch = FetchType.EAGER)
    private List<OptionalServiceEntity> optionalServiceEntities;
*/
    public OptionalServiceEntity(){}

    public OptionalServiceEntity(
            String name,
            int monthlyFee){

        this.name = name;
        this.monthlyFee = monthlyFee;

    }

    // GETTER AND SETTER //
    public String getId() {
        return optionalService_id;
    }

    public void setId(String optionalService_id) {
        this.optionalService_id = optionalService_id;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMonthly_fee(int monthlyFee){
        this.monthlyFee = monthlyFee;
    }

    public int getMonthly_fee(){
        return this.monthlyFee;
    }

}
