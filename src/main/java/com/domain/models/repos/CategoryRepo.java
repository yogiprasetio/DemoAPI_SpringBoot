package com.domain.models.repos;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.domain.models.entities.Category;

public interface CategoryRepo extends PagingAndSortingRepository<Category, Long> {
    ///Paging and sorting repo merupakan turunan dari CRUD Repository..

    Page<Category> findByNameContains (String name, Pageable pageable);


}
