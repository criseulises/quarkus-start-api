package quarkus.resources.dto;

import jakarta.validation.constraints.NotBlank;

import javax.xml.transform.Source;

public record CreateGenreDTO(@NotBlank String name) {
}
