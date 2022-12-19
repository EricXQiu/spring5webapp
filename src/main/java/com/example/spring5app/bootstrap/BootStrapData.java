package com.example.spring5app.bootstrap;

import com.example.spring5app.domain.Author;
import com.example.spring5app.domain.Book;
import com.example.spring5app.domain.Publisher;
import com.example.spring5app.repositories.AuthorRepository;
import com.example.spring5app.repositories.BookRepository;
import com.example.spring5app.repositories.PublisherRepository;
import jakarta.transaction.Transactional;

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
    @Transactional
    public void run(String... args) throws Exception {
        Author eric = new Author("Eric", "Evans");
        Book designBook = new Book("Domain Driven Design", "12345");

        Author rod = new Author("Rod", "Johnson");
        Book j2EEBook = new Book("J2EE Development without EJB", "67890");

        Publisher publisher = Publisher.builder()
                .name("SFG Publishing")
                .addressLine1("123 Test Street")
                .city("St Petersburg")
                .state("FL")
                .zip("12345")
                .build();

        this.publisherRepository.save(publisher);

        eric.getBooks().add(designBook);
        designBook.getAuthors().add(eric);
        designBook.setPublisher(publisher);
        publisher.getBooks().add(designBook);

        this.authorRepository.save(eric);
        this.bookRepository.save(designBook);
        this.publisherRepository.save(publisher);

        rod.getBooks().add(j2EEBook);
        j2EEBook.getAuthors().add(rod);
        j2EEBook.setPublisher(publisher);
        publisher.getBooks().add(j2EEBook);

        this.authorRepository.save(rod);
        this.bookRepository.save(j2EEBook);
        this.publisherRepository.save(publisher);

        System.out.println("The num of authors is: " + this.authorRepository.count());
        System.out.println("The num of books is: " + this.bookRepository.count());

        System.out.println("The num of publishers is: " + this.publisherRepository.count());
        System.out.println("The num of books in pulisher: " + publisher.getBooks().size());

    }
}
