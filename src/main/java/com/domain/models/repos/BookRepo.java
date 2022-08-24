package com.domain.models.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.domain.models.entities.Book;

public interface BookRepo extends JpaRepository<Book,Long> {
    
}
