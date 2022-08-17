package com.domain.models.repos;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.domain.models.entities.Supplier;

public interface SupplierRepo extends CrudRepository<Supplier, Long> {
    ///Derive Query Method Spring Data JPA-QL
    ///nama harus mengikuti dokumentasi SPring

    Supplier findByEmail(String email);

    List<Supplier> findByNameContainsOrderByIdDesc(String name);

    List<Supplier> findByNameStartingWith(String prefix);

    List<Supplier> findByNameContainsOrEmailContains(String name, String email);

}
