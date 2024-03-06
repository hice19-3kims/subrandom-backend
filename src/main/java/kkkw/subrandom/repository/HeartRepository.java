package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByMemberId(Long memberId);
    Optional<Heart> findByMemberIdAndReviewId(Long memberId, Long reviewId);
}
