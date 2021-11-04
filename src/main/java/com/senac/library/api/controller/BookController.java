package com.senac.library.api.controller;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(value="id") Long id) {
        Optional<Book> book = bookService.getBookById(id);

        if(book.isEmpty()){
            throw new RuntimeException();
        }
        return ResponseEntity.ok(new BookDto(book.get()));
    }

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
    public ResponseEntity<Object> createBook(@RequestBody BookRequest bookRequest) throws Exception {
        return ResponseEntity.ok(bookService.createNewBook(bookRequest));
    }

    @PutMapping
    public ResponseEntity<Object> updateBook(@RequestBody BookDto bookRequest) {
        return ResponseEntity.ok(bookService.updateById(bookRequest));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
