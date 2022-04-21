package com.distilled.library.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class searchResponse {
    String isbn;
    String title;
    String status;
}
