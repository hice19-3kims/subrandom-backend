package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberId);
}
