package com.senac.library.api.service.impl;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.repository.TypeValueRepository;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.senac.library.api.excepitions.BookException.bookException;
import static java.time.LocalDate.now;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private TypeValueRepository typeValueRepository;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksUpdated() {
        List<Book> books = bookRepository.findAllByUpdatedDtBetweenAndActivateIsTrue(now().minusDays(30L), now());
        return books.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public List<Book> findNewBooks() {
        List<Book> books = bookRepository.findAllByCreateDtIsBetweenAndActivateIsTrue(now().minusDays(30L), now());
        return books.stream().limit(10).collect(Collectors.toList());
    }

    @Override
    public BookDto createNewBook(BookRequest request) {

        if(bookRepository.findByAuthorAndTitleAndActivateIsTrue(request.getAuthor(), request.getTitle()).isPresent()) {
            bookException("book already exist");
        }

        List<TypeValue> typeValueList = new ArrayList<>();
        Store store = new Store();
        Book book = new Book(request);
        book.setActivate(true);

        request.getTypeValues().forEach(x -> typeValueList.add(typeValueRepository.save(x)));

        store.setAvailableBooks(request.getQuantityBook());
        store.setSoldBooks(0);
        store = storeRepository.save(store);

        book.setStore(store);
        book.setTypeValues(typeValueList);
        book.setCreateDt(now());
        book.setUpdatedDt(now());

        return new BookDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateByBook(Book bookRequest) {

        Optional<Book> book = bookRepository.findById(bookRequest.getId());

        if(book.isEmpty()) {
            bookException("book not found");
        }

        List<TypeValue> typeValues = new ArrayList<>();
        for(TypeValue x: bookRequest.getTypeValues()) {
            if (checkTypes(x, typeValues)) {
                typeValues.add(typeValueRepository.save(x));
            } else {
                bookException("this type already exist");
            }
        }

        book.get().setStore(storeRepository.save(bookRequest.getStore()));
        book.get().setTypeValues(typeValues);
        book.get().setUpdatedDt(now());
        return new BookDto(bookRepository.save(book.get()));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isEmpty()) {
            bookException("book not found");
        }

        book.get().setActivate(false);
        bookRepository.save(book.get());
    }

    @Override
    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    private boolean checkTypes(TypeValue typeValue, List<TypeValue> typeValues) {
        return typeValues.stream().noneMatch(x -> x.getBookCategoryEnum().equals(typeValue.getBookCategoryEnum()));
    }
}
