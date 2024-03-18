package kkkw.subrandom.service;


import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.dto.SignupDto;
import kkkw.subrandom.exceptions.MemberNotFoundException;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.Optional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member addMember(SignupDto signupDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(signupDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(signupDto.getEmail())
                .password(passwordEncoder.encode(signupDto.getPassword()))
                .name(signupDto.getName())
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        return memberRepository.save(member);
    }

    public Member findMemberWithAuthorities(String email) {
        Optional<Member> optionalMember = memberRepository
                .findOneWithAuthoritiesByEmail(email);

        if (optionalMember.isEmpty()){
            throw new MemberNotFoundException();
        }

        return optionalMember.get();
    }

    public Member findMyMemberWithAuthorities() {
        Optional<Member> optionalMember = memberRepository
                .findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUserEmail());

        if (optionalMember.isEmpty()){
            throw new MemberNotFoundException();
        }

        return optionalMember.get();
    }
}
