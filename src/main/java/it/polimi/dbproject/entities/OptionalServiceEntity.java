package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "optional_service", schema = "dbproject2022")
public class OptionalServiceEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "optionalService_id", unique = true, nullable = false)
    private String optionalService_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "monthly_fee", nullable = false)
    private int monthly_fee;

    @ManyToMany(mappedBy = "offeredOptionalServiceToAllPackages", fetch = FetchType.EAGER)
    private List<AvailableServicePackEntity> availableServicePackages;

    @ManyToMany(mappedBy = "offeredOptionalServiceToSinglePackages", fetch = FetchType.EAGER)
    private List<OptionalServiceEntity> optionalServiceEntities;

    public OptionalServiceEntity(){}

    public OptionalServiceEntity(
            String name,
            int monthly_fee){

        this.name = name;
        this.monthly_fee = monthly_fee;

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
        this.monthly_fee = monthlyFee;
    }

    public int getMonthly_fee(){
        return this.monthly_fee;
    }

}
