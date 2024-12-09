package quarkus.resources.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import quarkus.entities.Genre;
import quarkus.resources.dto.CreateGenreDTO;
import quarkus.resources.dto.GenreResponseDTO;
import quarkus.resources.dto.UpdateGenreDTO;

public interface GenreMapper {

    Genre fromCreate(CreateGenreDTO dto);

    void update(UpdateGenreDTO dto, Genre genre);

    GenreResponseDTO present(Genre genre);

}
