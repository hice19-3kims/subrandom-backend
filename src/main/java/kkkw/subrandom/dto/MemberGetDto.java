package kkkw.subrandom.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberGetDto {

    @NotBlank
    private String email;

    @NotBlank
    private String name;

}