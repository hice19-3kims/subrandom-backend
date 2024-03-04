package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberId);

    @Query("SELECT r FROM Review r ORDER BY r.date DESC")
    List<Review> findByDate();

    @Query("SELECT r FROM Review r ORDER BY r.heartCounts ASC")
    List<Review> findByHeartCounts();

    @Query("SELECT r FROM Review r ORDER BY r.score ASC")
    List<Review> findByScore();
}
