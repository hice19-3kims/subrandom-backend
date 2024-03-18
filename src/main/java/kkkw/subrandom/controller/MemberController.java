package kkkw.subrandom.controller;

import jakarta.validation.Valid;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.dto.MemberGetDto;
import kkkw.subrandom.dto.SignupDto;
import kkkw.subrandom.dto.RecipeGetDto;
import kkkw.subrandom.dto.ReviewGetDto;
import kkkw.subrandom.service.MemberService;
import kkkw.subrandom.service.RecipeService;
import kkkw.subrandom.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;
    private final ReviewService reviewService;
    private final RecipeService recipeService;

    @PostMapping("/signup")
    public ResponseEntity<MemberGetDto> memberAdd(
            @Valid @RequestBody SignupDto signupDto
    ) {
        Member member = memberService.addMember(signupDto);
        return ResponseEntity.ok(MemberGetDto.builder()
                .email(member.getEmail())
                .name(member.getName())
                .build());
    }

    @GetMapping("/me/reviews")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ReviewGetDto>> memberReviewList() {
        Member member = memberService.findMyMemberWithAuthorities();
        List<Review> reviews = reviewService.findReviewsByMemberId(member.getId());

        List<ReviewGetDto> result = reviews.stream()
                .map(ReviewGetDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/recipes")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<RecipeGetDto>> memberRecipeList() {
        Member member = memberService.findMyMemberWithAuthorities();
        List<Recipe> recipes = recipeService.findSavedRecipesByMemberId(member.getId());

        List<RecipeGetDto> result = recipes.stream()
                .map(RecipeGetDto::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }

    @GetMapping("/me/liked")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<List<ReviewGetDto>> memberHeartReviewList() {
        Member member = memberService.findMyMemberWithAuthorities();
        List<Review> reviews = reviewService.findReviewsByMemberHeart(member.getId());

        List<ReviewGetDto> result = reviews.stream()
                .map(r -> new ReviewGetDto(r))
                .collect(Collectors.toList());

        return ResponseEntity.ok(result);
    }



    @GetMapping("/me")
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public ResponseEntity<Member> memberMyDetails() {
        return ResponseEntity.ok(memberService.findMyMemberWithAuthorities());
    }

    @GetMapping("/{email}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Member> memberDetails(@PathVariable String email) {
        return ResponseEntity.ok(memberService.findMemberWithAuthorities(email));
    }

}
