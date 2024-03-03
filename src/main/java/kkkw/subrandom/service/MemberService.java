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

    public Member findMember(Long memberId){
//        if(memberRepository.findById(userId).isPresent())
            return memberRepository.findById(memberId).get();
//        else throw new MemberNotFoundException();
    }

    @Transactional
    public Member signup(MemberDto memberDto) {
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

    @Transactional(readOnly = true)
    public Member getMemberWithAuthorities(String email) {
        return memberRepository.findOneWithAuthoritiesByEmail(email).orElse(null);
    }

    @Transactional(readOnly = true)
    public Member getMyMemberWithAuthorities() {
        return SecurityUtil.getCurrentUsername()
                .flatMap(memberRepository::findOneWithAuthoritiesByEmail)
                .orElseThrow(() -> new RuntimeException("Member not found"));
    }

}
