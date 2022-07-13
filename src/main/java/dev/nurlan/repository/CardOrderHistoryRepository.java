package dev.nurlan.repository;

import dev.nurlan.entity.CardOrderHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface CardOrderHistoryRepository extends JpaRepository<CardOrderHistory, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "insert into card_order_history (cif, first_four, last_four, currier_id) values(:cif, :first_four, :last_four, :currier_id)")
    void insertCardOrder(@Param("cif") String cif,
                         @Param("first_four") String first_four,
                         @Param("last_four") String last_four,
                         @Param("currier_id") Long currier_id);

    @Modifying
    @Query(nativeQuery = true,
            value = "update card_order_history set cif = :cif where id = :id")
    void updateCif(@Param("id") Long id,
                   @Param("cif") String cif);
}
