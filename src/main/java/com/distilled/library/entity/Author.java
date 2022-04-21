package com.distilled.library.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity
@Table(name = "author")
public class Author implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "author_id")
    private long authorId;

    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy="authors")
    private List<Book> book;
}
