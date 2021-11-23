package com.senac.library.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.service.BookService;
import com.senac.library.api.service.LoginService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.senac.library.api.mother.BookMother.createBook;
import static com.senac.library.api.mother.BookMother.createOptionalBook;
import static com.senac.library.api.mother.BookRequestMother.createBookRequest;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(controllers = BookController.class)
@ActiveProfiles("dev")
@AutoConfigureMockMvc

class BookControllerTest {

    static String BOOK_API = "/book";

    @Autowired
    private MockMvc mvc;

    @MockBean
    private LoginService loginService;

    @MockBean
    private BookService service;

    @Test
    public void listAllTest() throws Exception {

        List<Book> books = new ArrayList<>();
        books.add(createBook());
        given(service.findAllBooks()).willReturn(books);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void listAllUpdatedTest() throws Exception {

        List<Book> books = new ArrayList<>();
        books.add(createBook());
        given(service.findBooksUpdated()).willReturn(books);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/updated"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void listAllNewTest() throws Exception {

        List<Book> books = new ArrayList<>();
        books.add(createBook());
        given(service.findNewBooks()).willReturn(books);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/new"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void getByIdTest() throws Exception {

        Optional<Book> book = createOptionalBook();
        given(service.getBookById((anyLong()))).willReturn(book);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .get(BOOK_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);
        mvc.perform(request)
                .andExpect(status().isOk());
    }

    @Test
    public void createBookTest() throws Exception {

        BookRequest bookRequest = createBookRequest();
        BookDto book = new BookDto(createBook());
        given(service.createNewBook((any(BookRequest.class)))).willReturn(book);
        String json = new ObjectMapper().writeValueAsString(bookRequest);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(BOOK_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty())
                .andExpect(jsonPath("title").value(bookRequest.getTitle()))
                .andExpect(jsonPath("author").value(bookRequest.getAuthor()))
                .andExpect(jsonPath("isbn").value(bookRequest.getIsbn()));
    }

    @Test
    public void updateBookTest() throws Exception {

        BookDto book = new BookDto(createBook());
        given(service.updateByBook((any(Book.class)))).willReturn(book);
        String json = new ObjectMapper().writeValueAsString(book);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .put(BOOK_API)
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON)
                .content(json);

        mvc.perform(request)
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    @Test
    public void deleteBookTest() throws Exception {

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .delete(BOOK_API.concat("/1"))
                .contentType(APPLICATION_JSON)
                .accept(APPLICATION_JSON);

        mvc.perform(request)
                .andExpect(status().isNoContent());
    }
}