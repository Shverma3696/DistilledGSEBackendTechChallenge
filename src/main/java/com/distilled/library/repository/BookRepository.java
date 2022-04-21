package com.distilled.library.repository;

import com.distilled.library.entity.Book;
import com.distilled.library.model.searchResponse;
import org.hibernate.sql.Select;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book,Long> {

    @Query("SELECT new com.distilled.library.model.searchResponse(b.isbn, b.title, b.status) FROM Book b WHERE b.isbn = :isbn")
    List<searchResponse> findAllByisbn(@Param("isbn") String isbn);

    @Query("SELECT new com.distilled.library.model.searchResponse(b.isbn, b.title, b.status) FROM Book b WHERE b.title = :title")
    List<searchResponse> findAllBytitle(@Param("title") String title);

    @Query("SELECT b FROM Book b WHERE b.status = :bookStatus AND b.title = :title")
    List<Book> findByTitle(@Param("title") String title,@Param("bookStatus") String bookStatus);
}
