package quarkus.resources;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
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
    public void deleteBook(@PathParam("id") Long id) {
        bookRepository.deleteById(id);
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Book updateBook(@PathParam("id") Long id, Book book) {
        Book updatedBook = bookRepository.findById(id);
        if (updatedBook != null) {
            updatedBook.setTitle(book.getTitle());
            updatedBook.setPublishDate(book.getPublishDate());
            updatedBook.setNumPages(book.getNumPages());
            updatedBook.setDescription(book.getDescription());
            bookRepository.persist(updatedBook);
            return updatedBook;
        }
        throw new NoSuchElementException("Book with id " + id + " not found");
    }
}
