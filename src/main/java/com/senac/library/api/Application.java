package com.senac.library.api;

import com.senac.library.api.model.entities.Address;
import com.senac.library.api.model.entities.Book;
import com.senac.library.api.model.entities.CartItem;
import com.senac.library.api.model.entities.Contact;
import com.senac.library.api.model.entities.CreditCard;
import com.senac.library.api.model.entities.Customer;
import com.senac.library.api.model.entities.Sale;
import com.senac.library.api.model.entities.Store;
import com.senac.library.api.model.entities.TypeValue;
import com.senac.library.api.repository.AddressRepository;
import com.senac.library.api.repository.BookRepository;
import com.senac.library.api.repository.CartItemRepository;
import com.senac.library.api.repository.ContactRepository;
import com.senac.library.api.repository.CreditCardRepository;
import com.senac.library.api.repository.CustomerRepository;
import com.senac.library.api.repository.SaleRepository;
import com.senac.library.api.repository.StoreRepository;
import com.senac.library.api.repository.TypeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;

import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE;
import static com.senac.library.api.enuns.BookCategoryEnum.ONLINE_PRINTED;
import static com.senac.library.api.enuns.BookCategoryEnum.PRINTED;
import static com.senac.library.api.enuns.TypeContactEnum.COMMERCIAL;
import static com.senac.library.api.enuns.TypeContactEnum.HOME;
import static com.senac.library.api.enuns.TypeContactEnum.PHONE;

@SpringBootApplication
public class Application implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Autowired
	private TypeValueRepository typeValueRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private StoreRepository storeRepository;

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private CreditCardRepository creditCardRepository;

	@Autowired
	private AddressRepository addressRepository;

	@Autowired
	private ContactRepository contactRepository;

	@Autowired
	private SaleRepository saleRepository;

	@Autowired
	private CartItemRepository cartItemRepository;

	@Override
	public void run(String... args) throws Exception {

//		TypeValue t1 = new TypeValue(null, 10.0, ONLINE);
//		TypeValue t2 = new TypeValue(null, 10.0, PRINTED);
//		TypeValue t3 = new TypeValue(null, 10.0, ONLINE_PRINTED);
//		typeValueRepository.saveAll(Arrays.asList(t1, t2, t3));
//
//		Store s1 = new Store(null, 1, 10);
//		Store s2 = new Store(null, 50, 100);
//		Store s3 = new Store(null, 0, 0);
//		storeRepository.saveAll(Arrays.asList(s1, s2, s3));
//
//		Book b1 = new Book(null, "title1", "desc", "content", "ator", "editor", LocalDate.now(), 1, "isbn", "image", "gender");
//		Book b2 = new Book(null, "title2", "desc", "content", "ator", "editor", LocalDate.now(), 1, "isbn", "image", "gender");
//		Book b3 = new Book(null, "title3", "desc", "content", "ator", "editor", LocalDate.now(), 1, "isbn", "image", "gender");
//
//		b1.getTypeValues().addAll(Arrays.asList(t1, t2));
//		b2.getTypeValues().addAll(Arrays.asList(t1, t2, t3));
//		b3.getTypeValues().add(t3);
//
//		b1.setStore(s1);
//		b2.setStore(s2);
//		b3.setStore(s3);
//		bookRepository.saveAll(Arrays.asList(b1, b2, b3));
//
//		Address a1 = new Address(null, "street", "12A", "complement", "state", "city", "country", "zip", true);
//		Address a2 = new Address(null, "avenue", "12A", "complement", "state", "city", "country", "zip", false);
//		Address a3 = new Address(null, "street", "50", "complement", "state", "city", "country", "zip", true);
//		addressRepository.saveAll(Arrays.asList(a1, a2, a3));
//
//		Contact c1 = new Contact(null, HOME, "21243432");
//		Contact c2 = new Contact(null, COMMERCIAL, "21243432");
//		Contact c3 = new Contact(null, PHONE, "21243432");
//		contactRepository.saveAll(Arrays.asList(c1, c2, c3));
//
//		CreditCard card1 = new CreditCard(null, "name", "cpf", "number", "validateDate", "securityNumber");
//		CreditCard card2 = new CreditCard(null, "name2", "cpf", "number", "validateDate", "securityNumber");
//		CreditCard card3 = new CreditCard(null, "name3", "cpf", "number", "validateDate", "securityNumber");
//		creditCardRepository.saveAll(Arrays.asList(card1, card2, card3));
//
//		Customer customer1 = new Customer(null, "123", "cpf", "email");
//		Customer customer2 = new Customer(null, "321", "cpf", "email");
//		Customer customer3 = new Customer(null, "456", "cpf", "email");
//
//		customer1.setAddresses(Arrays.asList(a1));
//		customer2.setAddresses(Arrays.asList(a1, a2));
//		customer3.setAddresses(Arrays.asList(a2, a3));
//
//		customer1.setContacts(Arrays.asList(c1, c3));
//		customer2.setContacts(Arrays.asList(c2, c3));
//		customer3.setContacts(Arrays.asList(c1, c2, c3));
//
//		customer1.setCreditCards(Arrays.asList(card1));
//		customer2.setCreditCards(Arrays.asList(card1, card3));
//		customer3.setCreditCards(Arrays.asList(card1, card2, card3));
//		customerRepository.saveAll(Arrays.asList(customer1, customer2, customer3));
//
//		Sale sale1 = new Sale(null, true);
//		Sale sale2 = new Sale(null, true);
//		Sale sale3 = new Sale(null, false);
//
//		sale1.setCustomer(customer1);
//		sale2.setCustomer(customer2);
//		sale3.setCustomer(customer3);
//		saleRepository.saveAll(Arrays.asList(sale1, sale2, sale3));
//
//		CartItem item1 = new CartItem(sale1, b1, 2, ONLINE);
//		CartItem item2 = new CartItem(sale1, b2, 3, PRINTED);
//		CartItem item3 = new CartItem(sale1, b3, 10, ONLINE_PRINTED);
//		CartItem item4 = new CartItem(sale2, b1, 2, PRINTED);
//		CartItem item5 = new CartItem(sale3, b1, 2, ONLINE);
//		CartItem item6 = new CartItem(sale3, b3, 3, ONLINE);
//		cartItemRepository.saveAll(Arrays.asList(item1, item2, item3, item4, item5, item6));

	}
}
