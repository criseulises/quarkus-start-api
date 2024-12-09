package quarkus.resources.dto;

import io.quarkus.runtime.annotations.RegisterForReflection;

@RegisterForReflection
public record GenreResponseDTO (Long id, String name){
}
