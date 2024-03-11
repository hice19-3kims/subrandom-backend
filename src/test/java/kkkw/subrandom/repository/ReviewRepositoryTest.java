package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Authority;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.domain.recipe.RecipeCheese;
import kkkw.subrandom.domain.recipe.RecipeSauce;
import kkkw.subrandom.domain.recipe.RecipeVegetable;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.repository.recipechoice.CheeseRepository;
import kkkw.subrandom.repository.recipechoice.SauceRepository;
import kkkw.subrandom.repository.recipechoice.VegetableRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class ReviewRepositoryTest {

    @Autowired
    ReviewRepository reviewRepository;
    @Autowired
    SauceRepository sauceRepository;
    @Autowired
    CheeseRepository cheeseRepository;
    @Autowired
    VegetableRepository vegetableRepository;
    @Autowired
    MemberRepository memberRepository;
    @Autowired
    RecipeRepository recipeRepository;

    @Test
    @DisplayName("리뷰 생성")
    void createReview() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review = Review.builder()
                .member(member)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        //when
        Review result1 = reviewRepository.save(review);

        //then
        assertThat(result1.getMember()).isEqualTo(member);
        assertThat(result1.getRecipe()).isEqualTo(recipe);
        assertThat(result1.getScore()).isEqualTo(3.5F);
        assertThat(result1.getComment()).isEqualTo("comment");
        assertThat(result1.getHeartCounts()).isEqualTo(0L);
        assertThat(result1.getDate()).isEqualTo(review.getDate());
        System.out.println("////////////////////////////////");
        System.out.println("result1.getDate() = " + result1.getDate());
        System.out.println("recipe.getRecipeVegetables().get(0).getVegetable().getVegetableName() = " + recipe.getRecipeVegetables().get(0).getVegetable().getVegetableName());
        System.out.println("////////////////////////////////");
    }


    @Test
    @DisplayName("멤버로 조회")
    void findByMemberId() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review1 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment1")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        for(int i = 0; i < 1000000000; i++) {}

        Review review2 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(5.0F)
                .comment("comment2")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);

        //when
        List<Review> member1Reviews = reviewRepository.findByMemberId(member1.getId());
        List<Review> member2Reviews = reviewRepository.findByMemberId(member2.getId());

        //then
        assertThat(member1Reviews).containsExactly(review1, review2);
        assertThat(member2Reviews).isEmpty();
    }

    @Test
    @DisplayName("최신순 조회")
    void findByDate() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review1 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment1")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        for(int i = 0; i < 1000000000; i++) {}

        Review review2 = Review.builder()
                .member(member2)
                .recipe(recipe)
                .score(5.0F)
                .comment("comment2")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);

        //when
        List<Review> reviewsByDate = reviewRepository.findByDate();

        //then
        assertThat(reviewsByDate).containsExactly(review2, review1);
//        System.out.println("/////////////////////////////////");
//        for(Review review : reviewsByDate)
//            System.out.println(review.getScore());
//        System.out.println("/////////////////////////////////");
    }


    @Test
    @DisplayName("좋아요순 조회")
    void findByHeartCounts() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review1 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment1")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        for(int i = 0; i < 1000000000; i++) {}

        Review review2 = Review.builder()
                .member(member2)
                .recipe(recipe)
                .score(5.0F)
                .comment("comment2")
                .heartCounts(3L)
                .date(LocalDateTime.now())
                .build();

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);

        //when
        List<Review> reviewsByHeart = reviewRepository.findByHeartCounts();

        //then
        assertThat(reviewsByHeart).containsExactly(review2, review1);
    }

    @Test
    @DisplayName("별점순 조회")
    void findByScore() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review1 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment1")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        for(int i = 0; i < 1000000000; i++) {}

        Review review2 = Review.builder()
                .member(member2)
                .recipe(recipe)
                .score(5.0F)
                .comment("comment2")
                .heartCounts(3L)
                .date(LocalDateTime.now())
                .build();

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);

        //when
        List<Review> reviewsByScore = reviewRepository.findByScore();

        //then
        assertThat(reviewsByScore).containsExactly(review2, review1);
    }

    @Test
    @DisplayName("전체 조회")
    void findAll() {
        //given
        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        Member member1 = Member.builder()
                .name("member1")
                .email("a@a.com")
                .password("1111")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        Member member2 = Member.builder()
                .name("member2")
                .email("b@b.com")
                .password("2222")
                .authorities(Collections.singleton(authority))
                .activated(true)
                .build();

        memberRepository.save(member1);
        memberRepository.save(member2);

        Recipe recipe = Recipe.builder()
                .bread("오트")
                .mainStuff("BLT")
                .build();

        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(1L).get(), recipe);
        RecipeSauce.CreateRecipeSauce(sauceRepository.findById(2L).get(), recipe);

        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(1L).get(), recipe);
        RecipeCheese.CreateRecipeCheese(cheeseRepository.findById(2L).get(), recipe);

        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(1L).get(), recipe);
        RecipeVegetable.CreateRecipeVegetable(vegetableRepository.findById(2L).get(), recipe);

        recipeRepository.save(recipe);

        Review review1 = Review.builder()
                .member(member1)
                .recipe(recipe)
                .score(3.5F)
                .comment("comment1")
                .heartCounts(0L)
                .date(LocalDateTime.now())
                .build();

        for(int i = 0; i < 1000000000; i++) {}

        Review review2 = Review.builder()
                .member(member2)
                .recipe(recipe)
                .score(5.0F)
                .comment("comment2")
                .heartCounts(3L)
                .date(LocalDateTime.now())
                .build();

        Review result1 = reviewRepository.save(review1);
        Review result2 = reviewRepository.save(review2);

        //when
        List<Review> reviews = reviewRepository.findAll();
        Pageable pageable = PageRequest.of(0,10);
        Page<Review> reviewPage = reviewRepository.findAll(pageable);

        //then
        assertThat(reviews).containsExactly(review1, review2);
        assertThat(reviewPage).containsExactly(review1, review2);
    }
}