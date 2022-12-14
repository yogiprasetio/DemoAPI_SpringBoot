package com.domain.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.domain.dto.ResponseData;
import com.domain.models.entities.Book;
import com.domain.services.BookService;
import com.domain.utils.CSVHelper;

import java.util.List;;

@RestController
@RequestMapping("/books")
public class BookController {
    
    @Autowired
    private BookService bookService;

    @GetMapping
    public ResponseEntity<?> findAllBook(){
        ResponseData response = new ResponseData<>();
        try{
            List<Book> books = bookService.findAll();
            response.setStatus(true);
            response.getMessages().add("Get all books");
            response.setPayload(books);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add("Could not get Books : "+ ex.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @PostMapping("/upload")
    public ResponseEntity<?> uploadFile(@RequestParam("file") MultipartFile file){
        ResponseData response = new ResponseData<>();
        if(!CSVHelper.hasCSVFormat(file)){
            response.setStatus(false);
            response.getMessages().add("PLEASE UPLOADS A CSV FILE !!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }
        try{
            List<Book> books = bookService.save(file);
            response.setStatus(true);
            response.getMessages().add("Uploaded the file SUCCESS : "+file.getOriginalFilename());
            response.setPayload(books);
            return ResponseEntity.ok(response);
        }catch(Exception ex){
            response.setStatus(false);
            response.getMessages().add("Could not get Books : "+ file.getOriginalFilename());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
        }
    }

}
