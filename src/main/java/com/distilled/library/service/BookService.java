package com.distilled.library.service;

import com.distilled.library.entity.Author;
import com.distilled.library.entity.Book;
import com.distilled.library.entity.Borrow;
import com.distilled.library.model.addAuthorRequest;
import com.distilled.library.model.addBookRequest;
import com.distilled.library.model.borrowBookRequest;
import com.distilled.library.model.searchResponse;
import com.distilled.library.repository.AuthorRepository;
import com.distilled.library.repository.BookRepository;
import com.distilled.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service("BookService")
public class BookService {

    final String availableStatus = "AVAILABLE";
    final String borrowedStatus = "BORROWED";

    @Autowired
    BookRepository bookRepository;

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    BorrowRepository borrowRepository;

    public Optional<Book> addBookDetails(addBookRequest bookRequest){
        Book bookObj = createBookObject(bookRequest);
        return Optional.of(bookRepository.save(bookObj));
    }

    public Book createBookObject(addBookRequest bookRequest){
        Book bookObj = new Book();
        bookObj.setIsbn(bookRequest.getIsbn());
        bookObj.setTitle(bookRequest.getTitle());
        bookObj.setStatus(bookRequest.getStatus());
        bookObj.setAuthors(authorRepository.findAllById(bookRequest.getAuthorId()));
        return bookObj;
    }

    public Optional<Book> checkAvailibility(borrowBookRequest borrowBookRequest){
        List<Book> availableBook = bookRepository.findByTitle(borrowBookRequest.getTitle(),availableStatus);
        if(availableBook.size() > 0){
            Book updatedBook = availableBook.get(0);
            updatedBook.setStatus(borrowedStatus);
            insertBorrowData(borrowBookRequest.getUserName(), availableBook.get(0).getBookId());
            return Optional.of(bookRepository.save(updatedBook));
        }
        else
            return Optional.empty();
    }

    public void insertBorrowData(String userName, long bookId){
        Borrow borrowObj = new Borrow();
        borrowObj.setUserName(userName);
        LocalTime localTime = LocalTime.now();
        borrowObj.setDateTime(localTime.toString());
        borrowObj.setBook(bookRepository.findById(bookId).get());
        borrowRepository.save(borrowObj);
    }

    public List<searchResponse> searchByIsbn(String isbn){
        return (bookRepository.findAllByisbn(isbn));
    }

    public List<searchResponse> searchByTitle(String title){
        return (bookRepository.findAllBytitle(title));
    }
}
