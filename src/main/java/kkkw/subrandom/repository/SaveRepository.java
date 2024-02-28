package kkkw.subrandom.repository;

import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SaveRepository extends JpaRepository<Save, Long> {
    List<Save> findByMemberId(Long memberId);

}
