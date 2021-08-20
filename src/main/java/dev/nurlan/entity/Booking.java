package dev.nurlan.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;
import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "booking")
@DynamicInsert
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(optional = false)
    @JoinColumn(name = "cust_id")
    private Customer customer;
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_id")
    private Room room;
    @Basic(optional = false)
    private Float commonPrice;
    @Basic(optional = false)
    private Integer bookingType;
    @Basic(optional = false)
    private Date fromDate;
    @Basic(optional = false)
    private Date toDate;
    @CreationTimestamp
    private Date bookingDate;
    @ColumnDefault("1")
    private Date exitDate;
    private Integer active;


}
