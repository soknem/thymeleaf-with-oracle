package test1.demo1.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
@Getter
public class Customer {

    // --- Getters and Setters ---
    private Long id;
    private String firstName;
    private String lastName;
    private Double salary;
    private Date dob;
    private String gender;
    private String mobilePhone;

}
