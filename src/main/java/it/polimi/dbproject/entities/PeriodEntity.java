package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "period", schema = "dbproject2022")
public class PeriodEntity implements Serializable{

    @Id
    @Column(name = "duration", nullable = false)
    private int duration;

    // GETTER AND SETTER //

    public void setDuration(int Duration){
        this.duration = duration;
    }

    public int getDuration(){
        return this.duration;
    }
}
