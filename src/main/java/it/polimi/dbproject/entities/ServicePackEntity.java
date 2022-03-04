package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "service_pack", schema = "dbproject2022")
public class ServicePackEntity implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "monthly_fee", nullable = false)
    private int monthlyFee;

    /*employeeID*/

    public ServicePackEntity(){}

    public ServicePackEntity(
            int id,
            String name,
            int monthlyFee){
        this.id = id;
        this.name = name;
        this.monthlyFee = monthlyFee;
    }

    // GETTER AND SETTER //

    public void setServiceID(int id){
        this.id = id;
    }

    public int getServiceID(){
        return id;
    }

    public void setName (String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setMonthlyFee(int monthlyFee){
        this.monthlyFee = monthlyFee;
    }

    public int getMonthlyFee(){
        return this.monthlyFee;
    }
}
