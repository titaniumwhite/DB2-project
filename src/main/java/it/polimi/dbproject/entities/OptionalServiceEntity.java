package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "optional_service", schema = "dbproject2022")
public class OptionalServiceEntity implements Serializable{

    @Id
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = true)
    private String description;

    @Column(name = "monthly_fee", nullable = false)
    private int monthly_fee;

    /*employeeID*/

    public OptionalServiceEntity(){}

    public OptionalServiceEntity(
            String name,
            String description,
            int monthly_fee
    ){
        this.name = name;
        this.description = description;
        this.monthly_fee = monthly_fee;
    }

    // GETTER AND SETTER //

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setDescription(String description){
        this.description = description;
    }

    public String getDescription(){
        return this.description;
    }

    public void setMonthly_fee(int monthlyFee){
        this.monthly_fee = monthlyFee;
    }

    public int getMonthly_fee(){
        return this.monthly_fee;
    }

    @Override
    public String toString(){
        return name;
    }
}
