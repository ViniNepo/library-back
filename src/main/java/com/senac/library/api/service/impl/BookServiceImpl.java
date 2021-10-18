package com.senac.library.api.service.impl;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Library;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.LibraryRepository;
import com.senac.library.api.repository.TypeValueRepository;
import com.senac.library.api.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE_PRINTED;
import static com.senac.library.api.enuns.BookCategoryEnum.PRINTED;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private LibraryRepository libraryRepository;

    @Autowired
    private TypeValueRepository typeValueRepository;

    @Override
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Book> findBooksUpdated() {

        List<Book> list = bookRepository.findAll();

        return list.stream().filter(x -> x.getUpdatedDt().isAfter(LocalDate.now().minusDays(30))).collect(Collectors.toList());
    }

    @Override
    public List<Book> findNewBooks() {

        List<Book> list = bookRepository.findAll();

        return list.stream().filter(x -> x.getCreateDt().isAfter(LocalDate.now().minusDays(30))).collect(Collectors.toList());
    }

    @Override
    public Book createNewBook(BookRequest request) {

        if (bookRepository.findByAuthorAndName(request.getAuthor(), request.getName()).isPresent()) {
            throw new RuntimeException("This book already exist");
        }

        if (request.getQuantityBook() == 0 && request.getTypeValues().stream().anyMatch(x -> x.getBookCategoryEnum().equals(PRINTED) || x.getBookCategoryEnum().equals(ONLINE_PRINTED))) {
            throw new RuntimeException("This book already to set quantity to sell");
        }

        Library library = new Library(request.getQuantityBook());
        library = libraryRepository.save(library);

        List<TypeValue> typeValuesList = new ArrayList<>();
        for (TypeValue x: request.getTypeValues()) {
            x = typeValueRepository.save(x);
            typeValuesList.add(x);
        }

        Book book = new Book(request);
        book.setLibrary(library);
        book.setTypeValue(typeValuesList);

        return bookRepository.save(book);
    }

    @Override
    public Book updateById(BookRequest request) {

        Optional<Book> book = bookRepository.findById(request.getId());

        if (book.isPresent()) {
            libraryRepository.deleteById(book.get().getLibrary().getId());

            book.get().getTypeValue().forEach(x -> typeValueRepository.deleteById(x.getId()));

            bookRepository.deleteById(book.get().getId());

            return book.get();
        }
        throw new RuntimeException("dwsd");
    }

    @Override
    public void deleteById(Long id) {

        Optional<Book> book = bookRepository.findById(id);

        if (book.isPresent()) {
            libraryRepository.deleteById(book.get().getLibrary().getId());

            book.get().getTypeValue().forEach(x -> typeValueRepository.deleteById(x.getId()));

            bookRepository.deleteById(book.get().getId());
        }
    }
}