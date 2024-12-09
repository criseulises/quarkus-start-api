package quarkus.resources;


import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Parameters;
import io.quarkus.panache.common.Sort;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Response;
import quarkus.entities.Genre;
import quarkus.repository.GenreRepository;
import quarkus.resources.dto.CreateGenreDTO;
import quarkus.resources.dto.GenreResponseDTO;
import quarkus.resources.dto.UpdateGenreDTO;
import quarkus.resources.mapper.GenreMapper;
import quarkus.resources.mapper.GenreMapperImpl;
import quarkus.utils.PaginatedResponse;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Path("/genres")
public class GenreResource {

    @Inject
    GenreRepository genreRepository;

    @Inject
    GenreMapper genreMapper;


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
    public PaginatedResponse<GenreResponseDTO> listQuery(@QueryParam("page") @DefaultValue("1") int page,
                                              @QueryParam("filter") String filter) {
//        Page pageObj = new Page(page - 1, 5);
//        var query = genreRepository.findAll(Sort.descending("createdAt")).page(pageObj);
//        if (filter != null) {
//            query.filter("name.like", Parameters.with("name", "%" + filter + "%"));
//        }
//        return new PaginatedResponse<>(query);


        PanacheQuery<Genre> query = genreRepository.findPage(page);
        PanacheQuery<GenreResponseDTO> newQuery = query.project(GenreResponseDTO.class);

        System.out.println(newQuery.toString());
        return new PaginatedResponse<>(newQuery);

    }

    @POST
    @Transactional
    public Response create(CreateGenreDTO genre) {
        System.out.println(genre);
        Genre entity = genreMapper.fromCreate(genre);
        genreRepository.persist(entity);
        var representation  = genreMapper.present(entity);
        return Response.created(URI.create("/genres/" + entity.getId())).entity(representation).build();
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
    public GenreResponseDTO get(@PathParam("id") Long id) {
        return genreRepository
                .findByIdOptional(id)
                .map(genreMapper::present)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + " not found"));
    }

    @PUT
    @Path("{id}")
    @Transactional
    public GenreResponseDTO update(@PathParam("id") Long id, UpdateGenreDTO dto) {
        Genre found = genreRepository
                .findByIdOptional(id)
                .orElseThrow(() -> new NoSuchElementException("Genre " + id + " not found"));

        genreMapper.update(dto, found);
        genreRepository.persist(found);
        return genreMapper.present(found);
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public void delete(@PathParam("id") Long id) {
        genreRepository.deleteById(id);
    }
}
