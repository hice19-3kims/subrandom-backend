package kkkw.subrandom.domaintest;


import jakarta.validation.ConstraintViolation;
import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Review;
import kkkw.subrandom.domain.recipe.Recipe;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

//@ExtendWith(SpringExtension.class)
//@SpringBootTest
@Transactional
public class ReviewTest {
    @Test
    public void reviewTest() throws Exception {
        Member member = Member.builder().id(1L).build();
        Recipe recipe = new Recipe(1L, "asdf", "asdfdasf", null, null, null);
        Review review = new Review(1L, member, recipe, 1.1F, null);
        System.out.println("review Comment = " + review.getComment());
        System.out.println("====================");
        System.out.println("member email = " + member.getEmail());

    }
}


