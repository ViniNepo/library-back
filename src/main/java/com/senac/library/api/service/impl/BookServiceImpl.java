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

import static java.time.LocalDateTime.now;


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
        return bookRepository.findAllByUpdateDtBefore(now().minusDays(30L));
    }

    @Override
    public List<Book> findNewBooks() {
        return bookRepository.findAllByCreateDtIsBefore(now().minusDays(30L));
    }

    @Override
    public BookDto createNewBook(BookRequest request) {

        if(bookRepository.findByAuthorAndTitle(request.getAuthor(), request.getTitle()).isPresent()) {
            throw new RuntimeException();
        }

        List<TypeValue> typeValueList = new ArrayList<>();
        Store store = new Store();
        Book book = new Book(request);

        request.getTypeValues().forEach(x -> typeValueList.add(typeValueRepository.save(x)));

        store.setAvailableBooks(request.getQuantityBook());
        store.setSoldBooks(0);
        store = storeRepository.save(store);

        book.setStore(store);
        book.setTypeValues(typeValueList);
        book.setCreateDt(now());
        return new BookDto(bookRepository.save(book));
    }

    @Override
    public BookDto updateById(BookDto bookDto) {

        if(bookRepository.findByAuthorAndTitle(bookDto.getAuthor(), bookDto.getTitle()).isPresent()) {
            throw new RuntimeException();
        }

        Optional<Book> book = bookRepository.findById(bookDto.getId());

        if(book.isEmpty()) {
            throw new RuntimeException();
        }

        List<TypeValue> typeValueList = new ArrayList<>();
        Store store = new Store();

//        request.getTypeValues().forEach(x -> typeValueList.add(typeValueRepository.save(x)));

        store = storeRepository.save(store);

        book.get().setStore(store);
        book.get().setTypeValues(typeValueList);
        book.get().setUpdateDt(now());
        return new BookDto(bookRepository.save(book.get()));
    }

    @Override
    public void deleteById(Long id) {
        Optional<Book> book = bookRepository.findById(id);

        if(book.isEmpty()) {
            throw new RuntimeException();
        }

        bookRepository.deleteById(id);
        storeRepository.deleteById(book.get().getStore().getId());
        book.get().getTypeValues().forEach(x -> typeValueRepository.deleteById(x.getId()));
    }
}
