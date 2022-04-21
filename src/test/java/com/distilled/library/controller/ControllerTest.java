package com.distilled.library.controller;

import com.distilled.library.entity.Author;
import com.distilled.library.entity.Book;
import com.distilled.library.model.addAuthorRequest;
import com.distilled.library.model.addBookRequest;
import com.distilled.library.model.borrowBookRequest;
import com.distilled.library.service.AuthorService;
import com.distilled.library.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class ControllerTest {

    @InjectMocks
    Controller controller;

    @Mock
    BookService bookService;

    @Mock
    AuthorService authorService;

    @Test
    public void testAddBookDetailsSuccess(){
        final addBookRequest bookRequest = mock(addBookRequest.class);
        final Book book = mock(Book.class);
        final Optional<Book> optional = Optional.of(book);

        when(bookService.addBookDetails(bookRequest)).thenReturn(optional);

        final ResponseEntity<Long> responseEntity = controller.addBookData(bookRequest);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testAddAuthorDetailsSuccess(){
        final addAuthorRequest authorRequest = mock(addAuthorRequest.class);
        final Author author = mock(Author.class);
        final Optional<Author> optional = Optional.of(author);

        when(authorService.addAuthorDetails(authorRequest)).thenReturn(optional);

        final ResponseEntity<Long> responseEntity = controller.addAuthorData(authorRequest);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testBorrowBookSuccess(){
        final borrowBookRequest borrowbookRequest = mock(borrowBookRequest.class);
        final Book book = mock(Book.class);
        final Optional<Book> optional = Optional.of(book);

        when(bookService.checkAvailibility(borrowbookRequest)).thenReturn(optional);

        final ResponseEntity<Long> responseEntity = controller.borrowBook(borrowbookRequest);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
