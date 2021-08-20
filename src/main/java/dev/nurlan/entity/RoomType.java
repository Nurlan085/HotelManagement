package dev.nurlan.entity;


import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicInsert;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "roomType")
@DynamicInsert
public class RoomType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Basic(optional = false)
    private String name;
    private String description;
    @CreationTimestamp
    private Date dataDate;
    @ColumnDefault("1")
    private Integer active;
}
