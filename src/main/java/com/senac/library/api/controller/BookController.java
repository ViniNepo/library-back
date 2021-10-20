package com.senac.library.api.controller;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "book")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping
    public ResponseEntity<List<BookDto>> listAllBooks() {
        List<Book> bookList = bookService.findAllBooks();
        return ResponseEntity.ok(bookList.stream().map(BookDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/updated")
    public ResponseEntity<List<BookDto>> listLastUpdatedBooks() {
        List<Book> bookList = bookService.findBooksUpdated();
        return ResponseEntity.ok(bookList.stream().map(BookDto::new).collect(Collectors.toList()));
    }

    @GetMapping("/new")
    public ResponseEntity<List<BookDto>> listNewBooks() {
        List<Book> bookList = bookService.findNewBooks();
        return ResponseEntity.ok(bookList.stream().map(BookDto::new).collect(Collectors.toList()));
    }

    @PostMapping
    public ResponseEntity<Object> createBook(@RequestBody BookRequest bookRequest) {
        Book book = bookService.createNewBook(bookRequest);
        return ResponseEntity.ok(new BookDto(book));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateBook(@PathVariable Long id, @RequestBody Book bookRequest) {
        Book book = bookService.updateById(id, bookRequest);
        return ResponseEntity.ok(new BookDto(book));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
