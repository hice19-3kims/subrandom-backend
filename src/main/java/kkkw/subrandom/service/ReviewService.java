package kkkw.subrandom.service;


import kkkw.subrandom.domain.Review;
import kkkw.subrandom.dto.ReviewDto;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final HeartRepository heartRepository;
    public final MemberRepository memberRepository;
    private final RecipeRepository recipeRepository;

    public List<Review> findReviews() {
        return reviewRepository.findAll();
    }

    @Transactional
    public Review addMyReview(ReviewDto reviewDto) {

        Review review = Review.builder()
                .member(memberRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUsername().get()).get())
                .recipe(recipeRepository.findById(reviewDto.getRecipeId()).get())
                .score(reviewDto.getScore())
                .comment(reviewDto.getComment())
                .build();

        return reviewRepository.save(review);
    }

    public String findReviewComment(Long reviewId) {
        return reviewRepository.findById(reviewId).get().getComment();
    }
}
