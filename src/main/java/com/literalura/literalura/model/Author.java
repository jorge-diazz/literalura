package com.literalura.literalura.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;
import java.util.OptionalInt;

@Entity
@Table(name = "authors")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String name;
    private Integer birthYear;
    private Integer deathYear;
    @ManyToMany(mappedBy = "authors", fetch = FetchType.EAGER)
    private List<Book> books;

    public Author() {}

    public Author(AuthorData authorData) {
        this.name = authorData.name();
        this.birthYear = authorData.birthYear();
        this.deathYear = OptionalInt.of(authorData.deathYear()).orElse(0);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(Integer birthYear) {
        this.birthYear = birthYear;
    }

    public Integer getDeathYear() {
        return deathYear;
    }

    public void setDeathYear(Integer deathYear) {
        this.deathYear = deathYear;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return String.format("""
                Nombre: %s
                Nacimiento: %d
                Fallecimiento: %d
                """, this.name, this.birthYear, this.deathYear);
    }

}
