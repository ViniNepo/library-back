package com.senac.library.api.mother;

import com.senac.library.api.model.request.BookRequest;

import java.time.LocalDate;

import static com.senac.library.api.mother.TypeValueMother.createTypeValueList;

public class BookRequestMother {

    public static BookRequest createBookRequest() {
        BookRequest book = new BookRequest();

        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setPublishDate(LocalDate.now().minusYears(5));
        book.setTypeValues(createTypeValueList());
        book.setQuantityBook(10);

        return book;
    }

    public static BookRequest createBookRequestController() {
        BookRequest book = new BookRequest();

        book.setId(1L);
        book.setTitle("title");
        book.setAuthor("eu mesmo");
        book.setEditor("eu mesmo");
        book.setContent("bla bla bla");
        book.setDescription("description");
        book.setGender("gender");
        book.setIsbn("123");
        book.setNumberPages(10);
        book.setTypeValues(createTypeValueList());
        book.setQuantityBook(10);

        return book;
    }
}
