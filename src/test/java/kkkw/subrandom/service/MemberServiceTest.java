package kkkw.subrandom.service;

import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.dto.MemberDto;
import kkkw.subrandom.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @Mock
    private MemberRepository memberRepository;

    @Spy
    private BCryptPasswordEncoder passwordEncoder;

    @InjectMocks
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 로직 테스트")
    void addMember() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        MemberDto memberDto = new MemberDto("aaa@a.com", "1111", "aaa");

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();


        when(memberRepository.save(any())).thenReturn(member);

        //when
        Member result = memberService.addMember(memberDto);

        //then
        assertThat(result.getPassword()).isNotEqualTo("1111");
        System.out.println("/////////////////////////////////");
        System.out.println("member.getPassword() = " + member.getPassword());
        System.out.println("result.getPassword() = " + result.getPassword());
        System.out.println("/////////////////////////////////");
    }

    @Test
    @DisplayName("이메일로 멤버 조회 로직 테스트")
    void findMemberWithAuthorities() {
    }

    @Test
    @DisplayName("현재 로그인 중인 멤버 조회 로직 테스트")
    void findMyMemberWithAuthorities() {
    }
}