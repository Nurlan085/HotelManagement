package dev.nurlan.request;

import dev.nurlan.entity.CardOrderHistory;

import java.io.Serializable;

public class CardOrderRequest implements Serializable {

    private String cif;
    private String firstFour;
    private String lastFour;
    private Long currierId;

    public String getCif() {
        return cif;
    }

    public void setCif(String cif) {
        this.cif = cif;
    }

    public String getFirstFour() {
        return firstFour;
    }

    public void setFirstFour(String firstFour) {
        this.firstFour = firstFour;
    }

    public String getLastFour() {
        return lastFour;
    }

    public void setLastFour(String lastFour) {
        this.lastFour = lastFour;
    }

    public Long getCurrierId() {
        return currierId;
    }

    public void setCurrierId(Long currierId) {
        this.currierId = currierId;
    }

    public static CardOrderHistory dtoToEntity(CardOrderRequest request) {
        return new CardOrderHistory()
                .setCif(request.getCif())
                .setFirstFour(request.getFirstFour())
                .setLastFour(request.getLastFour())
                .setCurrierId(request.getCurrierId());
    }
}
