package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {
    @NotNull
    private String email;

    @NotNull
    private String password;
}
