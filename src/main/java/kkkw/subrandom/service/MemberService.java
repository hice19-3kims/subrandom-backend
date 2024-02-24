package kkkw.subrandom.service;


import kkkw.subrandom.domain.Member;
import kkkw.subrandom.exceptions.MemberNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.repository.SaveRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    public final MemberRepository memberRepository;
    public final SaveRepository saveRepository;
    public final HeartRepository heartRepository;
    public final ReviewRepository reviewRepository;
    public final RecipeRepository recipeRepository;


    public Member findMember(Long memberId){
//        if(memberRepository.findById(userId).isPresent())
            return memberRepository.findById(memberId).get();
//        else throw new MemberNotFoundException();
    }
}
