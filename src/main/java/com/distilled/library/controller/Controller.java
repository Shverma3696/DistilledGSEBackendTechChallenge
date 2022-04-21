package com.distilled.library.controller;

import com.distilled.library.entity.Author;
import com.distilled.library.entity.Book;
import com.distilled.library.model.addAuthorRequest;
import com.distilled.library.model.addBookRequest;
import com.distilled.library.model.borrowBookRequest;
import com.distilled.library.model.searchResponse;
import com.distilled.library.service.AuthorService;
import com.distilled.library.service.BookService;
import com.distilled.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
public class Controller {

    @Qualifier("BookService")
    @Autowired
    BookService bookService;

    @Qualifier("AuthorService")
    @Autowired
    AuthorService authorService;

    @Qualifier("BorrowService")
    @Autowired
    BorrowService borrowService;

    @PutMapping("/addBookDetails")
    public ResponseEntity<Long> addBookData(@RequestBody addBookRequest bookRequest){
        Optional<Book> bookResponse = bookService.addBookDetails(bookRequest);
        if(bookResponse.isPresent())
            return ResponseEntity.ok(bookResponse.get().getBookId());
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }

    @PutMapping("/addAuthorDetails")
    public ResponseEntity<Long> addAuthorData(@RequestBody addAuthorRequest authorRequest){
        Optional<Author> authorResponse = authorService.addAuthorDetails(authorRequest);
        if(authorResponse.isPresent())
            return ResponseEntity.ok(authorResponse.get().getAuthorId());
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }

    @PostMapping("/borrowBook")
    public ResponseEntity<Long> borrowBook(@RequestBody borrowBookRequest borrowBookRequest){
        Optional<Book> borrowBookResponse = bookService.checkAvailibility(borrowBookRequest);
        if(borrowBookResponse.isPresent())
            return ResponseEntity.ok(borrowBookResponse.get().getBookId());
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/searchByIsbn/{isbn}")
    public ResponseEntity<List<searchResponse>> searchByIsbn(@PathVariable("isbn") String isbn){
        List<searchResponse> borrowBookResponse = bookService.searchByIsbn(isbn);
        if(borrowBookResponse.size() > 0)
            return ResponseEntity.ok(borrowBookResponse);
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/searchByTitle/{title}")
    public ResponseEntity<List<searchResponse>> searchByTitle(@PathVariable("title") String title){
        List<searchResponse> borrowBookResponse = bookService.searchByTitle(title);
        if(borrowBookResponse.size() > 0)
            return ResponseEntity.ok(borrowBookResponse);
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }

    @GetMapping("/searchByAuthor/{authorName}")
    public ResponseEntity<Set<searchResponse>> searchByAuthorName(@PathVariable("authorName") String authorName){
        Set<searchResponse> borrowBookResponse = borrowService.searchByAuthor(authorName);
        if(borrowBookResponse.size() > 0)
            return ResponseEntity.ok(borrowBookResponse);
        else
            return (ResponseEntity) ResponseEntity.notFound();
    }
}
