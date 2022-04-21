package com.distilled.library.entity;

import lombok.*;

import javax.persistence.*;

import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "book")
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "book_id")
    private long bookId;

    @Column(name = "isbn", nullable = false)
    private String isbn;

    @Column(name = "title")
    private String title;

    @Column(name = "status")
    private String status;


    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="bookauthor",
            joinColumns=@JoinColumn(name = "book_id",referencedColumnName = "book_id"),
            inverseJoinColumns=@JoinColumn(name = "author_id",referencedColumnName = "author_id"))
    private List<Author> authors;

    @OneToOne(mappedBy = "book")
    private Borrow borrow;
}
