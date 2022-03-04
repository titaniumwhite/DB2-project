package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;

@Entity

@Table(name = "service", schema = "dbproject2022")
public class ServiceEntity implements Serializable{

    @Column(name = "type", nullable = false)
    private String type;

    public ServiceEntity(){}

    public ServiceEntity(
            String type
    ) {
        this.type = type;
    }

    // GETTER AND SETTER //

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    @Override
    public String toString(){
        return type;
    }
}
