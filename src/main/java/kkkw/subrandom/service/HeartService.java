package kkkw.subrandom.service;

import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.exceptions.HeartNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {

    private final MemberRepository memberRepository;
    private final HeartRepository heartRepository;

    @Transactional
    public Heart addMyHeart(Review review) {
        Heart heart = Heart.createHeart(
                memberRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUsername().get()).get(),
                review);
        return heartRepository.save(heart);
    }

    @Transactional
    public void removeHeart(Long memberId, Long reviewId) {
        Heart heart = heartRepository.findByMemberIdAndReviewId(memberId, reviewId);
        heart.cancelHeart();
        heartRepository.delete(heart);
    }

    public List<Heart> findHeartsByMember(Long memberId) {
        if(!heartRepository.findByMemberId(memberId).isEmpty())
            return heartRepository.findByMemberId(memberId);
        throw new HeartNotFoundException();
    }
}
