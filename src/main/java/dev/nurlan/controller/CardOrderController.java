package dev.nurlan.controller;

import dev.nurlan.entity.CardOrderHistory;
import dev.nurlan.exception.CustomException;
import dev.nurlan.repository.CardOrderHistoryRepository;
import dev.nurlan.request.CardOrderRequest;
import dev.nurlan.response.CardOrderResponse;
import org.springframework.web.bind.annotation.*;

@RestController
public class CardOrderController {

    private final CardOrderHistoryRepository cardOrderHistoryRepository;

    public CardOrderController(CardOrderHistoryRepository cardOrderHistoryRepository) {
        this.cardOrderHistoryRepository = cardOrderHistoryRepository;
    }

    @PostMapping(value = "/card-order-create")
    public String cardOrderCreate(@RequestBody CardOrderRequest request) {
//        cardOrderHistoryRepository.insertCardOrder(
//                request.getCif(), request.getFirstFour(), request.getLastFour(), request.getCurrierId());
        cardOrderHistoryRepository.save(CardOrderRequest.dtoToEntity(request));
        return null;
    }

    @PutMapping(value = "/update-cif/{id}/{cif}")
    public String updateCif(@PathVariable("id") Long id,
                            @PathVariable("cif") String cif) {
        cardOrderHistoryRepository.updateCif(id, cif);
        return null;
    }

    @GetMapping(value = "/find-card-order-by-id/{id}")
    public CardOrderHistory findCardOrderById(@PathVariable("id") Long id) {
        CardOrderHistory response = cardOrderHistoryRepository.findById(id)
                .orElseThrow(() -> new CustomException("Data not found"));
        return response;
    }
}
