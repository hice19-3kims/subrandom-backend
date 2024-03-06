package kkkw.subrandom.service;

import kkkw.subrandom.domain.Member;
import kkkw.subrandom.domain.Save;
import kkkw.subrandom.domain.recipe.Recipe;
import kkkw.subrandom.exceptions.SaveNotFoundException;
import kkkw.subrandom.repository.SaveRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SaveService {

    private final SaveRepository saveRepository;

    @Transactional
    public Save addSave(Member member, Recipe recipe) {
        Save save = Save.builder()
                .member(member)
                .recipe(recipe)
                .build();
        return saveRepository.save(save);
    }

    public List<Save> findSavesByMember(Long memberId) {
        if(!saveRepository.findByMemberId(memberId).isEmpty())
            return saveRepository.findByMemberId(memberId);
        else throw new SaveNotFoundException();
    }
}
