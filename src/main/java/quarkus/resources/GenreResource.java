package quarkus.resources;


import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.entities.Genre;
import quarkus.repository.GenreRepository;
import quarkus.utils.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.NoSuchElementException;

@Path("/genres")
public class GenreResource {

    @Inject
    private GenreRepository genreRepository;

    @GET
    public List<Genre> listAll() {
        return genreRepository.listAll();
    }

    @GET
    @Path("automate")
    public List<Genre> list(@QueryParam("page") @DefaultValue("1") int page) {
        Page pageObj = new Page(page - 1, 5);
        var query = genreRepository.findAll(Sort.descending("createdAt")).page(pageObj).list();
        return query;
    }

    @GET
    @Path("query")
    public PaginatedResponse<Genre> listQuery(@QueryParam("page") @DefaultValue("1") int page,
                                              @QueryParam("filter") String filter) {
        Page pageObj = new Page(page - 1, 5);
        var query = genreRepository.findAll(Sort.descending("createdAt")).page(pageObj);
        if (filter != null) {
            query.filter("name.like", Parameters.with("name", "%" + filter + "%"));
        }
        return new PaginatedResponse<>(query);
    }

    @POST
    @Transactional
    public Response create(Genre genre) {
        genreRepository.persist(genre);
        return Response.created(URI.create("/genres/" + genre.getId())).entity(genre).build();
    }

    @POST
    @Path("/bulk")
    @Transactional
    public Response createGenres(List<Genre> genres) {
        for (Genre genre : genres) {
            genreRepository.persist(genre);
        }
        return Response.created(URI.create("/genres")).entity(genres).build();
    }

    @GET
    @Path("{id}")
    public Genre get(@PathParam("id") Long id) {
        return genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + " not found"));
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Genre update(@PathParam("id") Long id, Genre genre) {
        Genre found = genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + " not found"));

        found.setName(genre.getName());
        genreRepository.persist(found);
        return found;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        genreRepository.deleteById(id);
    }
}
