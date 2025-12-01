package it.univaq.swa.soccorsoweb.mapper;

import it.univaq.swa.soccorsoweb.model.dto.request.MissioneRequest;
import it.univaq.swa.soccorsoweb.model.dto.response.MissioneResponse;
import it.univaq.swa.soccorsoweb.model.entity.Missione;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        uses = {UserMapper.class, MezzoMapper.class, MaterialeMapper.class}
)
public interface MissioneMapper {

    // ========== Request → Entity ==========
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "richiesta", ignore = true)
    @Mapping(target = "caposquadra", ignore = true)
    @Mapping(target = "operatori", ignore = true)
    @Mapping(target = "mezzi", ignore = true)
    @Mapping(target = "materiali", ignore = true)
    @Mapping(target = "stato", constant = "IN_CORSO")
    @Mapping(target = "inizioAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "fineAt", ignore = true)
    @Mapping(target = "livelloSuccesso", ignore = true)
    @Mapping(target = "commentiFinali", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "aggiornamenti", ignore = true)
    Missione toEntity(MissioneRequest request);

    // ========== Entity → Response ==========
    @Mapping(target = "richiestaId", source = "richiesta.id")
    @Mapping(target = "numeroOperatori", expression = "java(entity.getOperatori().size())")
    MissioneResponse toResponse(Missione entity);

    // ========== List mapping ==========
    List<MissioneResponse> toResponseList(List<Missione> entities);
}
