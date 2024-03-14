package kkkw.subrandom.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import lombok.*;

import java.util.Collections;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MemberDto {

    @NotBlank
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotBlank
    private String password;

    @NotBlank
    private String name;

    public Member toEntity() {
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        return Member.builder()
                .email(email)
                .name(name)
                .password(password)
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
    }
}
