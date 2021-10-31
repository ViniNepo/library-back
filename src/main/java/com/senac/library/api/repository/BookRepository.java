package com.senac.library.api.repository;

import com.senac.library.api.model.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findByAuthorAndTitle(String author, String name);
    List<Book> findAllByCreateDtIsBefore(LocalDateTime author);
    List<Book> findAllByUpdateDtBefore(LocalDateTime author);
}
