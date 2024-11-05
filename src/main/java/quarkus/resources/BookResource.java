package quarkus.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import quarkus.entities.Book;
import quarkus.repository.BookRepository;

import java.util.List;
import java.util.NoSuchElementException;

@Transactional
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

    @GET
    @Path("/{id}")
    public Book getBook(@PathParam("id") Long id) {
        var book = bookRepository.findById(id);
        if (book != null) return book;
        throw new NoSuchElementException("Book with id " + id + " not found");
    }

    @DELETE
    @Path("/{id}")
    public String deleteBook(@PathParam("id") Long id) {
        if(bookRepository.deleteById(id)){
            return "Book with id " + id + " deleted";
        }
        return "Book with id " + id + " not found";
    }
}
