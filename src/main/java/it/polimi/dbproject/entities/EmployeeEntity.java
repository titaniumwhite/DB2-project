package it.polimi.dbproject.entities;

import javax.persistence.*;

import java.io.Serializable;

@Table(name = "employee", schema = "dbproject2022")
public class EmployeeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "employee_id", nullable = false)
    private Long employeeId;

    @Column(name = "email", unique = true, nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    public EmployeeEntity() {
    }

    public EmployeeEntity(String email,
                          String password) {

        this.email = email;
        this.password = password;

    }

    public Long getEmployee_id() {
        return employeeId;
    }

    public void setEmployee_id(Long employeeId) {
        this.employeeId = employeeId;
    }

    // GETTER AND SETTER //

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}