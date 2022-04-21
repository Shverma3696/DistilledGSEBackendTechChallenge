package com.distilled.library.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "borrow")
public class Borrow implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "borrow_id")
    private long borrowId;

    @Column(name = "username")
    private String userName;

    @Column(name = "datetime")
    private String dateTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id", referencedColumnName = "book_id")
    private Book book;
}
