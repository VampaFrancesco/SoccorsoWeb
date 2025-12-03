package it.univaq.swa.soccorsoweb.service;

import it.univaq.swa.soccorsoweb.mapper.UserMapper;
import it.univaq.swa.soccorsoweb.model.dto.request.LoginRequest;
import it.univaq.swa.soccorsoweb.model.dto.response.UserResponse;
import it.univaq.swa.soccorsoweb.model.entity.User;
import it.univaq.swa.soccorsoweb.repository.UserRepository;
import it.univaq.swa.soccorsoweb.security.jwt.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthControllerService {


    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;
    private final UserMapper userMapper;
    private final UserRepository userRepository;

    // Constructor Injection
    public AuthControllerService(AuthenticationManager authenticationManager,
                                 JWTUtil jwtUtil,
                                 UserRepository userRepository,
                                 UserMapper userMapper) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }


    public UserResponse login(LoginRequest loginRequest) {
        try {
            // 1️⃣ AUTENTICA con Spring Security
            // Questo confronta automaticamente la password in chiaro con l'hash nel DB
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getEmail(),
                            loginRequest.getPassword()  // ⬅️ Password in chiaro, va bene!
                    )
            );

            // 2️⃣ Se arrivi qui, le credenziali sono CORRETTE!
            // Recupera i dettagli dell'utente autenticato
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // 3️⃣ Genera il token JWT
            String token = jwtUtil.generateToken(userDetails);

            // 4️⃣ Carica i dati completi dell'utente dal DB
            // ⬅️⬅️⬅️ USA SOLO L'EMAIL, NON LA PASSWORD!
            User user = userRepository.findByEmail(loginRequest.getEmail())
                    .orElseThrow(() -> new RuntimeException("Utente non trovato"));

            // 5️⃣ Crea la risposta
            UserResponse response = userMapper.toResponse(user);
            response.setToken(token);

            return response;

        } catch (BadCredentialsException e) {
            throw new RuntimeException("Email o password errati", e);
        }
    }

//    public void logout() {
//
//
//    }
}
