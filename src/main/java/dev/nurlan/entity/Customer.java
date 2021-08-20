package dev.nurlan.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "customer")
@DynamicInsert
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private String name;
    @Basic(optional = false)
    private String surname;
    private Date dob;
    private String address;
    private String mobile;
    private Integer gender;
    @Basic(optional = false)
    private String passport;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault("1")
    private Integer active;

}
