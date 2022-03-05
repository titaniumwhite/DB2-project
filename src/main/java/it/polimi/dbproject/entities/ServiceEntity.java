package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service", schema = "dbproject2022")
public class ServiceEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "service_id", nullable = false)
    private int service_id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "minutes", nullable = false)
    private String minutes;

    @Column(name = "sms", nullable = false)
    private String sms;

    @Column(name = "gigas", nullable = false)
    private String gigas;

    @Column(name = "extraMinutes_fee", nullable = false)
    private String extraMinutes_fee;

    @Column(name = "extraSms_fee", nullable = false)
    private String extraSms_fee;

    @Column(name = "extraGigas_fee", nullable = false)
    private String extraGigas_fee;

    public ServiceEntity() {

    }

    public ServiceEntity(String type,
                         String minutes,
                         String sms,
                         String gigas,
                         String extraMinutes_fee,
                         String extraSms_fee,
                         String extraGigas_fee) {

        this.type = type;
        this.minutes = minutes;
        this.sms = sms;
        this.gigas = gigas;
        this.extraMinutes_fee = extraMinutes_fee;
        this.extraSms_fee = extraSms_fee;
        this.extraGigas_fee = extraGigas_fee;

    }

    // GETTER AND SETTER //

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMinutes() {
        return minutes;
    }

    public void setMinutes(String minutes) {
        this.minutes = minutes;
    }

    public String getSms() {
        return sms;
    }

    public void setSms(String sms) {
        this.sms = sms;
    }

    public String getGigas() {
        return gigas;
    }

    public void setGigas(String gigas) {
        this.gigas = gigas;
    }

    public String getExtraMinutes_fee() {
        return extraMinutes_fee;
    }

    public void setExtraMinutes_fee(String extraMinutes_fee) {
        this.extraMinutes_fee = extraMinutes_fee;
    }

    public String getExtraSms_fee() {
        return extraSms_fee;
    }

    public void setExtraSms_fee(String extraSms_fee) {
        this.extraSms_fee = extraSms_fee;
    }

    public String getExtraGigas_fee() {
        return extraGigas_fee;
    }

    public void setExtraGigas_fee(String extraGigas_fee) {
        this.extraGigas_fee = extraGigas_fee;
    }



    @Override
    public String toString(){
        return type;
    }
}