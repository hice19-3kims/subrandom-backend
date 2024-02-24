package kkkw.subrandom.service;


import kkkw.subrandom.repository.HeartRepository;
import kkkw.subrandom.repository.MemberRepository;
import kkkw.subrandom.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReviewService {

    public final ReviewRepository reviewRepository;
    public final HeartRepository heartRepository;
    public final MemberRepository memberRepository;


}
