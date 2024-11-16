package com.literalura.literalura.repository;

import com.literalura.literalura.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author, Long> {
    public Optional<Author> findByNameIgnoreCase(String name);

    @Query(value = "SELECT a FROM Author a WHERE a.birthYear <= :year AND (a.deathYear > :year OR a.deathYear = 0)")
    List<Author> authorAliveInYear(Integer year);
}
