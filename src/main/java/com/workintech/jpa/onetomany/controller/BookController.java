package com.workintech.jpa.onetomany.controller;

import com.workintech.jpa.onetomany.entity.Author;
import com.workintech.jpa.onetomany.entity.Book;
import com.workintech.jpa.onetomany.entity.Category;
import com.workintech.jpa.onetomany.service.AuthorService;
import com.workintech.jpa.onetomany.service.BookService;
import com.workintech.jpa.onetomany.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {
    private BookService bookService;
    private AuthorService authorService;
    private CategoryService categoryService;

    @Autowired
    public BookController(AuthorService authorService, BookService bookService, CategoryService categoryService) {
        this.authorService = authorService;
        this.bookService = bookService;
        this.categoryService = categoryService;
    }

    @GetMapping("/")
    public List<Book> findAll() {
        return bookService.find();
    }

    @GetMapping("/{id}")
    public Book findById(@PathVariable int id) {
        return bookService.findById(id);
    }

    @PostMapping("/")
    public Book save(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PostMapping("/{categoryId}")
    public Book saveByCategoryId(@RequestBody Book book, @PathVariable int categoryId){
        Category category = categoryService.findById(categoryId);
        if(category != null){
            category.getBooks().add(book);
            book.setCategory(category);
            return bookService.save(book);
        }
        return null;
    }

    @PostMapping("/{categoryId}/{authorId}")
    public Book saveByCategoryAndAuthor(@RequestBody Book book, @PathVariable int categoryId, @PathVariable int authorId ){
        Category category = categoryService.findById(categoryId);
        Author author = authorService.findById(authorId);
        if(category != null && author != null) {
            category.getBooks().add(book);
            author.getBooks().add(book);
            book.setCategory(category);
            book.setAuthor(author);
            return bookService.save(book);
        }
        return null;
    }
    @PutMapping("/{id}")
    public Book update(@RequestBody Book book, @PathVariable int id) {
        Book foundBook = bookService.findById(id);
        if (foundBook != null) {
            book.setId(id);
            return bookService.save(book);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public Book delete(@PathVariable int id) {
        Book book = bookService.findById(id);
        bookService.delete(book);
        return book;
    }
}