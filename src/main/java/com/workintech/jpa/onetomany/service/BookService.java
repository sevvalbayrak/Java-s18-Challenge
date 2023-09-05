package com.workintech.jpa.onetomany.service;

import com.workintech.jpa.onetomany.entity.Book;

import java.util.List;

public interface BookService {
    List<Book> find();
    Book findById(int id);
    Book save(Book book);
    void delete(Book book);
}