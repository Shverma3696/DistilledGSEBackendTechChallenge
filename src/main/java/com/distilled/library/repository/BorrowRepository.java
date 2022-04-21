package com.distilled.library.repository;

import com.distilled.library.entity.Borrow;
import com.distilled.library.model.searchResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface BorrowRepository extends JpaRepository<Borrow,Long> {

    @Query("SELECT new com.distilled.library.model.searchResponse(b.isbn, b.title, b.status) FROM Book b,Author a  WHERE a.name = :author")
    Set<searchResponse> findAllByAuthor(@Param("author") String author);
}
