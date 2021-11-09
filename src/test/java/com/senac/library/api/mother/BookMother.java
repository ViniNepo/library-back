package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Book;

import java.time.LocalDate;
import java.util.Optional;

import static com.senac.library.api.mother.StoreMother.createStore;
import static com.senac.library.api.mother.TypeValueMother.createTypeValue;

public class BookMother {

    public static Book createBook() {
        Book book = new Book();

        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setCreateDt(LocalDate.now());
        book.setUpdatedDt(LocalDate.now());
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setStore(createStore());
        book.setTypeValues(createTypeValue());

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
        book.get().setTypeValues(createTypeValue());

        book.get().getStore().setBook(book.get());

        return book;
    }
}
