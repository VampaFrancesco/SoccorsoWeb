package it.univaq.swa.soccorsoweb.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AggiornamentoMissioneRequest {

    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;
}
