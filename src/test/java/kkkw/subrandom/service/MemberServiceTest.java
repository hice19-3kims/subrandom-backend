package kkkw.subrandom.service;

import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.dto.SignupDto;
import kkkw.subrandom.exceptions.MemberNotFoundException;
import kkkw.subrandom.repository.MemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collections;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
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

        SignupDto signupDto = new SignupDto("aaa@a.com", "1111", "aaa");

        Member member = Member.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .name(signupDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();


        when(memberRepository.save(any())).thenReturn(member);

        when(memberRepository.findOneWithAuthoritiesByEmail(member.getEmail())).then(invocation -> {
            System.out.println("This is mock memberRepository.");
            return Optional.ofNullable(null);
        });

        //when
        Member result = memberService.addMember(signupDto);

        //then
        verify(memberRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("중복 회원 검출 테스트")
    void addMemberVerify() throws Exception{
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .email("aaa@a.com")
                .password(passwordEncoder.encode("1111"))
                .name("aaa")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        SignupDto signupDto = new SignupDto("aaa@a.com", "1111", "aaa");

        Member member2 = Member.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .name(signupDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        when(memberRepository.findOneWithAuthoritiesByEmail(member1.getEmail())).then(invocation -> {
            System.out.println("This is mock memberRepository.");
            return Optional.of(member1);
        });

        //when
        Throwable throwable = catchThrowable(() -> {
            memberService.addMember(signupDto);
        });

        //then
        Assertions.assertThat(throwable).isInstanceOf(RuntimeException.class);
        verify(memberRepository, Mockito.times(0)).save(any());
    }

    @Test
    @DisplayName("이메일로 멤버 조회 로직 테스트")
    void findMemberWithAuthorities() throws Exception{
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .email("aaa@a.com")
                .password(passwordEncoder.encode("1111"))
                .name("aaa")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        when(memberRepository.findOneWithAuthoritiesByEmail(member1.getEmail()))
                .thenReturn(Optional.of(member1));

        when(memberRepository.findOneWithAuthoritiesByEmail("bbb@b.com"))
                .thenReturn(Optional.empty());

        //when
        memberService.findMemberWithAuthorities(member1.getEmail());

        Throwable throwable = catchThrowable(() -> {
            memberService.findMemberWithAuthorities("bbb@b.com");
        });

        //then
        Assertions.assertThat(throwable).isInstanceOf(MemberNotFoundException.class);
        verify(memberRepository, Mockito.times(2)).findOneWithAuthoritiesByEmail(any());
    }

    /* DB 접근 필요하므로 생략함

    @Test
    @DisplayName("현재 로그인 중인 멤버 조회 로직 테스트")
    void findMyMemberWithAuthorities() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .email("aaa@a.com")
                .password(passwordEncoder.encode("1111"))
                .name("aaa")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        when(SecurityUtil.getCurrentUserEmail()).thenReturn("aaa@a.com");

        when(memberRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUserEmail()))
                .thenReturn(Optional.of(member1));

        //when
        Throwable throwable = catchThrowable(() -> {
            memberService.findMyMemberWithAuthorities();
        });

        //then
        verify(memberRepository, Mockito.times(1)).findOneWithAuthoritiesByEmail(any());
    }
    */
}