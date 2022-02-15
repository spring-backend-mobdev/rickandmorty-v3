package cl.mobdev.challenge.controller;

import cl.mobdev.challenge.domain.Character;
import cl.mobdev.challenge.usecase.GetCharacterUseCase;
import cl.mobdev.challenge.usecase.IsCharactersEarthlingUseCase;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RickAndMortyController {

    private final GetCharacterUseCase getCharacterUseCase;
    private final IsCharactersEarthlingUseCase charactersEarthlingUseCase;

    public RickAndMortyController(GetCharacterUseCase getCharacterUseCase,
                                  IsCharactersEarthlingUseCase charactersEarthlingUseCase) {
        this.getCharacterUseCase = getCharacterUseCase;
        this.charactersEarthlingUseCase = charactersEarthlingUseCase;
    }

    @GetMapping("character/{id}")
    public Character getExternalApi(@PathVariable String id){
        return getCharacterUseCase.execute(id);
    }
}
