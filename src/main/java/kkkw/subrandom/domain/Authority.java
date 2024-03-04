package kkkw.subrandom.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Authority {

    @Id
    @GeneratedValue
    @Column(name = "authority_name")
    private String authorityName;

    @Builder
    public Authority (String authorityName) {
        this.authorityName = authorityName;
    }
}
