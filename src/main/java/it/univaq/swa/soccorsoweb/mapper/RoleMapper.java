package it.univaq.swa.soccorsoweb.mapper;


import it.univaq.swa.soccorsoweb.model.dto.response.RoleResponse;
import it.univaq.swa.soccorsoweb.model.entity.Role;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RoleMapper {

    RoleResponse toResponse(Role role);
}
