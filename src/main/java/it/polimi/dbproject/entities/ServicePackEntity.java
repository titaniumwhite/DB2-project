package it.polimi.dbproject.entities;

import it.polimi.dbproject.services.UserService;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "service_pack", schema = "dbproject2022")
public class ServicePackEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_pack_id", nullable = false)
    private int servicePack_id;

    @Column(name = "start_date", nullable = false)
    private java.sql.Date startDate;

    @Column(name = "end_date", nullable = false)
    private java.sql.Date endDate;

    @Column(name = "cost", nullable = false)
    private int cost;

    @Column(name = "total_cost_optional_service", nullable = false)
    private int totalCostOptionalService;

    @ManyToOne (fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "available_package")
    private AvailableServicePackEntity availablePackages;

    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name="optional_services_selected",
            joinColumns={@JoinColumn(name="service_pack_id")},
            inverseJoinColumns={@JoinColumn(name="optional_service_id")}
    )
    private List<OptionalServiceEntity> selectedOptionalServices;


    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PeriodEntity chosenPeriod;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_service_package")
    private UserEntity user_service_package;


    @OneToOne(mappedBy = "servicePackageOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    private OrderEntity order;

    public ServicePackEntity(){}

    public ServicePackEntity(java.sql.Date startDate,
                             java.sql.Date endDate,
                             int cost,
                             int totalCostOptionalService,
                             AvailableServicePackEntity selectedAvailableService,
                             PeriodEntity period,
                             List<OptionalServiceEntity> optionalServices) {

        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.totalCostOptionalService = totalCostOptionalService;
        this.availablePackages = selectedAvailableService;
        this.chosenPeriod = period;
        this.selectedOptionalServices = optionalServices;

    }


    // GETTER AND SETTER //

    public void setId(int servicePack_id){
        this.servicePack_id = servicePack_id;
    }

    public int getId(){
        return servicePack_id;
    }

    public void setTotalCost(int cost){
        this.cost = cost;
    }

    public int getTotalCost(){
        return this.cost;
    }

    public java.sql.Date getStartDate() {
        return startDate;
    }

    public void setStartDate(java.sql.Date startDate) {
        this.startDate = startDate;
    }

    public java.sql.Date getEndDate() {
        return endDate;
    }

    public void setEndDate(java.sql.Date endDate) {
        this.endDate = endDate;
    }

    public int getOptionalServicesFee() {
        return totalCostOptionalService;
    }

    public void setOptionalServicesFee(int totalCostOptionalService) {
        this.totalCostOptionalService = totalCostOptionalService;
    }

    public void setUser(UserEntity user){
        this.user_service_package = user;
    }

    public UserEntity getUser(){
        return this.user_service_package;
    }

    public List<OptionalServiceEntity> getSelectedOptionalServices(){
        return selectedOptionalServices;
    }

    public AvailableServicePackEntity getAvailablePackages(){
        return availablePackages;
    }

    @Override
    public String toString() {
        return "ServicePackEntity{" +
                "servicePack_id=" + servicePack_id +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", cost=" + cost +
                ", totalCostOptionalService=" + totalCostOptionalService +
                ", availablePackages=" + availablePackages +
                ", selectedOptionalServices=" + selectedOptionalServices +
                ", chosenPeriod=" + chosenPeriod +
                ", user_service_package=" + user_service_package +
                ", order=" + order +
                '}';
    }
}
