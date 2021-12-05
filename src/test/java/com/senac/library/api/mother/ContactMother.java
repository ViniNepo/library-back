package com.senac.library.api.mother;


import com.senac.library.api.model.entities.Contact;

import java.util.ArrayList;
import java.util.List;

import static com.senac.library.api.enuns.TypeContactEnum.PHONE;

public class ContactMother {

    public static Contact createContact() {
        Contact contact = new Contact();

        contact.setId(1L);
        contact.setNumber("912345678");
        contact.setType(PHONE);

        return contact;
    }

    public static List<Contact> createContacts() {
        List<Contact> contacts = new ArrayList<>();

        contacts.add(createContact());

        return contacts;
    }
}
