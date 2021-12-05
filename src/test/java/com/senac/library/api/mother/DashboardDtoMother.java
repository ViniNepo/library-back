package com.senac.library.api.mother;

import com.senac.library.api.model.dto.BookDto;
import com.senac.library.api.model.dto.DashboardDto;

import static com.senac.library.api.mother.BookMother.createBook;

public class DashboardDtoMother {

    public static DashboardDto createdashboard() {
        DashboardDto dto = new DashboardDto();

        dto.setTotalBooksSoldToday(10);
        dto.setBestSellerBook(new BookDto(createBook()));
        dto.setBookWithLowStorage(new BookDto(createBook()));
        dto.setBookWithMaxStorage(new BookDto(createBook()));
        dto.setTotalBooks(10);
        dto.setWallet(10.0);
        dto.setTotalClients(10);

        return dto;
    }
}
