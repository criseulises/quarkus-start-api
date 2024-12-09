package quarkus.resources.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import quarkus.entities.Genre;
import quarkus.resources.dto.CreateGenreDTO;
import quarkus.resources.dto.UpdateGenreDTO;

@ApplicationScoped
public class GenreMapperImpl implements GenreMapper{

    @Override
    public Genre fromCreate(CreateGenreDTO dto) {
        Genre genre = new Genre();
        genre.setName(dto.name());
        return genre;
    }

    @Override
    public void update(UpdateGenreDTO dto, Genre genre) {
        genre.setName(dto.name());
    }
}
