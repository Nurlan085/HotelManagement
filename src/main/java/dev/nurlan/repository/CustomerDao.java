package dev.nurlan.repository;

import dev.nurlan.entity.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {

    List<Customer> findAllByActive(Integer active);

    Customer findByIdAndActive(Long id, Integer active);
}
