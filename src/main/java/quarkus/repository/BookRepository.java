package quarkus.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import quarkus.entities.Book;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

}
