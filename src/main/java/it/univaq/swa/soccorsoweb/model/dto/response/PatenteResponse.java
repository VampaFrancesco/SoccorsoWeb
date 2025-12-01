package it.univaq.swa.soccorsoweb.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatenteResponse {
    private Long id;
    private String tipo;
    private String descrizione;
}
