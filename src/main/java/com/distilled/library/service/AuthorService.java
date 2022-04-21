package com.distilled.library.service;

import com.distilled.library.entity.Author;
import com.distilled.library.entity.Book;
import com.distilled.library.entity.Borrow;
import com.distilled.library.model.addAuthorRequest;
import com.distilled.library.repository.AuthorRepository;
import com.distilled.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("AuthorService")
public class AuthorService {

        @Autowired
        AuthorRepository authorRepository;

        @Autowired
        BookRepository bookRepository;

        public Optional<Author> addAuthorDetails(addAuthorRequest authorRequest){
            Author authorObj = createAuthorObject(authorRequest);
            return Optional.of(authorRepository.save(authorObj));
        }

        public Author createAuthorObject(addAuthorRequest authorRequest){
            Author authorObj = new Author();
            authorObj.setName(authorRequest.getName());
            authorObj.setBook(bookRepository.findAllById(authorRequest.getBookIds()));
            return authorObj;
        }
}
