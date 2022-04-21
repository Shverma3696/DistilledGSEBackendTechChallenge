package com.distilled.library.model;

import lombok.Data;

import java.util.List;

@Data
public class addAuthorRequest {
    String name;
    List<Long> bookIds;
}
