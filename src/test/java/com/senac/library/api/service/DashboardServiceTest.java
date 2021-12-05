package com.senac.library.api.service;

import com.senac.library.api.model.entities.Book;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.service.impl.DashboardServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static com.senac.library.api.mother.BookMother.createBook;
import static com.senac.library.api.mother.BookMother.createBookList;
import static com.senac.library.api.mother.CustomerMother.createCustomerList;
import static com.senac.library.api.mother.SaleMother.createListSale;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class DashboardServiceTest {

    @InjectMocks
    private DashboardServiceImpl dashboardService;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private SaleRepository saleRepository;

    @Mock
    private CustomerRepository customerRepository;

    @BeforeEach
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void getDashboardServiceTest() {
        List<Book> bookList = createBookList();

        Book book = createBook();
        book.getStore().setSoldBooks(200);
        book.getStore().setAvailableBooks(2);

        Book book2 = createBook();
        book2.getStore().setSoldBooks(20);
        book2.getStore().setAvailableBooks(300);

        bookList.add(book);
        bookList.add(book2);

        when(bookRepository.findAll()).thenReturn(bookList);
        when(saleRepository.findAll()).thenReturn(createListSale());
        when(customerRepository.findAll()).thenReturn(createCustomerList());

        dashboardService.getDashboard();

        verify(bookRepository, times(1)).findAll();
        verify(saleRepository, times(1)).findAll();
        verify(customerRepository, times(1)).findAll();
    }


}