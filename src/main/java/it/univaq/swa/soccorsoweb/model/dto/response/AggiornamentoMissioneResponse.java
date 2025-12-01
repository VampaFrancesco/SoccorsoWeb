package it.univaq.swa.soccorsoweb.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggiornamentoMissioneResponse {
    private Long id;
    private Long missioneId;
    private String adminNome;
    private String adminCognome;
    private String descrizione;
    private LocalDateTime createdAt;
}
