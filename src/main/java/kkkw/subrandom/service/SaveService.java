package kkkw.subrandom.service;

import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.SaveRepository;
import kkkw.subrandom.repository.recipe.RecipeRepository;
import kkkw.subrandom.util.SecurityUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaveService {

    private final MemberRepository memberRepository;
    private final SaveRepository saveRepository;

    @Transactional
    public Save addMySave(Recipe recipe) {

        Save save = Save.builder()
                .member(memberRepository.findOneWithAuthoritiesByEmail(SecurityUtil.getCurrentUsername().get()).get())
                .recipe(recipe)
                .build();

        return saveRepository.save(save);
    }
}
