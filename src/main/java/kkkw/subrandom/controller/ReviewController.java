package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.dto.ReviewCreateDto;
import kkkw.subrandom.dto.ReviewGetDto;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.service.HeartService;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final RecipeService recipeService;
    private final ReviewRepository reviewRepository;
    private final ReviewService reviewService;
    private final HeartService heartService;

    @PostMapping("/write")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Review> reviewAdd(
            @Valid @RequestBody ReviewCreateDto reviewCreateDto
    ) {
        Review review = reviewService.addMyReview(reviewCreateDto);
        return ResponseEntity.ok(review);
    }

    @GetMapping("")
    public ResponseEntity<List<ReviewGetDto>> reviewList() {
        List<Review> reviews = reviewRepository.findAll();
        List<ReviewGetDto> result = reviews.stream()
                .map(r -> new ReviewGetDto(r))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/detail/comment/{reviewId}")
    public ResponseEntity<String> reviewCommentDetail(@PathVariable Long reviewId) {
        return ResponseEntity.ok(reviewService.findReviewComment(reviewId));
    }

    @PostMapping("/heart")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Heart> heartAdd(@RequestBody Long reviewId) {
        return ResponseEntity.ok(heartService.addMyHeart(reviewRepository.findById(reviewId).get()));
    }

}
