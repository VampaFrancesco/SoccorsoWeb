package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.request.AggiornamentoMissioneRequest;
import it.univaq.swa.soccorsoweb.model.dto.response.AggiornamentoMissioneResponse;
import it.univaq.swa.soccorsoweb.model.entity.AggiornamentoMissione;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AggiornamentoMissioneMapper {

    // Request → Entity
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "missione", ignore = true)
    @Mapping(target = "admin", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    AggiornamentoMissione toEntity(AggiornamentoMissioneRequest request);

    // Entity → Response
    @Mapping(target = "missioneId", source = "missione.id")
    @Mapping(target = "adminNome", source = "admin.nome")
    @Mapping(target = "adminCognome", source = "admin.cognome")
    AggiornamentoMissioneResponse toResponse(AggiornamentoMissione entity);

    List<AggiornamentoMissioneResponse> toResponseList(List<AggiornamentoMissione> entities);
}

