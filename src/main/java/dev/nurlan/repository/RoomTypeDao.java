package dev.nurlan.repository;


import dev.nurlan.entity.RoomType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomTypeDao extends CrudRepository<RoomType, Long> {

    RoomType findByIdAndActive(Long id, Integer active);

}
