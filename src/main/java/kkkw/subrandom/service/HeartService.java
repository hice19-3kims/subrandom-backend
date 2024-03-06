package kkkw.subrandom.service;

import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.exceptions.HeartNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;

    @Transactional
    public Heart changeHeart(Member member, Review review) {

        Optional<Heart> optionalHeart = heartRepository.findByMemberIdAndReviewId(member.getId(), review.getId());

        if (optionalHeart.isEmpty()){
            Heart heart = Heart.createHeart(member, review);
            return heartRepository.save(heart);
        }
        else {
            Heart heart = optionalHeart.get();
            heart.cancelHeart();
            heartRepository.delete(heart);
            return null;
        }
    }

    public List<Heart> findHeartsByMemberId(Long memberId) {
        if(heartRepository.findByMemberId(memberId).isEmpty())
            throw new HeartNotFoundException();

        return heartRepository.findByMemberId(memberId);
    }

}
