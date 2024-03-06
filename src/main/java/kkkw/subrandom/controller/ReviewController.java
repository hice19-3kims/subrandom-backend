package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.ReviewCreateDto;
import kkkw.subrandom.dto.ReviewGetDto;
import kkkw.subrandom.repository.ReviewRepository;
import kkkw.subrandom.service.HeartService;
import kkkw.subrandom.service.MemberService;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
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
    private final MemberService memberService;

    @PostMapping("/write")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Review> reviewAdd(
            @Valid @RequestBody ReviewCreateDto reviewCreateDto
    ) {
        Member member = memberService.findMyMemberWithAuthorities();
        Recipe recipe = recipeService.findRecipeByIdOrThrow(reviewCreateDto.getRecipeId());
        Review review = reviewService.addReview(member, recipe, reviewCreateDto);
        return ResponseEntity.ok(review);
    }

    @GetMapping("")
    public Page<ReviewGetDto> reviewList(@RequestParam(value = "page", required = false, defaultValue = "0") int page) {
        Page<Review> paging = reviewService.findPagedReviews(page);
        return new PageImpl<>(paging.stream()
                .map(ReviewGetDto::new)
                .collect(Collectors.toList()), paging.getPageable(), paging.getTotalElements());
    }

    @PostMapping("/heart")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Heart> reviewAddHeart(@RequestBody Long reviewId) {
        Member member = memberService.findMyMemberWithAuthorities();
        Review review = reviewService.findReview(reviewId);
        return ResponseEntity.ok(heartService.changeHeart(member, review));
    }

}
