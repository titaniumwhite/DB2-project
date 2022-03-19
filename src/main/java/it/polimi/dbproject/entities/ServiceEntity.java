package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@NamedQuery(
        name = "Service.retrieveAllAvailableServicePackages",
        query = "SELECT s FROM ServiceEntity s"
)

@Entity
@Table(name = "service", schema = "dbproject2022")
public class ServiceEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private int serviceId;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "num_of_minutes", nullable = false)
    private int numOfMinutes;

    @Column(name = "num_of_SMS", nullable = false)
    private int numOfSMS;

    @Column(name = "num_of_giga", nullable = false)
    private int numOfGiga;

    @Column(name = "fee_extra_minutes", nullable = false)
    private int feeExtraMinutes;

    @Column(name = "fee_extra_sms", nullable = false)
    private int feeExtraSMS;

    @Column(name = "fee_extra_giga", nullable = false)
    private int feeExtraGiga;

    @ManyToMany(mappedBy = "services", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AvailableServicePackEntity> availableServicePackages;

    public ServiceEntity() {

    }

        public ServiceEntity(String type,
                         int numOfMinutes,
                         int numOfSMS,
                         int numOfGiga,
                         int feeExtraMinutes,
                         int feeExtraSMS,
                         int feeExtraGiga) {

        this.type = type;
        this.numOfMinutes = numOfMinutes;
        this.numOfSMS = numOfSMS;
        this.numOfGiga = numOfGiga;
        this.feeExtraMinutes = feeExtraMinutes;
        this.feeExtraSMS = feeExtraSMS;
        this.feeExtraGiga = feeExtraGiga;

    }

    // GETTER AND SETTER //

    public int getService_id() {
        return serviceId;
    }

    public void setService_id(int serviceId) {
        this.serviceId = serviceId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMinutes() {
        return numOfMinutes;
    }

    public void setMinutes(int numOfMinutes) {
        this.numOfMinutes = numOfMinutes;
    }

    public int getSms() {
        return numOfSMS;
    }

    public void setSms(int numOfSMS) {
        this.numOfSMS = numOfSMS;
    }

    public int getGigas() {
        return numOfGiga;
    }

    public void setGigas(int numOfGiga) {
        this.numOfGiga = numOfGiga;
    }

    public int getExtraMinutes_fee() {
        return feeExtraMinutes;
    }

    public void setExtraMinutes_fee(int feeExtraMinutes) {
        this.feeExtraMinutes = feeExtraMinutes;
    }

    public int getExtraSms_fee() {
        return feeExtraSMS;
    }

    public void setExtraSms_fee(int feeExtraSMS) {
        this.feeExtraSMS = feeExtraSMS;
    }

    public int getExtraGigas_fee() {
        return feeExtraGiga;
    }

    public void setExtraGigas_fee(int feeExtraGiga) {
        this.feeExtraGiga = feeExtraGiga;
    }



    @Override
    public String toString(){
        return type;
    }
}