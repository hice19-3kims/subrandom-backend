package kkkw.subrandom.service;


import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.dto.MemberDto;
import kkkw.subrandom.exceptions.MemberNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.repository.SaveRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
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
    public final SaveRepository saveRepository;
    public final HeartRepository heartRepository;
    public final ReviewRepository reviewRepository;
    public final RecipeRepository recipeRepository;

    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Member addMember(MemberDto memberDto) {
        if (memberRepository.findOneWithAuthoritiesByEmail(memberDto.getEmail()).orElse(null) != null) {
            throw new RuntimeException("이미 가입되어 있는 유저입니다.");
        }

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .email(memberDto.getEmail())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
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
