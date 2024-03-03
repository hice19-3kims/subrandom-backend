package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByMemberId(Long memberId);
    List<Heart> findByReviewId(Long reviewId);
}
