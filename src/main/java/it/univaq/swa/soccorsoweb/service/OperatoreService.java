package it.univaq.swa.soccorsoweb.service;

import it.univaq.swa.soccorsoweb.mapper.UserMapper;
import it.univaq.swa.soccorsoweb.model.dto.response.UserResponse;
import it.univaq.swa.soccorsoweb.model.entity.User;
import it.univaq.swa.soccorsoweb.repository.UserRepository;
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

    public List<UserResponse> operatoreDisponibile() {
        List<User> operatori = userRepository.findAll()
//        for(User u : operatori){
//            if(!u.getDisponibile() || u.getRoles().stream().noneMatch(r -> r.getName().equals("OPERATORE"))){
//                operatori.remove(u);
//            }
//            return userMapper.toResponseList(operatori);
//        }

                .stream()
                .filter(user -> user.getRoles()
                        .stream()
                        .anyMatch( role -> role.getName().equals("OPERATORE") && user.getDisponibile())).toList();

        log.info("Operatore disponibile: {} ", operatori.toString());

        return userMapper.toResponseList(operatori);
    }
}
