package kkkw.subrandom.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member",
        uniqueConstraints = {
            @UniqueConstraint(
                name = "EMAIL_NAME_UNIQUE",
                columnNames = {"email", "name"}
            )
        }
)
public class Member {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long memberId;

    @Column(unique = true)
    @NotNull
    private String email;

    @Column(unique = true)
    @NotNull
    private String name;

    @NotNull
    private String password;
}
