package kkkw.subrandom.repository.recipechoice;

import kkkw.subrandom.domain.recipe.recipechoice.Cheese;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheeseRepository extends JpaRepository<Cheese, Long> {
    // List<Cheese> findByCheeseName(String ch);
    // 하이버네이트에서 알아서 다 구현 해줌
    // 복잡한 쿼리는 Querydsl
}