package kkkw.subrandom.service;

import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                review
        );

        return heartRepository.save(heart);
    }
}
