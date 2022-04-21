package com.distilled.library.service;

import com.distilled.library.model.searchResponse;
import com.distilled.library.repository.BorrowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service("BorrowService")
public class BorrowService {

    @Autowired
    BorrowRepository borrowRepository;

    public Set<searchResponse> searchByAuthor(String authorName){
        return borrowRepository.findAllByAuthor(authorName);
    }
}
