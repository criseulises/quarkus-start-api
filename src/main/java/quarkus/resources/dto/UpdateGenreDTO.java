package quarkus.resources.dto;

import jakarta.validation.constraints.NotBlank;

public record UpdateGenreDTO(@NotBlank String name) {
}
