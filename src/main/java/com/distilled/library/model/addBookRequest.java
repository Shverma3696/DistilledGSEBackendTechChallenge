package com.distilled.library.model;

import lombok.Data;

import java.util.List;

@Data
public class addBookRequest {
        String isbn;
        String title;
        String status;
        List<Long> authorId;
}
