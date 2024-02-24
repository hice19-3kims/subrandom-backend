package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Heart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HeartRepository extends JpaRepository<Heart, Long> {

    List<Heart> findByMember(Long member);
    List<Heart> findByReview(Long review);
}
