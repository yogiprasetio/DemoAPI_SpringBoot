package com.domain.services;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.domain.models.entities.Book;
import com.domain.models.repos.BookRepo;
import com.domain.utils.CSVHelper;

@Service("bookService")
@Transactional
public class BookService {
    
    @Autowired
    private BookRepo bookRepo;

    public List<Book> save(MultipartFile file){
        try{
            List<Book> books = CSVHelper.csvToBooks(file.getInputStream());
            return bookRepo.saveAll(books);
        }catch(IOException e){
            throw new RuntimeException("Fail to Store CSV Data : "+e.getMessage());
        }
    }

    public List<Book> findAll(){
        return bookRepo.findAll();
    }

}
