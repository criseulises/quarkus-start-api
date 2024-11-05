package quarkus.resources;

import jakarta.transaction.Transactional;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import quarkus.entities.Book;

import java.util.List;

@Transactional
@Path("/books")
public class BookResource {

    @GET
    public List<Book> listBooks() {
        return Book.listAll();
    }

    @POST
    public Book insertBook(Book book) {
        book.persist();
        return book;
    }
}
