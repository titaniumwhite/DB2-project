package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_pack", schema = "dbproject2022")
public class ServicePackEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "servicePack_id", nullable = false)
    private int servicePack_id;

    @Column(name = "startDate", nullable = false)
    private Date startDate;

    @Column(name = "endDate", nullable = false)
    private Date endDate;

    @Column(name = "total_cost", nullable = false)
    private int totalCost;

    @Column(name = "optional_services_fee", nullable = false)
    private int optionalServicesFee;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "selectedPackage")
    private AvailableServicePackEntity selectedPackage;

    @ManyToMany (mappedBy="offeredOptionalServiceToSinglePackages", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    // VA MESSA LA JOIN TABLE? //
    /*@JoinTable(
            name="offeredOptionalServiceToSinglePackages",
            joinColumns={@JoinColumn(name="id")},
            inverseJoinColumns={@JoinColumn(name="id")}
    )*/
    private List<OptionalServiceEntity> selectedOptionalServices;

    @OneToOne(mappedBy="chosenPeriod", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PeriodEntity chosenPeriod;

    public ServicePackEntity(){}

    public ServicePackEntity(Date startDate,
                             Date endDate,
                             int totalCost,
                             int optionalServicesFee) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCost = totalCost;
        this.optionalServicesFee = optionalServicesFee;

    }


    // GETTER AND SETTER //

    public void setId(int servicePack_id){
        this.servicePack_id = servicePack_id;
    }

    public int getId(){
        return servicePack_id;
    }

    public void setTotalCost(int totalCost){
        this.totalCost = totalCost;
    }

    public int getTotalCost(){
        return this.totalCost;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getOptionalServicesFee() {
        return optionalServicesFee;
    }

    public void setOptionalServicesFee(int optionalServicesFee) {
        this.optionalServicesFee = optionalServicesFee;
    }

}
