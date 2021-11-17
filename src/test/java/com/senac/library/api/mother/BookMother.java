package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Book;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.mother.StoreMother.createStore;
import static com.senac.library.api.mother.TypeValueMother.createTypeValueList;
import static com.senac.library.api.mother.TypeValueMother.createTypeValueListOnlineBook;

public class BookMother {

    public static Book createBook() {
        Book book = new Book();

        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setCreateDt(LocalDate.now().minusMonths(2));
        book.setUpdatedDt(LocalDate.now().minusMonths(2));
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setStore(createStore());
        book.setTypeValues(createTypeValueList());

        return book;
    }

    public static Book createBookOnlineType() {
        Book book = new Book();

        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setCreateDt(LocalDate.now().minusMonths(2));
        book.setUpdatedDt(LocalDate.now().minusMonths(2));
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setStore(createStore());
        book.setTypeValues(createTypeValueListOnlineBook());

        return book;
    }

    public static Book createBookUpdated() {
        Book book = new Book();

        book.setId(2L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setCreateDt(LocalDate.now().minusMonths(2));
        book.setUpdatedDt(LocalDate.now().minusDays(10));
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setStore(createStore());
        book.setTypeValues(createTypeValueList());

        book.getStore().setBook(book);

        return book;
    }

    public static Book createBookNew() {
        Book book = new Book();

        book.setId(3L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setCreateDt(LocalDate.now().minusDays(10));
        book.setUpdatedDt(LocalDate.now().minusMonths(2));
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setStore(createStore());
        book.setTypeValues(createTypeValueList());

        book.getStore().setBook(book);

        return book;
    }

    public static Optional<Book> createOptionalBook() {
        Optional<Book> book = Optional.of(new Book());

        book.get().setId(1L);
        book.get().setTitle("title");
        book.get().setAuthor("eu mesmo");
        book.get().setEditor("eu mesmo");
        book.get().setContent("bla bla bla");
        book.get().setDescription("description");
        book.get().setCreateDt(LocalDate.now());
        book.get().setUpdatedDt(LocalDate.now());
        book.get().setGender("gender");
        book.get().setIsbn("123");
        book.get().setNumberPages(10);
        book.get().setPublishDate(LocalDate.now().minusYears(5));
        book.get().setStore(createStore());
        book.get().setTypeValues(createTypeValueList());

        book.get().getStore().setBook(book.get());

        return book;
    }

    public static List<Book> createBookList() {
        List<Book> bookList = new ArrayList<>();

        bookList.add(createBook());
        bookList.add(createBookNew());
        bookList.add(createBookUpdated());

        return bookList;
    }
}
