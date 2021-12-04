package com.senac.library.api.service.impl;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.dto.CartItemDto;
import com.senac.library.api.model.dto.DashboardDto;
import com.senac.library.api.model.dto.SaleDto;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.CartItemRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.List;
import java.util.stream.Collectors;

import static com.senac.library.api.utils.Utils.getValueDto;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private StoreRepository storeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private BookRepository bookRepository;


    @Override
    public DashboardDto getDashboard() {
        DashboardDto dto = new DashboardDto();
        DateTimeFormatter fmt = DateTimeFormatter
                .ofPattern("dd/MM/yyyy")
                .withResolverStyle(ResolverStyle.STRICT);

        List<Book> books = bookRepository.findAll();
        List<Sale> sales = saleRepository.findAll();
        List<Customer> customers = customerRepository.findAll();

        List<SaleDto> dtos = sales.stream().map(SaleDto::new).collect(Collectors.toList());

        for(Book book: books) {
            dto.setBookWithMaxStorage(new BookDto(book));
            dto.setBookWithLowStorage(new BookDto(book));
            dto.setBestSellerBook(new BookDto(book));

            if(book.getStore().getAvailableBooks() < dto.getBookWithLowStorage().getStore().getAvailableBooks()) {
                dto.setBookWithLowStorage(new BookDto(book));
            }
            if(book.getStore().getAvailableBooks() > dto.getBookWithMaxStorage().getStore().getAvailableBooks()) {
                dto.setBookWithMaxStorage(new BookDto(book));
            }

            if(book.getStore().getSoldBooks() > dto.getBestSellerBook().getStore().getSoldBooks()) {
                dto.setBestSellerBook(new BookDto(book));
            }
        }

        double total = 0.0;
        int totalSalesToday = 0;
        for (SaleDto item : dtos) {
            for (CartItemDto cart : item.getCartItems()) {
                item.setTotal(total += getValueDto(cart.getTypeValue(), cart.getBook().getTypeValues()) * cart.getQuantity());

                if(item.getCreateDt().compareTo(LocalDate.now()) != -1) {
                    totalSalesToday++;
                }
            }
        }

        dto.setWallet(total);
        dto.setTotalBooksSoldToday(totalSalesToday);
        dto.setTotalClients(customers.size());
        dto.setTotalBooks(books.size());


        return dto;
    }
}
