package com.literalura.literalura.model;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.util.List;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String title;
    @Enumerated(EnumType.STRING)
    private Language language;
    private Double downloads;
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id"))
    private List<Author> authors;

    public Book() {}

    public Book(BookData bookData) {
        this.title = bookData.title();
        this.language = Language.fromString(bookData.languages().get(0));
        this.downloads = bookData.downloads();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

    public Double getDownloads() {
        return downloads;
    }

    public void setDownloads(Double downloads) {
        this.downloads = downloads;
    }

    public List<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(List<Author> authors) {
        this.authors = authors;
    }

    @Override
    public String toString() {
        return String.format("""
                Titulo: %s
                Autor(es): %s
                Lenguaje: %s
                Descargas: %s
                """, title, getNameAuthors(), language, downloads);
    }

    public List<String> getNameAuthors() {
        return authors.stream()
                .map(Author::getName)
                .toList();
    }
}
