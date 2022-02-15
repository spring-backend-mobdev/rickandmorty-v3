package cl.mobdev.challenge.usecase;

import cl.mobdev.challenge.domain.Character;
import cl.mobdev.challenge.gateway.HumanCharacterGateway;

public class IsHumanCharacterUseCase {

    //Tendra un metodo que dado un id,
    //usara un gateway que trae un character
    //y retorna un "true" si es humano y false en otro caso

    private final HumanCharacterGateway humanCharacterGateway;

    public IsHumanCharacterUseCase(HumanCharacterGateway humanCharacterGateway) {
        this.humanCharacterGateway = humanCharacterGateway;
    }

    public boolean execute(String id) {
        Character character;
        character = humanCharacterGateway.findCharacter(id);
        if (null != character && "Human".equals(character.getSpecies())) {
            return true;
        }
        return false;
    }
}
