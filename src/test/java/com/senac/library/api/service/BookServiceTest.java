package com.senac.library.api.service;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.model.request.BookRequest;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.repository.TypeValueRepository;
import com.senac.library.api.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.enuns.BookCategoryEnum.PRINTED;
import static com.senac.library.api.mother.BookMother.createBook;
import static com.senac.library.api.mother.BookMother.createBookList;
import static com.senac.library.api.mother.BookMother.createOptionalBook;
import static com.senac.library.api.mother.BookMother.createUpdateBook;
import static com.senac.library.api.mother.BookRequestMother.createBookRequest;
import static com.senac.library.api.mother.StoreMother.createStore;
import static com.senac.library.api.mother.TypeValueMother.createTypeValue;
import static java.time.LocalDate.now;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class BookServiceTest {

    @InjectMocks
    private BookServiceImpl bookService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private StoreRepository storeRepository;

    @Mock
    private TypeValueRepository typeValueRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    void findAllBooksTeste() {
        when(bookRepository.findAll()).thenReturn(createBookList());

        List<Book> bookList = bookService.findAllBooks();

        verify(bookRepository, times(1)).findAll();

        assertThat(bookList.size()).isEqualTo(3);
    }

    @Test
    void findBooksUpdatedTest() {
        when(bookRepository.findAllByUpdatedDtBetweenAndActivateIsTrue(now().minusDays(30L), now())).thenReturn(createBookList());

        List<Book> bookList = bookService.findBooksUpdated();

        verify(bookRepository, times(1)).findAllByUpdatedDtBetweenAndActivateIsTrue(now().minusDays(30L), now());

        assertThat(bookList.size()).isEqualTo(3);
    }

    @Test
    void findNewBooks() {
        when(bookRepository.findAllByCreateDtIsBetweenAndActivateIsTrue(now().minusDays(30L), now())).thenReturn(createBookList());

        List<Book> bookList = bookService.findNewBooks();

        verify(bookRepository, times(1)).findAllByCreateDtIsBetweenAndActivateIsTrue(now().minusDays(30L), now());

        assertThat(bookList.size()).isEqualTo(3);
    }

    @Test
    void createNewBookTest() {

        BookRequest request = createBookRequest();

        when(bookRepository.findByAuthorAndTitleAndActivateIsTrue(anyString(), anyString())).thenReturn(Optional.empty());
        when(typeValueRepository.save(any(TypeValue.class))).thenReturn(createTypeValue());
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(bookRepository.save(any(Book.class))).thenReturn(createBook());

        BookDto book = bookService.createNewBook(request);

        verify(bookRepository, times(1)).findByAuthorAndTitleAndActivateIsTrue(anyString(), anyString());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(typeValueRepository, times(1)).save(any(TypeValue.class));
        verify(storeRepository, times(1)).save(any(Store.class));

        assertThat(book.getId()).isNotNull();
        assertThat(book.getStore().getId()).isNotNull();
        assertThat(book.getTypeValues().get(0).getId()).isNotNull();
        assertThat(book.getTypeValues().get(0).getBookCategoryEnum()).isEqualTo(request.getTypeValues().get(0).getBookCategoryEnum());
    }

    @Test
    void cannotCreateNewBookTest() {

        BookRequest request = createBookRequest();

        when(bookRepository.findByAuthorAndTitleAndActivateIsTrue(anyString(), anyString())).thenReturn(createOptionalBook());

        assertThatThrownBy(() -> bookService.createNewBook(request))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("book already exist");

        verify(bookRepository, times(1)).findByAuthorAndTitleAndActivateIsTrue(anyString(), anyString());
        verify(bookRepository, times(0)).save(any(Book.class));
        verify(typeValueRepository, times(0)).save(any(TypeValue.class));
        verify(storeRepository, times(0)).save(any(Store.class));
    }

    @Test
    void updateByIdTest() {
        TypeValue typeValue = createTypeValue();
        typeValue.setBookCategoryEnum(PRINTED);
        TypeValue typeValue2 = createTypeValue();
        typeValue2.setBookCategoryEnum(ONLINE);

        when(bookRepository.findById(anyLong())).thenReturn(createOptionalBook());
        when(typeValueRepository.save(any(TypeValue.class))).thenReturn(createTypeValue(), typeValue, typeValue2);
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(bookRepository.save(any(Book.class))).thenReturn(createUpdateBook());

        BookDto book = bookService.updateByBook(createUpdateBook());

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(1)).save(any(Book.class));
        verify(typeValueRepository, times(3)).save(any(TypeValue.class));
        verify(storeRepository, times(1)).save(any(Store.class));

        assertThat(book.getTypeValues().size()).isEqualTo(3);
    }

    @Test
    void errorUpdateByIdWithTheSameTypeValueTest() {
        TypeValue typeValue = createTypeValue();
        typeValue.setBookCategoryEnum(PRINTED);
        TypeValue typeValue2 = createTypeValue();
        typeValue2.setBookCategoryEnum(ONLINE);

        Book b = createUpdateBook();
        b.getTypeValues().get(2).setBookCategoryEnum(PRINTED);

        when(bookRepository.findById(anyLong())).thenReturn(createOptionalBook());
        when(typeValueRepository.save(any(TypeValue.class))).thenReturn(createTypeValue(), typeValue, typeValue);
        when(storeRepository.save(any(Store.class))).thenReturn(createStore());
        when(bookRepository.save(any(Book.class))).thenReturn(b);

        assertThatThrownBy(() -> bookService.updateByBook(b))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("this type already exist");

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(0)).save(any(Book.class));
        verify(typeValueRepository, times(2)).save(any(TypeValue.class));
        verify(storeRepository, times(0)).save(any(Store.class));

    }

    @Test
    void errorUpdateByIdTest() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.updateByBook(createUpdateBook()))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("book not found");

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(0)).save(any(Book.class));
        verify(typeValueRepository, times(0)).save(any(TypeValue.class));
        verify(storeRepository, times(0)).save(any(Store.class));

    }

    @Test
    void deleteByIdTest() {
        when(bookRepository.findById(anyLong())).thenReturn(createOptionalBook());

        bookService.deleteById(1L);

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void cannotDeleteByIdTest() {
        when(bookRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookService.deleteById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("book not found");

        verify(bookRepository, times(1)).findById(anyLong());
        verify(bookRepository, times(0)).deleteById(anyLong());
        verify(storeRepository, times(0)).deleteById(anyLong());
        verify(typeValueRepository, times(0)).deleteById(anyLong());
    }

    @Test
    void getBookByIdTest() {
        when(bookRepository.findById(anyLong())).thenReturn(createOptionalBook());

        Optional<Book> book = bookService.getBookById(1L);

        verify(bookRepository, times(1)).findById(anyLong());

        assertThat(book).isPresent();
    }
}