package com.senac.library.api.mother;

import com.senac.library.api.model.entities.Store;

public class StoreMother {

    public static Store createStore() {
        Store store = new Store();

        store.setId(1L);
        store.setSoldBooks(0);
        store.setAvailableBooks(10);

        return store;
    }
}
