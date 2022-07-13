package dev.nurlan.entity;

import javax.persistence.*;

@Entity
@Table(name = "card_order_history")
public class CardOrderHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cif")
    private String cif;

    @Column(name = "first_four")
    private String firstFour;

    @Column(name = "last_four")
    private String lastFour;

    @Column(name = "currier_id")
    private Long currierId;

    public Long getId() {
        return id;
    }

    public CardOrderHistory setId(Long id) {
        this.id = id;
        return this;
    }

    public String getCif() {
        return cif;
    }

    public CardOrderHistory setCif(String cif) {
        this.cif = cif;
        return this;
    }

    public String getFirstFour() {
        return firstFour;
    }

    public CardOrderHistory setFirstFour(String firstFour) {
        this.firstFour = firstFour;
        return this;
    }

    public String getLastFour() {
        return lastFour;
    }

    public CardOrderHistory setLastFour(String lastFour) {
        this.lastFour = lastFour;
        return this;
    }

    public Long getCurrierId() {
        return currierId;
    }

    public CardOrderHistory setCurrierId(Long currierId) {
        this.currierId = currierId;
        return this;
    }
}
