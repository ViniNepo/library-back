package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Address;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static com.senac.library.api.mother.AddressMother.createAddress;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("dev")
@DataJpaTest
class AddressRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @MockBean
    AddressRepository addressRepository;

    @Test
    public void findAddressTest() {
        Address address = createAddress();
        entityManager.persist(address);

        List<Address> addresses = addressRepository.findAll();

        assertThat(addresses.size() == 1).isTrue();
    }

    @Test
    public void saveAddressTest() {
        Address address = createAddress();

        Address addresses = addressRepository.save(address);

        assertThat(addresses.getId()).isNotNull();
    }

    @Test
    public void deleteAddressTest() {
        Address address = createAddress();
        entityManager.persist(address);

        Address searchAddress = entityManager.find(Address.class, address.getId());
        this.addressRepository.delete(searchAddress);

        Address deletedBook = entityManager.find(Address.class, address.getId());

        assertThat(deletedBook).isNull();
    }
}