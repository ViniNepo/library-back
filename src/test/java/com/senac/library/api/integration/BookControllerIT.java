package com.senac.library.api.integration;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.repository.TypeValueRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.mother.BookMother.createUpdateBook;
import static com.senac.library.api.mother.BookRequestMother.createBookRequest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
class BookControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TypeValueRepository typeValueRepository;

    @Autowired
    private StoreRepository storeRepository;

    static String BOOK_API = "/book";

    @Test
    public void listAllTest() {

        createBookList();

        List<Book> bookDtoList = testRestTemplate.exchange(BOOK_API, HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {}).getBody();

        assertThat(bookDtoList).hasSize(1);
        assertThat(bookDtoList.get(0).getId()).isNotNull();
    }

    @Test
    public void listAllUpdatedTest() {

        createBookList();

        List<Book> bookDtoList = testRestTemplate.exchange(BOOK_API.concat("/updated"), HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {}).getBody();

        assertThat(bookDtoList).hasSize(1);
        assertThat(bookDtoList.get(0).getId()).isNotNull();
    }

    @Test
    public void listAllNewTest() {

        createBookList();

        List<Book> bookDtoList = testRestTemplate.exchange(BOOK_API.concat("/new"), HttpMethod.GET, null, new ParameterizedTypeReference<List<Book>>() {}).getBody();

        assertThat(bookDtoList).hasSize(1);
        assertThat(bookDtoList.get(0).getId()).isNotNull();
    }

    @Test
    public void getByIdTest() {

        createBookList();

        BookDto bookDto = testRestTemplate.exchange(BOOK_API.concat("/1"), HttpMethod.GET, null, new ParameterizedTypeReference<BookDto>() {}).getBody();

        assertThat(bookDto).isNotNull();
    }

    @Test
    public void createBookTest() {

        Book book = testRestTemplate.postForEntity(BOOK_API, createBookRequest(), Book.class).getBody();

        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void updateBookTest() {
        Book updatedBook = createUpdateBook();

        Book book = testRestTemplate.postForEntity(BOOK_API, updatedBook, Book.class).getBody();

        assertThat(book.getId()).isNotNull();
        assertThat(book.getTitle()).isEqualTo(updatedBook.getTitle());
    }

    private void createBookList() {
        TypeValue t1 = new TypeValue(null, 10.0, ONLINE);
        typeValueRepository.saveAll(Arrays.asList(t1));

        Store s1 = new Store(null, 1, 10);
        storeRepository.saveAll(Arrays.asList(s1));

        Book b1 = new Book(null, "title1", "desc", "content", "ator", "editor", LocalDate.now(), 1, "isbn", "image", "gender");
        b1.setActivate(true);
        b1.setCreateDt(LocalDate.now());
        b1.setUpdatedDt(LocalDate.now());

        b1.getTypeValues().addAll(Arrays.asList(t1));

        b1.setStore(s1);
        bookRepository.saveAll(Arrays.asList(b1));
    }
}