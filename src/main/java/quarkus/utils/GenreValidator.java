package quarkus.utils;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.validation.Validator;
import quarkus.resources.dto.CreateGenreDTO;

import java.util.Optional;


@ApplicationScoped
public class GenreValidator {
    Validator validator;

    @Inject
    public GenreValidator(Validator validator) {
        this.validator = validator;
    }


    public Optional<String> validateGenre(CreateGenreDTO dto){
        var errors = validator.validate(dto);
        if(errors.isEmpty()){
            return Optional.empty();
        }
        return Optional.of(errors.stream().findFirst().get().getMessage());

    }
}
