package kkkw.subrandom.service;

import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Heart;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import kkkw.subrandom.domain.recipe.recipechoice.Sauce;
import kkkw.subrandom.domain.recipe.recipechoice.Vegetable;
import kkkw.subrandom.dto.ReviewCreateDto;
import kkkw.subrandom.exceptions.HeartNotFoundException;
import kkkw.subrandom.exceptions.ReviewNotFoundException;
import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.ReviewRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceTest {

    @Mock
    private ReviewRepository reviewRepository;
    @Mock
    private HeartRepository heartRepository;

    @InjectMocks
    private ReviewService reviewService;

    Authority authority = Authority.builder()
            .authorityName("ROLE_USER")
            .build();

    Member member = Member.builder()
            .name("aaa").email("aaa@a.com").password("1111")
            .authorities(Collections.singleton(authority))
            .activated(true)
            .build();

    Recipe recipe = Recipe.builder()
            .id(1L)
            .bread("flat")
            .mainStuff("bbq")
            .build();

    ReviewCreateDto reviewCreateDto = ReviewCreateDto.builder()
            .recipeId(1L).score(3.0F).comment("Comment Test.")
            .build();

    Review review = Review.builder()
            .member(member)
            .recipe(recipe)
            .score(reviewCreateDto.getScore())
            .comment(reviewCreateDto.getComment())
            .heartCounts(0L)
            .date(LocalDateTime.now())
            .build();

    @BeforeEach
    void beforeEach() {
        Cheese cheese1 = Cheese.builder().id(1L).cheeseName("american").build();
        Cheese cheese2 = Cheese.builder().id(2L).cheeseName("mozzarella").build();
        Sauce sauce1 = Sauce.builder().id(1L).sauceName("bbq").build();
        Sauce sauce2 = Sauce.builder().id(2L).sauceName("chipotle").build();
        Vegetable vegetable1 = Vegetable.builder().id(1L).vegetableName("cucumber").build();
        Vegetable vegetable2 = Vegetable.builder().id(2L).vegetableName("jalapeno").build();

        RecipeCheese.CreateRecipeCheese(cheese1, recipe);
        RecipeCheese.CreateRecipeCheese(cheese2, recipe);
        RecipeSauce.CreateRecipeSauce(sauce1, recipe);
        RecipeSauce.CreateRecipeSauce(sauce2, recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetable1, recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetable2, recipe);
    }

    @Test
    @DisplayName("리뷰 추가 로직 테스트")
    void addReview() {
        //given
        when(reviewRepository.save(any())).thenReturn(review);

        //when
        Review result = reviewService.addReview(member, recipe, reviewCreateDto);

        //then
        verify(reviewRepository, Mockito.times(1)).save(any());
    }

    @Test
    @DisplayName("멤버별 리뷰 조회 로직 테스트")
    void findReviewsByMemberId() throws Exception{
        //given
        List<Review> reviews = new ArrayList<>();
        reviews.add(review);

        when(reviewRepository.findByMemberId(member.getId()))
                .thenReturn(reviews);

        when(reviewRepository.findByMemberId(2L))
                .thenReturn(Collections.emptyList());

        //when
        List<Review> result1 = reviewService.findReviewsByMemberId(member.getId());
        Throwable throwable = catchThrowable(() -> {
            List<Review> result2 = reviewService.findReviewsByMemberId(2L);
        });

        //then
        Assertions.assertThat(throwable).isInstanceOf(ReviewNotFoundException.class);
        verify(reviewRepository, Mockito.times(3)).findByMemberId(any());
    }

    @Test
    @DisplayName("리뷰 조회 로직 테스트")
    void findReview() throws Exception{
        //given
        when(reviewRepository.findById(review.getId()))
                .thenReturn(Optional.of(review));

        when(reviewRepository.findById(2L))
                .thenReturn(Optional.empty());

        //when
        Review result1 = reviewService.findReview(review.getId());
        Throwable throwable = catchThrowable(() -> {
            Review result2 = reviewService.findReview(2L);
        });

        //then
        Assertions.assertThat(throwable).isInstanceOf(ReviewNotFoundException.class);
        verify(reviewRepository, Mockito.times(3)).findById(any());
    }

    @Test
    @DisplayName("좋아요한 리뷰 조회 로직")
    void findReviewsByMemberHeart() throws Exception{
        //given

        Review review2 = Review.builder()
                .member(member)
                .recipe(recipe)
                .score(reviewCreateDto.getScore())
                .comment(reviewCreateDto.getComment())
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        List<Review> reviews = new ArrayList<>();
        reviews.add(review);
        reviews.add(review2);

        List<Heart> hearts = new ArrayList<>();
        hearts.add(Heart.createHeart(member, review));
        hearts.add(Heart.createHeart(member, review2));

        when(heartRepository.findByMemberId(member.getId()))
                .thenReturn(hearts);

        when(heartRepository.findByMemberId(2L))
                .thenReturn(Collections.emptyList());

        //when
        List<Review> result = reviewService.findReviewsByMemberHeart(member.getId());

        Throwable throwable = catchThrowable(() -> {
            reviewService.findReviewsByMemberHeart(2L);
        });

        //then
        Assertions.assertThat(throwable).isInstanceOf(HeartNotFoundException.class);
        Assertions.assertThat(result).isEqualTo(reviews);
    }

    //이하 단순 조회 로직은 생략
    @Test
    void findReviewsByDate() {
        //given

        //when

        //then
    }

    @Test
    void findReviewsByHeartCounts() {
        //given

        //when

        //then
    }

    @Test
    void findReviewsByScore() {
        //given

        //when

        //then
    }

    @Test
    void findPagedReviews() {
        //given

        //when

        //then
    }
}