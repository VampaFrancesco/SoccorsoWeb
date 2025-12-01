package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.response.MaterialeResponse;
import it.univaq.swa.soccorsoweb.model.entity.Materiale;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaterialeMapper {

    MaterialeResponse toResponse(Materiale materiale);
}

