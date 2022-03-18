package it.polimi.dbproject.entities;

import it.polimi.dbproject.services.UserService;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@NamedQuery(
        name = "ServicePack.retrievePackageThroughOrderID",
        query = " SELECT s FROM ServicePackEntity s" +
                " WHERE s.order.orderId = :orderId"
)

@Entity
@Table(name = "service_pack", schema = "dbproject2022")
public class ServicePackEntity implements Serializable{

    private static final long serialVersionUID = 1L;

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

    @Column(name = "total_cost_optional_services", nullable = false)
    private int totalCostOptionalService;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "available_package")
    private AvailableServicePackEntity availablePackages;

    @ManyToMany (fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name="optional_services_selected",
            joinColumns={@JoinColumn(name="service_pack_id")},
            inverseJoinColumns={@JoinColumn(name="optional_service_id")}
    )
    private List<OptionalServiceEntity> selectedOptionalServices;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name ="period_service_pack")
    private PeriodEntity chosenPeriod;

    @ManyToOne (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_service_package")
    private UserEntity user_service_package;

    @OneToOne(mappedBy = "servicePackageOrder", cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE,
            CascadeType.REFRESH,
            CascadeType.DETACH}, orphanRemoval = true)
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
        return this.cost + this.totalCostOptionalService;
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

    public void setAvailablePackage (AvailableServicePackEntity availablePackages){
        this.availablePackages = availablePackages;
    }

    public PeriodEntity getChosenPeriod(){return this.chosenPeriod;}

    public void setChosenPeriod(PeriodEntity chosenPeriod){ this.chosenPeriod=chosenPeriod; }

    public AvailableServicePackEntity getAvailablePackage() { return this.availablePackages; }

    public void setUser(UserEntity user){
        this.user_service_package = user;
    }

    public UserEntity getUser(){
        return this.user_service_package;
    }

    public List<OptionalServiceEntity> getSelectedOptionalServices(){
        return this.selectedOptionalServices;
    }

    public int getServicePack_id() {
        return servicePack_id;
    }

    public void setServicePack_id(int servicePack_id) {
        this.servicePack_id = servicePack_id;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTotalCostOptionalService() {
        return totalCostOptionalService;
    }

    public void setTotalCostOptionalService(int totalCostOptionalService) {
        this.totalCostOptionalService = totalCostOptionalService;
    }

    public void setAvailablePackages(AvailableServicePackEntity availablePackages) {
        this.availablePackages = availablePackages;
    }

    public void setSelectedOptionalServices(List<OptionalServiceEntity> selectedOptionalServices) {
        this.selectedOptionalServices = selectedOptionalServices;
    }

    public UserEntity getUser_service_package() {
        return user_service_package;
    }

    public void setUser_service_package(UserEntity user_service_package) {
        this.user_service_package = user_service_package;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
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
