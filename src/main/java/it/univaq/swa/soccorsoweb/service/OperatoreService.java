package it.univaq.swa.soccorsoweb.service;

import it.univaq.swa.soccorsoweb.mapper.UserMapper;
import it.univaq.swa.soccorsoweb.model.dto.response.UserResponse;
import it.univaq.swa.soccorsoweb.model.entity.Role;
import it.univaq.swa.soccorsoweb.model.entity.User;
import it.univaq.swa.soccorsoweb.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OperatoreService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public OperatoreService(UserMapper userMapper, UserRepository userRepository) {
        this.userMapper = userMapper;
        this.userRepository = userRepository;
    }

    public List<UserResponse> operatoreDisponibile(boolean disponibili) {
        List<User> operatori = userRepository.findOperatoriByDisponibile(disponibili);

        List<User> operatoriFiltrati = operatori.stream()
                .filter(operatore -> {
                    List<Role> ruoli = operatore.getRoles().stream().toList();
                    boolean hasOperatore = ruoli.stream().anyMatch(role -> role.getName().equals("OPERATORE"));
                    boolean hasAdmin = ruoli.stream().anyMatch(role -> role.getName().equals("ADMIN"));

                    // Mantieni solo chi ha OPERATORE ma NON ADMIN
                    return hasOperatore && !hasAdmin;
                })
                .toList();

        return userMapper.toResponseList(operatoriFiltrati);
    }


    public UserResponse dettagliOperatore(Long id){

        User operatore = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Operatore non trovato con ID: " + id));
        boolean isOperatore = operatore.getRoles().stream()
                .anyMatch(role -> role.getName().equals("OPERATORE"));
        if (!isOperatore) {
            throw new IllegalArgumentException("L'utente con ID " + id + " non è un operatore");
        }
        return userMapper.toResponse(operatore);
    }
}
