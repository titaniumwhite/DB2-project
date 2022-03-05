package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "period", schema = "dbproject2022")
public class PeriodEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_id", unique = true, nullable = false)
    private String periodId;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "monthly_fee", nullable = false)
    private int monthlyFee;

    public PeriodEntity() {    }

    public PeriodEntity(int duration) {
        this.duration = duration;
    }

    // GETTER AND SETTER //


    public String getPeriod_id() {
        return periodId;
    }

    public void setPeriod_id(String periodId) {
        this.periodId = periodId;
    }

    public void setDuration(int duration){
        this.duration = duration;
    }

    public int getDuration(){
        return this.duration;
    }

    public void getMonthlyFee(int monthlyFee){
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee(){
        return this.monthlyFee;
    }
}

