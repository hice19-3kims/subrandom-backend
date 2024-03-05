package kkkw.subrandom.service;


import kkkw.subrandom.domain.Review;
import kkkw.subrandom.dto.ReviewCreateDto;
import kkkw.subrandom.exceptions.HeartNotFoundException;
import kkkw.subrandom.exceptions.ReviewNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
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
    public final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    @Transactional
    public Review addMyReview(ReviewCreateDto reviewCreateDto) {
        Review review = Review.builder()
                .member(memberRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUserEmail()).get())
                .recipe(recipeRepository.findById(reviewCreateDto.getRecipeId()).get())
                .score(reviewCreateDto.getScore())
                .comment(reviewCreateDto.getComment())
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();
        return reviewRepository.save(review);
    }

//    public List<Review> findReviews() {
//        return reviewRepository.findAll();
//    }

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
}
