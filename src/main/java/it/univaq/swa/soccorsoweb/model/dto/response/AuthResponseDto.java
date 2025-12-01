package it.univaq.swa.soccorsoweb.model.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthResponseDto {

    private String token;
    private String type = "Bearer";
    private UserResponse user;

    public AuthResponseDto(String token, UserResponse user) {
        this.token = token;
        this.user = user;
        this.type = "Bearer";
    }
}
