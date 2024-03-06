package kkkw.subrandom.service;


import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.ReviewCreateDto;
import kkkw.subrandom.exceptions.HeartNotFoundException;
import kkkw.subrandom.exceptions.ReviewNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final HeartRepository heartRepository;

    @Transactional
    public Review addReview(Member member, Recipe recipe, ReviewCreateDto reviewCreateDto) {
        Review review = Review.builder()
                .member(member)
                .recipe(recipe)
                .score(reviewCreateDto.getScore())
                .comment(reviewCreateDto.getComment())
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

    public Review findReview(Long reviewId) {
        if(reviewRepository.findById(reviewId).isPresent())
            return reviewRepository.findById(reviewId).get();
        else throw new ReviewNotFoundException();
    }

    public List<Review> findReviewsByMemberId(Long memberId) {
        if(!reviewRepository.findByMemberId(memberId).isEmpty())
            return reviewRepository.findByMemberId(memberId);
        else throw new ReviewNotFoundException();
    }

    public List<Review> findReviewsByMemberHeart(Long memberId) {
        if(!heartRepository.findByMemberId(memberId).isEmpty()) {
            List<Review> result = new ArrayList<>();
            heartRepository.findByMemberId(memberId).forEach(h -> result.add(h.getReview()));
            return result;
        } else throw new HeartNotFoundException();
    }

    // 최신순 정렬
    public List<Review> findReviewsByDate() {
        if(!reviewRepository.findAll().isEmpty())
            return reviewRepository.findByDate();
        else throw new ReviewNotFoundException();
    }

    // 좋아요순 정렬
    public List<Review> findReviewsByHeartCounts() {
        if(!reviewRepository.findAll().isEmpty())
            return reviewRepository.findByHeartCounts();
        else throw new ReviewNotFoundException();
    }

    // 별점순 정렬
    public List<Review> findReviewsByScore() {
        if(!reviewRepository.findAll().isEmpty())
            return reviewRepository.findByScore();
        else throw new ReviewNotFoundException();
    }

    public Page<Review> findPagedReviews(int page) {
        Pageable pageable = PageRequest.of(page, 10);
        if(!reviewRepository.findAll(pageable).isEmpty())
            return reviewRepository.findAll(pageable);
        else throw new ReviewNotFoundException();
    }
}
