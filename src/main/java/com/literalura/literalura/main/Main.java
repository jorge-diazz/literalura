package com.literalura.literalura.main;

import com.literalura.literalura.model.*;
import com.literalura.literalura.repository.AuthorRepository;
import com.literalura.literalura.repository.BookRepository;
import com.literalura.literalura.service.ConsumeAPI;
import com.literalura.literalura.service.ConvertData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    private Scanner scanner = new Scanner(System.in);
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void start() {
        int option;
        do {
            menu();
            option = scanner.nextInt();
            scanner.nextLine();
            System.out.println();
            options(option);
        } while(option != 0);
    }

    private void menu() {
        System.out.println("""
                1.- Buscar libro.
                2.- Libros registrados.
                3.- Autores registrados.
                4.- Autores vivos en determinado año.
                5.- Libros por idioma.
                0.- Salir.
                """);
        System.out.print("Ingresa una opcion: ");
    }

    private void options(int option) {
        switch (option) {
            case 1:
                searchBook();
                break;
            case 2:
                registeredBooks();
                break;
            case 3:
                registeredAuthors();
                break;
            case 4:
                authorsAliveInYear();
                break;
            case 5:
                booksByLanguage();
                break;
            case 0:
                System.out.println("Saliedo del programa.");
                break;
            default:
                System.out.println("Opcion no valida.");
        }
    }

    private void searchBook() {
        System.out.print("Ingrese el titulo del libro: ");
        String title = scanner.nextLine().toLowerCase();
        System.out.println();

        Optional<BookData> bookDataOptional = getBookByTitleApi(title);
        if (bookDataOptional.isPresent()) {
            Optional<Book> bookOptional = getBookByTitle(bookDataOptional.get().title());
            if (bookOptional.isPresent()) {
                Book book = bookOptional.get();
                System.out.println(book);
            } else {
                Book book = new Book(bookDataOptional.get());
                List<Author> authors = new ArrayList<>();
                for (AuthorData authorData : bookDataOptional.get().authors()) {
                    Optional<Author> authorOptional = getAuthorByName(authorData.name());
                    if (authorOptional.isPresent()) {
                        authors.add(authorOptional.get());
                    } else {
                        Author author = authorRepository.save(new Author(authorData));
                        authors.add(author);
                    }
                }
                book.setAuthors(authors);
                bookRepository.save(book);
                System.out.println(book);
            }
        } else {
            System.out.println("Libro no encontrado.");
        }
    }

    private Optional<BookData> getBookByTitleApi(String title) {
        ConsumeAPI consumeAPI = new ConsumeAPI();
        ConvertData convertData = new ConvertData();

        String json = consumeAPI.getData("https://gutendex.com/books/?search=" + title.replace(" ", "+"));
        Data data = convertData.convert(json, Data.class);

        return data.books().stream()
                .filter(b -> b.title().toLowerCase().contains(title))
                .findFirst();
    }

    private Optional<Book> getBookByTitle(String title) {
        return bookRepository.findByTitleIgnoreCase(title);
    }

    private Optional<Author> getAuthorByName(String name) {
        return authorRepository.findByNameIgnoreCase(name);
    }

    private void registeredBooks() {
        List<Book> books = bookRepository.findAll();
        books.forEach(System.out::println);
    }

    private void registeredAuthors() {
        List<Author> authors = authorRepository.findAll();
        authors.forEach(System.out::println);
    }

    private void authorsAliveInYear() {
        System.out.print("Ingrese el año: ");
        Integer year = scanner.nextInt();
        scanner.nextLine();

        List<Author> authors = authorRepository.authorAliveInYear(year);
        authors.forEach(System.out::println);
    }

    private void booksByLanguage() {
        System.out.println("""
                Idioma:
                en - Ingles
                es - Español
                fr - Frances
                pt - Portugues
                """);
        System.out.print("Ingrese el idioma del libro: ");
        String language = scanner.nextLine().toLowerCase();

        List<Book> books = bookRepository.findByLanguage(Language.fromString(language));

        books.forEach(System.out::println);
    }
}
