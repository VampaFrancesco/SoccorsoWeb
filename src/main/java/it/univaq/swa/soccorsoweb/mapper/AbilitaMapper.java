package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.response.AbilitaResponse;
import it.univaq.swa.soccorsoweb.model.entity.Abilita;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AbilitaMapper {

    AbilitaResponse toResponse(Abilita abilita);
}
