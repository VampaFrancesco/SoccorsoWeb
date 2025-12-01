package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.response.PatenteResponse;
import it.univaq.swa.soccorsoweb.model.entity.Patente;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PatenteMapper {

    PatenteResponse toResponse(Patente patente);
}
