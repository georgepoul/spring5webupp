package com.spring.demo.bootstrap;

import com.spring.demo.domain.Author;
import com.spring.demo.domain.Book;
import com.spring.demo.domain.Publisher;
import com.spring.demo.repositories.AuthorRepository;
import com.spring.demo.repositories.BookRepository;
import com.spring.demo.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("abc", "1234567");
        Publisher kat = new Publisher("Carl", "agioi","Athens","la","82100");


        kat.getBooks().add(ddd);
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(kat);
        System.out.println("Publisher count: " + publisherRepository.count());


        Author rod = new Author("Rod","geo");
        Book nor = new Book("safg","0987654");

        rod.getBooks().add(nor);
        nor.getAuthors().add(rod);
        kat.getBooks().add(nor);


        authorRepository.save(rod);
        bookRepository.save(nor);

        System.out.println("Number of books: " + bookRepository.count());
        System.out.println("Books of publisher: " + kat.getBooks().size());
    }
}
