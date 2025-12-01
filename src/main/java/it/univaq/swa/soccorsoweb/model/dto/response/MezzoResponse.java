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
public class MezzoResponse {
    private Long id;
    private String nome;
    private String descrizione;
    private String tipo;
    private String targa;
    private Boolean disponibile;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
