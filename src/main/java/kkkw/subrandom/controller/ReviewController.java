package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.RecipeDto;
import kkkw.subrandom.dto.ReviewDto;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final RecipeService recipeService;
    private final ReviewService reviewService;

    @PostMapping("/write")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Review> reviewAdd(
            @Valid @RequestBody ReviewDto reviewDto
    ) {
        Review review = reviewService.addMyReview(reviewDto);
        return ResponseEntity.ok(review);
    }

    @GetMapping("")
    public ResponseEntity<List<Review>> reviewList() {
        return ResponseEntity.ok(reviewService.findReviews());
    }

    @GetMapping("/detail/{reviewId}")
    public ResponseEntity<String> reviewCommentDetail(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.findReviewComment(reviewId));
    }
}
