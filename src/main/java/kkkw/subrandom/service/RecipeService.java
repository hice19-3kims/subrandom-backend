package kkkw.subrandom.service;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true) //기본값 조회가 성능이 좋음
@RequiredArgsConstructor
public class RecipeService {

}