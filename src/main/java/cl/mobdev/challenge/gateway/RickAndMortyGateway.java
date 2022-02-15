package cl.mobdev.challenge.gateway;

import cl.mobdev.challenge.domain.Character;
import cl.mobdev.challenge.gateway.mapper.CharacterToCharacterResponseMapper;
import cl.mobdev.challenge.gateway.model.ApiCharacter;
import cl.mobdev.challenge.gateway.model.ApiLocation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

public class RickAndMortyGateway {

    private final RestTemplate restTemplate;
    private final String apiUrl;
    private final CharacterToCharacterResponseMapper characterResponseMapper;

    public RickAndMortyGateway(RestTemplate restTemplate,
                               @Value("${apiUrl}") String apiUrl,
                               CharacterToCharacterResponseMapper characterResponseMapper) {
        this.restTemplate = restTemplate;
        this.apiUrl = apiUrl;
        this.characterResponseMapper = characterResponseMapper;
    }

    public Character getApiCharacter(String id){
        Character character;
        ResponseEntity<ApiCharacter> apiCharacter= restTemplate.getForEntity(apiUrl+ id , ApiCharacter.class);
        character = characterResponseMapper.mapper(apiCharacter.getBody(), getApiLocation(apiCharacter.getBody()));
        return character;
    }

    public ApiLocation getApiLocation(ApiCharacter apiCharacter){
        ApiLocation location= new ApiLocation();
        if (apiCharacter.getOrigin() != null && !"".equals(apiCharacter.getOrigin().getUrl())){
            location = restTemplate.getForObject(apiCharacter.getOrigin().getUrl(), ApiLocation.class);
        }
        return location;
    }
}
