package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMemberId(Long memberId);

    @Query("SELECT r FROM Review r ORDER BY r.date DESC")
    List<Review> findByDate();

    @Query("SELECT r FROM Review r ORDER BY r.heartCounts DESC")
    List<Review> findByHeartCounts();

    @Query("SELECT r FROM Review r ORDER BY r.score DESC")
    List<Review> findByScore();

    Page<Review> findAll(Pageable pageable);
}
