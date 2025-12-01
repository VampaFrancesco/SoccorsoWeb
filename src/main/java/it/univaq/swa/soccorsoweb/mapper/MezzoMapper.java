package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.response.MezzoResponse;
import it.univaq.swa.soccorsoweb.model.entity.Mezzo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MezzoMapper {

    MezzoResponse toResponse(Mezzo mezzo);
}
