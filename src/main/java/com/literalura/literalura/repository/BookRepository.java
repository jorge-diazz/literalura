package com.literalura.literalura.repository;

import com.literalura.literalura.model.Book;
import com.literalura.literalura.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<Book> findByTitleIgnoreCase(String title);

    List<Book> findByLanguage(Language language);
}
