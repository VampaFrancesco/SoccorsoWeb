package it.univaq.swa.soccorsoweb.model.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RichiestaSoccorsoRequest {

    @NotBlank(message = "La descrizione è obbligatoria")
    private String descrizione;

    @NotBlank(message = "L'indirizzo è obbligatorio")
    private String indirizzo;

    private BigDecimal latitudine;

    private BigDecimal longitudine;

    @NotBlank(message = "Il nome del segnalante è obbligatorio")
    private String nomeSegnalante;

    @NotBlank(message = "L'email del segnalante è obbligatoria")
    @Email(message = "L'email del segnalante deve essere valida")
    private String emailSegnalante;

    private String telefonoSegnalante;
}
