package dev.nurlan.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "room")
@DynamicInsert
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private String roomNo;
    @ManyToOne(optional = false)
    @JoinColumn(name = "room_type_id")
    private RoomType roomType;
    @ColumnDefault("0")
    private Integer roomStatus;
    @Basic(optional = false)
    private Integer roomFloor;
    @Basic(optional = false)
    private Float roomPrice;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault("1")
    private Integer active;
}
