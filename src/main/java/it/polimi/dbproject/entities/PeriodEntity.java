package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@NamedQuery(
        name = "Period.findAll",
        query = "SELECT p FROM PeriodEntity p"
)
@NamedQuery(
        name = "Period.findPeriodThroughPackage",
        query = "SELECT p FROM PeriodEntity p " +
                "JOIN p.availableServicePackages s " +
                "WHERE s.availableServicePackId = :availableServicePackId "
)
@NamedQuery(
        name = "Period.findPeriodThroughID",
        query = "SELECT p FROM PeriodEntity p " +
                "WHERE p.periodId = :periodId"
)

@Table(name = "period", schema = "dbproject2022")
public class PeriodEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "period_id", unique = true, nullable = false)
    private int periodId;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "monthly_fee", nullable = false)
    private int monthlyFee;

    @ManyToMany(mappedBy = "periods", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<AvailableServicePackEntity> availableServicePackages;

    public PeriodEntity() {    }

    public PeriodEntity(int duration) {
        this.duration = duration;
    }

    // GETTER AND SETTER //


    public int getPeriod_id() {
        return periodId;
    }

    public void setPeriod_id(int periodId) {
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

