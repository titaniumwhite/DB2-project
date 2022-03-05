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
    private String period_id;

    @Column(name = "duration", nullable = false)
    private int duration;

    public PeriodEntity() {    }

    public PeriodEntity(int duration) {
        this.duration = duration;
    }

    // GETTER AND SETTER //


    public String getPeriod_id() {
        return period_id;
    }

    public void setPeriod_id(String period_id) {
        this.period_id = period_id;
    }

    public void setDuration(int Duration){
        this.duration = duration;
    }

    public int getDuration(){
        return this.duration;
    }
}
