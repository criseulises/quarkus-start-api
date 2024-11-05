package quarkus;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import quarkus.entities.Book;
import quarkus.repository.BookRepository;

import java.util.List;

@Path("/books")
public class BookResource {

    @Inject
    private BookRepository bookRepository;


    @GET
    public List<Book> listBooks() {
        return bookRepository.listAll();
    }

    @POST
    public Book insertBook(Book book) {
        bookRepository.persist(book);
        return book;
    }
}
