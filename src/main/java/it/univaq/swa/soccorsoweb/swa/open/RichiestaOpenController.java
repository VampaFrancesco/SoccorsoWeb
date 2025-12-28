package it.univaq.swa.soccorsoweb.swa.open;

import it.univaq.swa.soccorsoweb.model.dto.request.ConvalidaRequest;
import it.univaq.swa.soccorsoweb.model.dto.request.RichiestaSoccorsoRequest;
import it.univaq.swa.soccorsoweb.model.dto.response.RichiestaSoccorsoResponse;
import it.univaq.swa.soccorsoweb.service.RichiestaService;
import jakarta.mail.MessagingException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/swa/open/richieste")
public class RichiestaOpenController {

    private final RichiestaService richiestaService;

    @Value("${app.frontend-url}")
    private String frontendUrl;

    public RichiestaOpenController(RichiestaService richiestaService) {
        this.richiestaService = richiestaService;
    }

    /**
     * API 2: Inserisci nuova richiesta di soccorso
     * @param richiestaSoccorsoRequest
     * @return ResponseEntity<RichiestaSoccorsoResponse>
     */
    //POST /swa/open/richieste
    @PostMapping
    public ResponseEntity<RichiestaSoccorsoResponse> nuovaRichiesta(
            @Valid @RequestBody RichiestaSoccorsoRequest richiestaSoccorsoRequest,
            HttpServletRequest request) throws MessagingException {

        RichiestaSoccorsoResponse response = richiestaService.nuovaRichiesta(richiestaSoccorsoRequest, request);

        return ResponseEntity
                .created(URI.create("/swa/open/richieste/" + response.getId()))
                .body(response);
    }

    /**
     * API 3: Redirect alla pagina di convalida del frontend
     * @param token_convalida Token di convalida ricevuto via email
     * @return RedirectView verso il frontend
     */
    // GET /swa/open/richieste/convalida?token_convalida=...
    @GetMapping("/convalida")
    public RedirectView redirectConvalida(
            @RequestParam("token_convalida") String token_convalida) {

        // Redirect al frontend con il token
        String redirectUrl = frontendUrl + "/convalida.html?token_convalida=" + token_convalida;
        return new RedirectView(redirectUrl);
    }

    /**
     * API: Conferma convalida richiesta di soccorso
     * Chiamato dal frontend dopo che l'utente ha confermato
     * @param request ConvalidaRequest con token di convalida
     * @return ResponseEntity<Map<String, Object>>
     */
    // POST /swa/open/richieste/conferma-convalida
    @PostMapping("/conferma-convalida")
    public ResponseEntity<Map<String, Object>> confermaConvalida(
            @Valid @RequestBody ConvalidaRequest request) {

        richiestaService.convalidaRichiesta(request.getTokenConvalida());

        return ResponseEntity.ok(Map.of(
                "message", "✅ La tua richiesta di soccorso è stata convalidata con successo!",
                "timestamp", java.time.LocalDateTime.now()
        ));
    }
}
