package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByAuthorAndTitleAndActivateIsTrue(String author, String name);
    List<Book> findAllByCreateDtIsBetweenAndActivateIsTrue(LocalDate before, LocalDate now);
    List<Book> findAllByUpdatedDtBetweenAndActivateIsTrue(LocalDate before, LocalDate now);
}
