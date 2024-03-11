package kkkw.subrandom.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;

    @Test
    @DisplayName("리뷰 생성")
    void createReview() {

    }

    @Test
    @DisplayName("멤버로 조회")
    void findByMemberId() {

    }

    @Test
    @DisplayName("최신순 조회")
    void findByDate() {
    }

    @Test
    @DisplayName("좋아요순 조회")
    void findByHeartCounts() {
    }

    @Test
    @DisplayName("별점순 조회")
    void findByScore() {
    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
    }
}