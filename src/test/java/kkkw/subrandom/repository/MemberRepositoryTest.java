package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;

    Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

    @Test
    @DisplayName("멤버 생성")
    void createMember() { //fixtureMonkey
        //given
        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        //when
        Member result1 = memberRepository.save(member1);
        Member result2 = memberRepository.save(member2);

        //then
        assertThat(member1.getName()).isEqualTo(result1.getName());
        assertThat(member2.getName()).isEqualTo(result2.getName());
        assertThat(member1.getPassword()).isEqualTo(result1.getPassword());
    }

    @Test
    @DisplayName("findOneWithAuthoritiesByEmail 확인")
    void findOneWithAuthoritiesByEmail() {
        //given
        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();
        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        //when
        Optional<Member> result1 = memberRepository.findOneWithAuthoritiesByEmail(member1.getEmail());
        Optional<Member> result2 = memberRepository.findOneWithAuthoritiesByEmail(member2.getEmail());
        Optional<Member> result3 = memberRepository.findOneWithAuthoritiesByEmail("a@a.com");
        Optional<Member> result4 = memberRepository.findOneWithAuthoritiesByEmail("c@c.com");

        //then
        assertThat(result1.get()).isSameAs(member1);
        assertThat(result2.get()).isSameAs(member2);
        assertThat(result3.get()).isSameAs(member1);
        assertThat(result4).isEmpty();


    }
}